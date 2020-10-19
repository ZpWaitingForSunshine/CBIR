package com.njust.hsicloud.core.assist.dag;


import org.cloudbus.cloudsim.Cloudlet;

import java.util.*;

/**
 * Created by Tao on 2017/11/24.
 * �ڽ�����Adjacency List��ʵ�ֵ�����ͼ
 */
public class ListDGraph {
    /**
     * ������������ж�Ӧ�Ķ����Լ����Դ˶���Ϊ���ı�
     */
    public class VE {
        /**�˶���*/
        private Cloudlet v;
        /**�Դ˶���Ϊ���ıߵļ��ϣ���һ���б��б��ÿһ����һ����*/
        public List<Edge> mEdgeList;
        /**
         * ����һ���µĶ������
         * @param v
         */
        public VE(Cloudlet v) {
            this.v = v;
            this.mEdgeList = new LinkedList<Edge>();
        }

        @Override
        public String toString() {
            String ret = String.format("v : %s , list len : %s",
                    v, mEdgeList.size());
            return ret;
        }

        /**
         * ��һ������ӵ��߼�����
         * @param e
         */
        public void addEdge(Edge e) {
            //Utils.log("add edge : %s", e);
            if(getEdge(e.getDest()) == null) {
                mEdgeList.add(e);
            } else {
//                Utils.log("edge exist : %s", e);
            }
        }

        /**
         * ��ȡĳ����
         * @param dest
         * @return
         */
        public Edge getEdge(Cloudlet dest) {
            Edge ret = null;
            if(dest != null) {
                for(Edge edge : mEdgeList) {
                    if(edge.getDest() != null &&
                            dest.equals(edge.getDest())) {
                        //Utils.log("get edge : %s", edge);
                        ret = edge;
                        break;
                    }
                }
            }
            return ret;
        }

        /**
         * ��ȡĳ����
         * @param dest
         * @return
         */
        public Edge removeEdge(Cloudlet dest) {
            Edge ret = null;
            if(dest != null) {
                for(Edge edge : mEdgeList) {
                    if(edge.getDest() != null &&
                            dest.equals(edge.getDest())) {
                        //Utils.log("remove edge : %s", edge);
                        ret = edge;
                        mEdgeList.remove(edge);
                        break;
                    }
                }
            }
            return ret;
        }
    }

    /**
     * ������ȵĵ�����
     */
   public class BFSIterator {
        /**�ѷ��ʹ��Ķ����б�*/
        private List<Cloudlet> mVisitList = null;
        /**�����ʵĶ������*/
        private Queue<Cloudlet> mVQueue = null;
        private int vex_num;
        private Cloudlet root;
        /**
         * ���������ȵ�����
         * @param root
         */
        public BFSIterator(Cloudlet root) {
            mVisitList = new LinkedList<Cloudlet>();
            mVQueue = new LinkedList<Cloudlet>();

            //����ʼ�ڵ������
            this.root = root;
            vex_num = mVEList.size();
        }

        //�ж��Ƿ���ڻ�
        public boolean hasCircle() {
            mVQueue.offer(root);
            //1.ȡ����Ԫ��
            Cloudlet v = mVQueue.poll();
            HashMap<Cloudlet, Integer> map = new HashMap<>();
            map.put(v, 0);
            while(v != null) {
                VE ve = getVE(v);
                if(ve != null) {
                    List<Edge> list = ve.mEdgeList;
                    for(Edge edge : list) {
                        Cloudlet dest = edge.getDest();
                        if(!VinList(dest, mVisitList.iterator())){
                            mVQueue.offer(dest);
                            if(!map.containsKey(dest)){
                                map.put(dest, 1);
                            }else{
                                int x = map.get(dest)+1;
                                map.put(dest,x);
                                if(x > vex_num){
                                    return true;
                                }
                            }
                        }
                        // Utils.log("add to queue : " + dest);
                    }
                    v = mVQueue.poll();
                }

            }
            //4.���س����е�Ԫ��
            return false;
        }


        //������ȱ���
        public List<Cloudlet> ergodic() {
            List<Cloudlet> templist = new ArrayList<Cloudlet>();//���ؽ��
            mVQueue.offer(root);
            templist.add(root);
            //1.ȡ����Ԫ��
            Cloudlet v = mVQueue.poll();
            while(v != null) {
                //2.����Ԫ�ص��ڽӱ��ж�Ӧ��������У���Щ������Ҫ��������������
                //1)û���ʹ���
                //2)���ڶ����У�

                VE ve = getVE(v);
                if(ve != null) {
                    List<Edge> list = ve.mEdgeList;
                    for(Edge edge : list) {
                        Cloudlet dest = edge.getDest();
                        if(!VinList(dest, mVisitList.iterator()) &&
                                !VinList(dest, mVQueue.iterator())) {
                            mVQueue.offer(dest);
                        }
                    }
                }
                v = mVQueue.poll();
                if(v!=null){
                    //System.out.println("->"+v.getName());
                    templist.add(v);
                }

                //3.���˶�����ӵ��ѷ��ʹ��Ķ����б���
                mVisitList.add(v);
            }
            return templist;
        }

    }

