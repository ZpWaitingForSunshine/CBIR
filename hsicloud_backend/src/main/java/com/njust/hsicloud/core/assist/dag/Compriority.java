package com.njust.hsicloud.core.assist.dag;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Vm;

import com.njust.hsicloud.core.assist.dag.ListDGraph.VE;
import com.njust.hsicloud.core.assist.help.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

/**
 * Created by Tao on 2017/11/24.
 * �������ȼ���est�����翪ʼʱ�䣩��lst����ʼʱ�䡢�ؼ�·��
 */
public class Compriority {
    /**�����ʵı߶���*/
    private Queue<Edge> mEQueue = null;
    /**�����ʵĽڵ����*/
    private Queue<Cloudlet> mVQueue = null;
    /**ListDGraph*/
    private ListDGraph mDG = null;
    ReadPar readpra = null;
    private int vex_num;//�ڵ�����
    private Cloudlet root;//DAG���ڵ�
    private Cloudlet tail;//DAG��β�ڵ�
    private List cplist;//��Źؼ�·���ڵ�
    //��¼�ڵ����翪ʼʱ��
    private LinkedHashMap<Cloudlet, Double> mestmap = null;
    //��¼�ڵ�����ʼʱ��
    private Map<Cloudlet, Double> mlstmap = null;
    //��¼���ȼ�
    private Map<Cloudlet, Double> ftmap = null;
    //����������ʱ��
    private double lasttime;//��¼�������ʱ��
    private Arith arith;//����double���ͼӷ��ͼ���
    private List<Map.Entry<Cloudlet, Double>> sortlist = null;//�������翪ʼʱ����б�
    private Map<Cloudlet, Double> primap = null;//�������ȼ�
    private Map<Cloudlet, Integer> heightmap = null;
    List<Cloudlet> ergodic = null;//������ȱ���
    private Queue<Cloudlet> est_time;
    private static LinkedHashMap<Cloudlet, Integer> height;
    private Vm vm;
    public Compriority(ListDGraph mDG, Cloudlet root, Cloudlet tail, List<Cloudlet> er, Vm vm) {
        this.mDG = mDG;
        this.root = root;
        this.tail = tail;
        this.ergodic = er;
        this.vm = vm;
        this.est_time = new LinkedList<>();

        mEQueue = new LinkedList<Edge>();
        vex_num = mDG.getmVEList().size();
        mestmap = new LinkedHashMap<>();
        mlstmap = new LinkedHashMap<>();
        ftmap = new LinkedHashMap<>();
        lasttime = 0.0;
        arith = new Arith();//double����ӷ��������������
        cplist = new ArrayList<Cloudlet>();
        primap = new LinkedHashMap<Cloudlet, Double>();
        heightmap = new LinkedHashMap<>();
        mVQueue = new LinkedList<Cloudlet>();
        ReadPar readpra = new ReadPar();//���������������
        ergodic = new ArrayList<>();
    }

    /**
     * �������ʱ��
     * @throws Exception 
     */
    public ArrayList<Cloudlet> totaltime() throws Exception {
        est();
        cal_height();
        ArrayList<Cloudlet> result = new ArrayList<>();
        List<Cloudlet> caled = new ArrayList<>();
        while(!est_time.isEmpty()) {
            Cloudlet cloudlet = est_time.peek();
            if (!caled.contains(cloudlet)) {
                if (cloudlet.getflag() == false) {
                    result.add(cloudlet);
                    caled.add(cloudlet);
                    est_time.poll();
                    height.remove(cloudlet);
                }else {
                    int x = height.get(cloudlet);
                    Cloudlet cloudlet2 = getfirstcloudlet(height);
                    if (height.get(cloudlet2)<x) {
                        result.add(cloudlet2);
                        caled.add(cloudlet2);
                        height.remove(cloudlet2);
                    }else {
                        result.add(cloudlet);
                        caled.add(cloudlet);
                        est_time.poll();
                        height.remove(cloudlet);
                    }
                }
            }else {
                est_time.poll();
            }
        }
        return result;
    }

    private Cloudlet getfirstcloudlet(LinkedHashMap<Cloudlet, Integer> height) {
        Cloudlet rc = null;
        for(Cloudlet key : height.keySet()){
            rc = key;
            break;
        }
        return rc ;
    }

