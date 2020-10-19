package com.njust.hsicloud.core.assist.algrithm;

import com.njust.hsicloud.core.assist.help.*;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.lists.CloudletList;

import com.njust.hsicloud.core.assist.dag.*;

import java.util.*;
/**
 * Created by Tao on 2017/12/1.
 * x: x��ÿ��ֵ�Ķ�������ʽ��Ӧtask��VMʹ�����
 */
public class Individual {
    int VMsize;
    List<Cloudlet> cloudletList;
    ListDGraph mDG;
    ArrayList<Edge> edgeList;
    public Individual(int VMsize, List<Cloudlet> cloudletList,
                      ListDGraph mDG,ArrayList<Edge> edgeList) {
        this.VMsize = VMsize;
        this.cloudletList = cloudletList;
        this.mDG = mDG;
        this.edgeList = edgeList;
    }
    public Population fun(ArrayList<Integer> x) {
        LinkedHashMap<String, Integer> listx = new LinkedHashMap<>();
        ArrayList<Double> listf = new ArrayList<>();
        long makespan = 0;                                      //time of the task
        long[] time = new long[VMsize];                         //computation time of each VM
        long[] timeAmount = new long[VMsize];                   //the amount time of each VM
        boolean flag1 = false;                                  //�Ƿ�����ִ�нڵ���ӽڵ�
        boolean flag2 = true;                                   //��ǰ�Ƿ�ɷ���VM
        boolean[] VM = new boolean[VMsize];                     //false����ʹ��     ��ʼ��false
        double f1 = 0;
        double f2 = 0;
        double f3 = 0;
        Set<Long> currentTime = new HashSet<>();
        ArrayList<Integer> completeTimeOfFather = new ArrayList<>();//�����ָ��ӹ�ϵ����¼ȫ�����ڵ�����ʱ��
        int[] completeTime = new int[VMsize];                     //��̨��������ʱ��
//        ArrayList<Cloudlet> preCloudlet = new ArrayList<>();
//        Cloudlet[] preCloudlet = new Cloudlet[VMsize];          //�������������ִ�е�����������ִ����Ϊnull
        Map<Cloudlet,Integer> preCloudlet = new HashMap<>();      //<�������ʱ��>
        Cloudlet currentCloudlet;

        for (int i = 0; i < x.size(); i++) {
            completeTimeOfFather.clear();
            flag1 = false;
            flag2 = true;
            int xTmp = x.get(i);
            ArrayList<Integer> indexOne = getAttribute(xTmp);    //���xTmp��Ӧ��������ʽ1���±�λ��,��0��ʼ
//            listx.add(xTmp);
            listx.put(cloudletList.get(i).toString(),xTmp);      //toString(): the name of Cloudlet
            Cloudlet tmpCloudlet = cloudletList.get(i);

            /**
             * ��ǰ�ڵ��Ƿ��ǵ�ǰ���н׶��е�ĳһ�ڵ���ӽڵ�
             */
            currentCloudlet = cloudletList.get(i);
            Iterator<Map.Entry<Cloudlet,Integer>> iterator = preCloudlet.entrySet().iterator();
            while (iterator.hasNext()) {                                     //�ж����ڵ��Ƿ��Ǹ��ӹ�ϵ
                Map.Entry<Cloudlet,Integer> entry = iterator.next();
                Edge tmpEdge = new Edge(entry.getKey(),currentCloudlet);
                for (int jj = 0; jj < edgeList.size(); jj++) {
                    Edge existEdge = edgeList.get(jj);
                    if (existEdge.equals(tmpEdge)) {
                        completeTimeOfFather.add(entry.getValue());
                        flag1 = true;
                    }
                }
            }

            for (int j = 0; j < indexOne.size(); j++) {
                if(VM[indexOne.get(j)])     flag2 = false;          //���������Ƿ�ɲ���?????//
            }

            //���Ǹ����ҿɲ���
            if (flag1 == false && flag2 == true) {                  //�ɼ������������
                //ÿ��������ĵ���ִ��ʱ��֮��
                double startTime = 0.0;                             //����Ŀ�ʼʱ��
                for (int j = 0; j < indexOne.size(); j++) {
                    VM[indexOne.get(j)] = true;                     //��ǰVM��ʹ����
                    if (tmpCloudlet.getflag()) {                    //�ɲ��е�
                    	//���ж�
                       int parallel = indexOne.size();             //���ж�
//                   	  int index=indexOne.size(); 
//                	  int[] parallellll= {1,2,4,8};
//                	  int parallel=parallellll[index-1];
                        time[indexOne.get(j)] += tmpCloudlet.getlists(parallel-1).get(parallel-1).getCloudletLength();
                        currentTime.add(time[indexOne.get(j)]);
                        startTime = completeTime[indexOne.get(j)];
                        completeTime[indexOne.get(j)] += tmpCloudlet.getlists(parallel-1).get(parallel-1).getCloudletLength(); //���ʱ��

                        ArrayList<Cloudlet> arrCloudlets = tmpCloudlet.getlists(parallel - 1);
                        for (int k = 0; k < arrCloudlets.size(); k++) {
                            arrCloudlets.get(k).setExecStartTime(startTime);
                        }
                    } else {                                        //���ɲ��е�
                        time[indexOne.get(j)] += tmpCloudlet.getCloudletLength();
                        currentTime.add(time[indexOne.get(j)]);
                        startTime = completeTime[indexOne.get(j)];
                        completeTime[indexOne.get(j)] += tmpCloudlet.getCloudletLength();
                        tmpCloudlet.setExecStartTime(startTime);
                    }
                    preCloudlet.put(currentCloudlet,completeTime[indexOne.get(j)]);
                }
            }
            if (flag1 == false && flag2 == false) { //�ڵ�֮���޸��ӹ�ϵ��û�п��е�������ɷ���
                int max = 0;
                for (int j = 0; j < indexOne.size(); j++) {
                    if (completeTime[indexOne.get(j)] > max)
                        max = completeTime[indexOne.get(j)];    //�ҳ���Ҫ�õ�����������������ʱ��
                }

                //ÿ��������ĵ���ִ��ʱ��֮��
                for (int j = 0; j < indexOne.size(); j++) {
                    VM[indexOne.get(j)] = true;
                    if (tmpCloudlet.getflag()) {                //�ɲ��е�
                        int parallel = indexOne.size();         //���ж�
//                   	 int index=indexOne.size(); 
//                	 int[] parallellll= {1,2,4,8};
//                	 int parallel=parallellll[index-1];
                        ArrayList<Cloudlet> tmp = tmpCloudlet.getlists(parallel-1);
                        time[indexOne.get(j)] += tmpCloudlet.getlists(parallel-1).get(parallel-1).getCloudletLength();
                        currentTime.add(time[indexOne.get(j)]);
                        completeTime[indexOne.get(j)] = max;
                        completeTime[indexOne.get(j)] += tmpCloudlet.getlists(parallel-1).get(parallel-1).getCloudletLength();

                        ArrayList<Cloudlet> arrCloudlets = tmpCloudlet.getlists(parallel - 1);
                        for (int k = 0; k < arrCloudlets.size(); k++) {
                            arrCloudlets.get(k).setExecStartTime(max);
                        }
                    } else {                                    //���ɲ��е�
                        time[indexOne.get(j)] += tmpCloudlet.getCloudletLength();
                        currentTime.add(time[indexOne.get(j)]);
                        completeTime[indexOne.get(j)] = max;
                        completeTime[indexOne.get(j)] += tmpCloudlet.getCloudletLength();
                        tmpCloudlet.setExecStartTime(max);
                    }
                    preCloudlet.put(currentCloudlet,completeTime[indexOne.get(j)]);
                }
            }
            if (flag1 == true) {
                int max = Collections.max(completeTimeOfFather);
//                for (int j = 0; j < indexOne.size(); j++) {
//                    if (completeTime[indexOne.get(j)] > max)
//                        max = completeTime[indexOne.get(j)];
//                }
                /**
                 * test
                 */
                if (!tmpCloudlet.getflag()) {           //������
                    if (completeTime[indexOne.get(0)] > max)
                        max = completeTime[indexOne.get(0)];
                    time[indexOne.get(0)] += tmpCloudlet.getCloudletLength();
                    currentTime.add(time[indexOne.get(0)]);
                    completeTime[indexOne.get(0)] = max;
                    completeTime[indexOne.get(0)] += tmpCloudlet.getCloudletLength();
                    tmpCloudlet.setExecStartTime(max);
                    preCloudlet.put(currentCloudlet,completeTime[indexOne.get(0)]);//<�������ʱ��>
                }
               int parallel = indexOne.size();
//           	 int index=indexOne.size(); 
//           	 int[] parallellll= {1,2,4,8};
//           	 int parallel=parallellll[index-1];
                int compleTime = 0;
                int tmpMax = max;
                if (tmpCloudlet.getflag()) {            //���е�
                    ArrayList<Cloudlet> cloudletList = tmpCloudlet.getlists(parallel-1);
                    for (int j = 0; j < indexOne.size(); j++) {
                        if (completeTime[indexOne.get(j)] > tmpMax)
                            max = completeTime[indexOne.get(j)];

                        Cloudlet cloudletPiece = cloudletList.get(j);   //ĳһ���ɲ��������Ƭ
                        time[indexOne.get(j)] += cloudletPiece.getCloudletLength();
                        completeTime[indexOne.get(j)] = (int)(max + cloudletPiece.getCloudletLength());
                        if (completeTime[indexOne.get(j)] > compleTime) {
                            compleTime = completeTime[indexOne.get(j)];
                        }
                        //���ÿ�ʼʱ��
                        cloudletPiece.setExecStartTime(max);
                        max = tmpMax;
                    }
                    preCloudlet.put(currentCloudlet,compleTime);
                }
            }
        }

        Population res = new Population();
        for (int i = 0; i < VMsize; i++) {
            if (completeTime[i] > makespan)
                makespan = completeTime[i];
        }
        f1 = makespan;                             //Ŀ�꺯��1     �������ʱ��
        /**
         * calculate ���ؾ��� of all the VMs
         */
//        f2 = variance(time);                     //Ŀ�꺯��2     ��������ؾ���
//        f2 = Math.sqrt(f2);

        for (int k = 0; k < time.length; k++) {  //Ŀ�꺯��3     ���������
            f3 += (time[k] * 0.002);
            f3 += ((makespan - time[k]) * 0.000002);
        }
//        f3 = f3 / 1000;

        listf.add(f1);
//        listf.add(f2);
        listf.add(f3);
        res.setF(listf);
        res.setList(listx);
        res.setNumX(2);
        return res;
    }

    private double variance(long[] time) {
        double res;
        double sum = 0;
        for (int i = 0; i < time.length; i++) {
            sum += time[i];
        }
        double average = sum / time.length;
        double S = 0.0;
        for (int i = 0; i < time.length; i++) {
            S += (average - time[i]) * (average - time[i]);
        }
        res = S / time.length;
        return res;
    }

    static public ArrayList<Integer> getAttribute(int x) {
        int n = x;
        ArrayList<Integer> res = new ArrayList<>();
        int ind = 0;
        while (n > 0) {
            if ((n%2) == 1) {
                res.add(ind);
            }
            n = n / 2;
            ind++;
        }
        return res;
    }
}