    /**�����б����ڻᾭ�����в���ɾ����ʹ���������*/
    private LinkedList<VE> mVEList;
    private LinkedList<SUPVE> msupVEList;
    public LinkedList<VE> getmVEList(){
        return mVEList;
    }
    public LinkedList<SUPVE> getmsupVEList(){
        return msupVEList;
    }
    /**
     * �����ڽ���������ͼ
     */
    public ListDGraph() {
        mVEList = new LinkedList<VE>();
        msupVEList = new LinkedList<SUPVE>();
    }

    /**
     * ���һ���˵�
     * @param v
     * @return �����˵�ı�ţ�-1��ʾ����ʧ��
     */
    public int add(Cloudlet v) {
        int index = -1;
        if(v != null) {
            VE list = new VE(v);
            SUPVE suplist = new SUPVE(v);
            mVEList.add(list);
            msupVEList.add(suplist);
            index = mVEList.indexOf(list);
        }
        return index;
    }

    //��ӱߣ������ڻ���������˳�
    public void add(Edge e, ListDGraph.BFSIterator bfs) {
        if(e != null) {
            VE ve = getVE(e.getSource());
            //�ߵ������յ�
            Edge supe = new Edge(e.getDest(), e.getSource(), e.getWeight());
            SUPVE supve = getsupVE(e.getDest());
            if(ve != null) {
                //���ߵ�����Ѿ����б����ֱ�ӽ�����ӵ���Ӧ�Ķ��������
                ve.addEdge(e);
            } else {
                //������ʾ����
//                Utils.log("Error, can't find v : %s", e.getSource());
            }
            if(supve != null) {
                //���ߵ�����Ѿ����б����ֱ�ӽ�����ӵ���Ӧ�Ķ��������
                supve.addEdge(supe);
            } else {
                //������ʾ����
//                Utils.log("Error, can't find v : %s", e.getSource());
            }
            if(bfs.hasCircle()){
                ve.addEdge(e);
                System.out.println("���ʧ�ܣ����ڻ�");
                System.exit(0);
            }

        }
    }

    public Cloudlet remove(Cloudlet v) {
        Cloudlet ret = null;

        VE ve = removeVE(v);
        if(ve != null) {
            ret = ve.v;
        }

        removeRelateEdge(v);

        return ret;
    }

    public Edge remove(Edge e) {
        Edge ret = null;

        if(e != null) {
            VE ve = getVE(e.getSource());
            if(ve != null) {
                ret = ve.removeEdge(e.getDest());
            }
        }
        if(e != null) {
            SUPVE ve = getsupVE(e.getSource());
            if(ve != null) {
                ret = ve.removeEdge(e.getDest());
            }
        }
        return ret;
    }

    public Cloudlet get(int index) {
        Cloudlet ret = null;
        if(index >=0 && index < mVEList.size()) {
            VE ve = mVEList.get(index);
            if(ve != null) {
                ret = ve.v;
                //Utils.log("get , index : %s , v : %s", index, ret);
            }
        }
        return ret;
    }

    public Edge get(Cloudlet s, Cloudlet d) {
        Edge ret = null;
        if(s != null && d != null) {
            VE ve = getVE(s);
            if(ve != null) {
                ret = ve.getEdge(d);
            }
        }
        return ret;
    }

    public Edge get(int src, int dest) {
        Edge ret = null;
        Cloudlet s = get(src);
        Cloudlet d = get(dest);
        if(s != null && d != null) {
            VE ve = getVE(s);
            if(ve != null) {
                ret = ve.getEdge(d);
            }
        }
        return ret;
    }

    public Edge getEdge(Cloudlet a, Cloudlet b){
        Edge ret = null;
        int c = mVEList.indexOf(getVE(a));
        int d = mVEList.indexOf(getVE(b));
        ret = get(c,d);
        return ret;
    }

    //////////////////////////////˽�з���//////////////////////////////
    /**
     * �Ӷ�������б��ж�ȡ���붥���Ӧ�Ķ���
     * @param v
     * @return
     */
    public VE getVE(Cloudlet v) {
        VE ret = null;
        if(v != null) {
            for(VE ve : mVEList) {
                if(ve.v != null && v.equals(ve.v)) {
                    //Utils.log("getVE : %s", ve);
                	System.out.println("ok?");
                    ret = ve;
                    break;
                }
            }
        }
        return ret;
    }