    //����DAG���ڵ������ġ��߶ȡ�
    private void cal_height() {
        mVQueue.clear();
        heightmap.put(root, 1);
        mVQueue.offer(root);
        //1.ȡ����Ԫ��
        Cloudlet v = mVQueue.poll();
        System.out.print((v.getCloudletLength()/vm.getMips()));
        while(v != null) {
            //2.����Ԫ�ص��ڽӱ��ж�Ӧ��������У���Щ������Ҫ��������������
            //1)û���ʹ���
            //2)���ڶ����У�
            VE ve = mDG.getVE(v);
            int x = heightmap.get(v);
            if(ve != null) {
                List<Edge> list = ve.mEdgeList;
                for(Edge edge : list) {
                    Cloudlet dest = edge.getDest();
                    if(! heightmap.containsKey(dest)){
                        heightmap.put(dest, x+1);
                    }else{
                        if(x+1>heightmap.get(dest)){
                            heightmap.remove(dest);
                            heightmap.put(dest, x+1);
                        }
                    }
                    mVQueue.offer(dest);
                }
            }
            v = mVQueue.poll();
        }

        //����
        List<Entry<Cloudlet, Integer>> list = new ArrayList<Map.Entry<Cloudlet, Integer>>(heightmap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Cloudlet, Integer>>() {
            @Override
            public int compare(Entry<Cloudlet, Integer> o1,
                               Entry<Cloudlet, Integer> o2) {
                // TODO �Զ����ɵķ������
                return o1.getValue().compareTo(o2.getValue());
                //return (int) o1.getValue().toString().compareTo(o2.getValue().toString());
            }
        });

        for (Map.Entry<Cloudlet, Integer> mapping : list) {
            System.out.println(mapping.getKey() + "::" + mapping.getValue());
            height.put(mapping.getKey(), mapping.getValue());
        }
    }

    /**����est
     * �ѱߴ��ȥ����������ߵ�dest���ж��Ƿ����est
     */
    public LinkedHashMap<Cloudlet,Double> est() throws Exception{
    	try{
    		//�ж��Ƿ���ø÷���
    		System.out.println("------------------------------------------");
    		    mEQueue.clear();            //�����ʱ߶���
    	        mestmap.put(root, 0.0);     //LinkedHashMap

    	        VE r = mDG.getVE(root);
    	        System.out.println(r);

    	        List<Edge> rlist = r.mEdgeList;
    	        System.out.println("daxiao "+rlist.size());
    	        for(Edge edge : rlist) {
    	   	        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhh");
    	        	System.out.println("test������ɶ"+edge);
    	            mEQueue.offer(edge);
    	        }
    	        System.out.println("jjjjjjjjjjjj");
    	        //1.ȡ����Ԫ��
    	        Edge e = mEQueue.poll();
    	        while(e != null) {
    	            Cloudlet dest = e.getDest();
    	            //�ж��Ƿ����
    	            double sum = arith.add(0, arith.add(mestmap.get(e.getSource()), (e.getSource().getCloudletLength()/vm.getMips())));
    	            if(!mestmap.containsKey(dest)){
    	                mestmap.put(dest, sum);
    	            }else{
    	                double w = mestmap.get(dest);
    	                //�Ƚϴ�С
    	                if(sum > w){
    	                    mestmap.remove(e.getDest());
    	                    mestmap.put(e.getDest(), sum);
    	                }
    	            }
    	            r = mDG.getVE(dest);
    	            rlist = r.mEdgeList;
    	            for(Edge edge : rlist) {
    	                mEQueue.offer(edge);
    	            }
    	            e = mEQueue.poll();
    	        }
    	        //����
    	        sortlist = new ArrayList<Map.Entry<Cloudlet, Double>>(mestmap.entrySet());
    	        Collections.sort(sortlist, new Comparator<Map.Entry<Cloudlet, Double>>() {
    	            @Override
    	            public int compare(Entry<Cloudlet, Double> o1,
    	                               Entry<Cloudlet, Double> o2) {
    	                // TODO �Զ����ɵķ������
    	                if(o1.getValue()< o2.getValue())
    	                    return -1;
    	                else {
    	                    return 1;
    	                }
    	            }
    	        });
    	        lasttime = sortlist.get(sortlist.size()-1).getValue();
    	        LinkedHashMap<Cloudlet, Double> result = new LinkedHashMap<>();
    	        for (Map.Entry<Cloudlet, Double> mapping : sortlist) {
    	            System.out.println(mapping.getKey() + ":" + mapping.getValue());
    	            result.put(mapping.getKey(), mapping.getValue());
    	            est_time.offer(mapping.getKey());
    	        }
    	        return result;
    	    
    	}catch(Exception e) {
    		 throw e ;
    	}
    }
}