    public SUPVE getsupVE(Cloudlet v) {
        SUPVE ret = null;
        if(v != null) {
            for(SUPVE ve : msupVEList) {
                if(ve.supv != null && v.equals(ve.supv)) {
                    //Utils.log("getVE : %s", ve);
                    ret = ve;
                    break;
                }
            }
        }
        return ret;
    }
    /**
     * �Ӷ�������б���ɾ�����붥���Ӧ�Ķ���
     * @param v
     * @return ɾ���Ķ������
     */
    private VE removeVE(Cloudlet v) {
        VE ret = null;
        if(v != null) {
            for(VE ve : mVEList) {
                if(ve.v != null && v.equals(ve.v)) {
                    //Utils.log("removeVE : %s", v);
                    ret = ve;
                    mVEList.remove(ve);
                    break;
                }
            }
        }
        return ret;
    }

    private SUPVE removeSUPVE(Cloudlet v) {
        SUPVE ret = null;
        if(v != null) {
            for(SUPVE ve : msupVEList) {
                if(ve.supv != null && v.equals(ve.supv)) {
                    //Utils.log("removeVE : %s", v);
                    ret = ve;
                    msupVEList.remove(ve);
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * ɾ����ĳ������Ϊ�ص�ı�
     * @param v
     */
    private void removeRelateEdge(Cloudlet v) {
        if(v != null) {
            for(VE ve : mVEList) {
                ve.removeEdge(v);
            }
        }
    }

    /**
     * �ж�ĳ���˵��Ƿ���ĳ���б���
     * @param v
     * @param it
     * @return
     */
     boolean VinList(Cloudlet v, Iterator<Cloudlet> it) {
        boolean ret = false;

        if(v != null && it != null) {
            while(it.hasNext()) {
                Cloudlet v_temp = it.next();
                if(v_temp != null && v_temp.equals(v)) {
                    ret = true;
                    break;
                }
            }
        }

        return ret;
    }


    public void add(Edge e) {
    	 if(e != null) {
             VE ve = getVE(e.getSource());
             //�ߵ������յ�
             Edge supe = new Edge(e.getDest(), e.getSource(), e.getWeight());
             SUPVE supve = getsupVE(e.getDest());
             if(ve != null) {
                 //���ߵ�����Ѿ����б����ֱ�ӽ�����ӵ���Ӧ�Ķ��������
                 ve.addEdge(e);
                 System.out.println("��ӳɹ�1");
             } else {
                 //������ʾ����
//                 Utils.log("Error, can't find v : %s", e.getSource());
             }
             if(supve != null) {
                 //���ߵ�����Ѿ����б����ֱ�ӽ�����ӵ���Ӧ�Ķ��������
                 supve.addEdge(supe);
                 System.out.println("��ӳɹ�2");
             } else {
                 //������ʾ����
//                 Utils.log("Error, can't find v : %s", e.getSource());
             }


         }
    }

    /**
     * �����ڲ��࣬��������˹�ϵ
     * ������������ж�Ӧ�Ķ����Լ����Դ˶���Ϊ���ı�
     */
    public class SUPVE {
        /**�˶���*/
        private Cloudlet supv;
        /**�Դ˶���Ϊ���ıߵļ��ϣ���һ���б��б��ÿһ����һ����*/
        public List<Edge> msupEdgeList;
        /**
         * ����һ���µĶ������
         * @param v
         */
         SUPVE(Cloudlet v) {
            this.supv = v;
            this.msupEdgeList = new LinkedList<Edge>();
        }

        public String toString() {
            String ret = String.format("v : %s , list len : %s",
                    supv, msupEdgeList.size());
            return ret;
        }

        /**
         * ��һ������ӵ��߼�����
         * @param e
         */
        void addEdge(Edge e) {
            //Utils.log("add edge : %s", e);
            if(getEdge(e.getDest()) == null) {
                msupEdgeList.add(e);
            } else {
//                Utils.log("edge exist : %s", e);
            }
        }

        /**
         * ��ȡĳ����
         * @param dest
         * @return
         */
         Edge getEdge(Cloudlet dest) {
            Edge ret = null;
            if(dest != null) {
                for(Edge edge : msupEdgeList) {
                    if(edge.getDest() != null &&
                            dest.equals(edge.getDest())) {
                        //Utils.log("get edge : %s", edge);
                        ret = edge;
                        break;
                    }
                }
            }
            return ret;
        }

        /**
         * ��ȡĳ����
         * @param dest
         * @return
         */
         Edge removeEdge(Cloudlet dest) {
            Edge ret = null;
            if(dest != null) {
                for(Edge edge : msupEdgeList) {
                    if(edge.getDest() != null &&
                            dest.equals(edge.getDest())) {
                        //Utils.log("remove edge : %s", edge);
                        ret = edge;
                        msupEdgeList.remove(edge);
                        break;
                    }
                }
            }
            return ret;
        }
    }
}

