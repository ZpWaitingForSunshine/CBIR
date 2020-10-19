package com.njust.hsicloud.core.assist.algrithm;

import com.njust.hsicloud.core.assist.help.*;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.core.CloudSimTags;
import org.cloudbus.cloudsim.lists.VmList;

import com.njust.hsicloud.core.assist.dag.*;

import java.util.*;
/**
 * Created by Tao on 2017/11/17.
 */
public class IA extends DatacenterBroker{
    private List<Vm> vmList = new ArrayList<>();            //������б�
    private int vmSize;
    private List<Cloudlet> cloudletList = new ArrayList<>();//�Ƽ��������б�
    private ListDGraph mDG;
    private int tasknum;            //��������
    private int workernum;          //�����ڵ�����
    private int Gmax;               //����������
    private int nd;                 //maximum size of dominant population
    private int na;                 //maximum size of active population
    private int nc;                 //size of clone population
    private double cross_ratio;     //������
    private double mutate_ratio;    //������
    private ArrayList<Edge> edgeList;
    private boolean[] flag;
    private int CtSize;
    ArrayList<Population> B = new ArrayList<Population>();     //active population
    public IA(String name) throws Exception {
        super(name);
    }

    public int getCtSize() {
        return this.CtSize;
    }

    public void start(List<Vm> vmList, ArrayList<Cloudlet> cloudletList, ListDGraph mDG,
                      int Gmax,int nd,int na,int nc,double cross_ratio,double mutate_ratio,
                      int tasknum,ArrayList<Edge> edgeList,boolean[] flag){
        this.vmList = vmList;
        this.vmSize = vmList.size();
        this.cloudletList = cloudletList;
        this.mDG = mDG;
        this.Gmax = Gmax;
        this.nd = nd;
        this.na = na;
        this.nc = nc;
        this.cross_ratio = cross_ratio;
        this.mutate_ratio = mutate_ratio;
        this.edgeList = edgeList;
        this.tasknum = tasknum;
        this.flag = flag;
    }

    /**
     * ��Ⱥ��ʼ��
     */
    public ArrayList<Population> init() {
        B.clear();
//        ArrayList<Population> B = new ArrayList<Population>();
        Individual indiv = new Individual(vmSize, cloudletList, mDG, edgeList);
        Random random = new Random();
        int[] rand = {8,8,4,1,191,121,176,64,4,12,32,168,4,67,32,
                      167,200,38,2,64,187,227,150,2,191,2,128,16,64,4,126,230,2};
        for (int i = 0; i < nd; i++) {                                  //nd
            ArrayList<Integer> in = new ArrayList<Integer>();                //����
            for (int j = 0; j < tasknum; j++) {
                if (cloudletList.get(j).getflag()) {                         //�ɲ���
                    int maxGene = (int)Math.pow(2,vmSize);                   //�ö��������б�ʾVMʹ�����
                    int taskGene = random.nextInt(maxGene - 1) + 1;   //����task�Ļ���   [1,2,...,255]
                    in.add(taskGene);
                } else {
                    int taskGene = random.nextInt(vmSize);                   //[0,8)
                    in.add((int)Math.pow(2,taskGene));
                }
//                in.add(rand[j]);
            }
            Population tmp = indiv.fun(in);
            B.add(tmp);
        }
        return B;
    }

    public ArrayList<Population> main(ArrayList<Population> B) {
        for (int i = 0; i < Gmax; i++) {                        //Gmax
            System.out.println(i);
            crowDistance(B);                                     //cal every individual's crowd distance
            ArrayList<Population> D = UpdateDomination(B,nd);    //nd < B.size()
            ArrayList<Population> A = ActiveSelection(D, na);
            ArrayList<Population> C = Clone(A, nc);
            ArrayList<Population> CT = CrossOver(C, A, flag);
            ArrayList<Population> Ct = Mutate(CT, mutate_ratio,vmSize, cloudletList, mDG, edgeList, flag);
            this.CtSize = Ct.size();
            Ct.addAll(D);
            B = Ct;
        }
        ArrayList<Population> D = UpdateDomination(B,nd);

//        ArrayList<Double> x = new ArrayList<Double>();
//        ArrayList<Double> y = new ArrayList<Double>();
//        for (int i = 0; i < D.size(); i++) {
//            x.add(D.get(i).getF().get(0));
//            y.add(D.get(i).getF().get(1));
//        }
        System.out.println("********main end********");
        return B;
    }

    public ArrayList<Population> main2(ArrayList<Population> initB2, ArrayList<Population> tmpD) {
        for (int i = 0; i < Gmax; i++) {                        //Gmax
            System.out.println(i);
            if (initB2.size() == 0) {
                System.out.println();
            }
            crowDistance(initB2);                                     //cal every individual's crowd distance
            ArrayList<Population> D = UpdateDomination(initB2,nd);    //nd < B.size()
            ArrayList<Population> A = ActiveSelection(D, na);
            ArrayList<Population> C = Clone(A, nc);
            ArrayList<Population> CT = CrossOver(C, A, flag);
            ArrayList<Population> Ct = Mutate(CT, mutate_ratio,vmSize, cloudletList, mDG, edgeList, flag);
            this.CtSize = Ct.size();
            Ct.addAll(D);
            initB2 = Ct;
        }
        ArrayList<Population> D = UpdateDomination(initB2,nd);//D - ��ǰ����dominate   tmpD - ǰ��������dominate
        //����ǰD���뵽tmpD��
        D = DAddtmpD(D,tmpD);

        System.out.println("********main end********");
        return D;
    }

    /**
     * ��D�������֧��tmpD�Ľ����tmpD��
     * @param D
     * @param tmpD
     * @return
     */
    private ArrayList<Population> DAddtmpD(ArrayList<Population> D, ArrayList<Population> tmpD) {
//        ArrayList<Population> res = new ArrayList<Population>();
        for (int i = 0; i < D.size(); i++) {
            System.out.println("    " + i + "    ");
            Population DPop = D.get(i);
            boolean flag = true;
            for (int j = 0; j < tmpD.size(); j++) {
                Population tmpDPop = tmpD.get(j);
                int res = Dominant(DPop, tmpDPop); //if flag = 3   �����
                if (res != 3) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                tmpD.add(DPop);
            }
        }

        return tmpD;
    }

    /**
     * ����
     * @param T: ����任�����Ⱥ
     * @param pm
     * @return
     */
    private ArrayList<Population> Mutate(ArrayList<Population> T, double pm,
                                                int VMsize, List<Cloudlet> cloudletList,
                                                ListDGraph mDG,ArrayList<Edge> edgeList, boolean[] flag) {
        ArrayList<Population> R = new ArrayList<Population>();
        Random rand = new Random();
//        boolean[] flag = {false,false,false,false,true,true,true,true,true,true,true,
//                false,false,false,true,true,true,true,true,true,true,
//                false,false,false,false,false,false,false,false,false,false,true,false};
//        boolean[] flag = {false,false,true,true,true,false};
        for (int i = 0; i < T.size(); i++) {
            if (Math.random() < pm) {
                ArrayList<Integer> listX = new ArrayList<>();
                int test = T.get(i).getX().size();

                LinkedHashMap<String,Integer> getX = T.get(i).getX();
                Iterator<Map.Entry<String,Integer>> iterX = getX.entrySet().iterator();
                while (iterX.hasNext()) {
                    listX.add(iterX.next().getValue());
                }

//                for (int k = 0; k < T.get(i).getX().size(); k++) {
//                    listX.add(T.get(i).getX().get(k));
//                }

                ArrayList<String> str = IntergerToString(listX);
                StringBuilder strc = new StringBuilder();       //������task���ַ�����ʽƴ��
                for (int k = 0; k < str.size(); k++) {
                    strc.append(str.get(k));
                }

                String tempStr = "";
                int r = 0;
                while (true) {
                    r = rand.nextInt(strc.length() - 1);
                    int quatient = r / vmSize;
                    if (flag[quatient])
                        break;
                }

                if (strc.charAt(r) == '0') {
                    tempStr = strc.substring(0,r) + "1" + strc.substring(r+1);
                } else {
                    boolean tempFalg = false;
                    tempStr = strc.substring(0,r) + "0" + strc.substring(r+1);
                    int quatient = r / vmSize;                           //��������
                    for (int k = 0; k < vmSize; k++) {
                        if (tempStr.charAt(vmSize*quatient+k) == '1') {
                            tempFalg = true;
                            break;
                        }
                    }
                    if (!tempFalg)          //if tempStr ĳ���ɲ���������ȫ��0 ����ִ��mutate
                        tempStr = strc.substring(0,r) + "1" + strc.substring(r+1);
                }

                ArrayList<Integer> num = new ArrayList<Integer>();
                for (int k = 0; k < T.get(i).getX().size(); k++) {
                    int numTmp = StringToInteger(tempStr.substring(k*vmSize,(k+1)*vmSize));
                    num.add(numTmp);
                }

                Individual ind = new Individual(vmSize, cloudletList, mDG, edgeList);
                Population p = ind.fun(num);
                R.add(p);
            } else {
                R.add(T.get(i));
            }
        }
        return R;
    }

    /**
     * ����
     * @param C: the population after clone
     * @param A; the active selection population
     * @return
     */
    private ArrayList<Population> CrossOver(ArrayList<Population> C, ArrayList<Population> A, boolean[] flag) {
        ArrayList<Population> T = new ArrayList<Population>();
        Random rand = new Random();
//        boolean[] flag = {false,false,false,false,true,true,true,true,true,true,true,
//                false,false,false,true,true,true,true,true,true,true,
//                false,false,false,false,false,false,false,false,false,false,true,false};
//        boolean[] flag = {false,false,true,true,true,false};    //test
        for (int i = 0; i < C.size(); i++) {
            int r = rand.nextInt(A.size());                       //��active��Ⱥ�����ѡ��һ��population
//            int r = (int)(Math.random() * (A.size()));          //��  -1
            Population a = A.get(r);
//            ArrayList<Integer> Xa = C.get(i).getX();
//            ArrayList<Integer> Xb = a.getX();
            LinkedHashMap<String,Integer> Xa = C.get(i).getX();
            LinkedHashMap<String,Integer> Xb = a.getX();
            ArrayList<Integer> xtemp = new ArrayList<>();        //��Ž������֮��Ľ��

            Iterator<Map.Entry<String,Integer>> iterClone = Xa.entrySet().iterator();
            Iterator<Map.Entry<String,Integer>> iterActive = Xb.entrySet().iterator();
            while (iterActive.hasNext() && iterClone.hasNext()) {
                int j = 0;
                Map.Entry<String,Integer> getClone = iterClone.next();
                Map.Entry<String,Integer> getActive = iterActive.next();
                String keyClone = getClone.getKey();
                Integer valueClone = getClone.getValue();
                String keyActive = getActive.getKey();
                Integer valueActive = getActive.getValue();
                if (!flag[j]) {             //����ǰ�ڵ�ǲ��У������������
                    xtemp.add(valueClone);
                    continue;
                }
                ArrayList<String> str = IntergerToString(valueClone,valueActive);
                int R = rand.nextInt(str.get(0).length());
//                int R = (int)(Math.random() * (str.get(0).length()));   // ��  -1
                String temp = str.get(0).substring(0,R) + str.get(1).substring(R);
                int x = StringToInteger(temp);
                if (x == 0) {           //x == 0 --- ������VM
                    xtemp.add(valueClone);
                    continue;
                }
                xtemp.add(StringToInteger(temp));
                j++;
            }

            Individual ind = new Individual(vmSize, cloudletList, mDG, edgeList);
            Population p = ind.fun(xtemp);
            T.add(p);
        }
        return T;
    }
    /**
     * proportional clone operation
     * @param A: active population
     * @param Nc: size of clone population
     * @return
     */
    private static ArrayList<Population> Clone(ArrayList<Population> A, int Nc) {
        ArrayList<Population> c = new ArrayList<Population>();
        double num = 0.0;
        int k = 0;
        ComparatorCrowdReverse comparator = new ComparatorCrowdReverse();   //��������
        Collections.sort(A, comparator);
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).getCrowd() != Double.POSITIVE_INFINITY) {
                k = i;
                break;
            }
        }
        for (int j = 0; j < k; j++) {
            A.get(j).setCrowd(A.get(k).getCrowd() * 2);
        }
        for (int i = 0; i < A.size(); i++) {
            num += A.get(i).getCrowd();
        }
        //copy by proportion
        for (int i = 0; i < A.size(); i++) {
            int l = (int)(A.get(i).getCrowd() * Nc / num);
            for (int j = 0; j < l; j++) {
                c.add(A.get(i));
            }
        }
        return c;
    }
    /**
     * ������Ⱥ��ɾ��ӵ���Ⱦ���ֵС�ĸ���
     * @param D: domination
     * @param na: maximum size of active population
     * @return
     */
    private static ArrayList<Population> ActiveSelection(ArrayList<Population> D, int na) {
        ArrayList<Population> res = new ArrayList<Population>();
        if (D.size() <= na) {
            return D;
        } else {
            ComparatorCrowdReverse comparator = new ComparatorCrowdReverse();   //��������
            Collections.sort(D, comparator);
            for (int i = 0; i < na; i++) {
                res.add(D.get(i));
            }
            return res;
        }
    }
    /**
     ��������ӵ������
     */
    private static void crowDistance(ArrayList<Population> B) {
        int n = B.size();                   //the number of population
//        System.out.println("B.size():" + B.size());
        for (int i = 0; i < B.get(0).getF().size(); i++) {
            if (i == 0) {                   //����Population����������f1��f2��������
                ComparatorF1 comparator = new ComparatorF1();   //����Ŀ�꺯��1��������
                Collections.sort(B,comparator);
            } else {
                ComparatorF2 comparator = new ComparatorF2();
                Collections.sort(B,comparator);
            }
            B.get(0).setCrowd(Double.POSITIVE_INFINITY);
            B.get(n-1).setCrowd(Double.POSITIVE_INFINITY);
            double h = B.get(n-1).getF().get(i) - B.get(0).getF().get(i);
            for (int j = 1; j < n-1; j++) {
                double tmpCrowd = B.get(j).getCrowd();
                double tmp = (B.get(j+1).getF().get(i) - B.get(j-1).getF().get(i)) / h;
                B.get(j).setCrowd(tmpCrowd + tmp);
            }
        }
    }
    /**
     *input: B -- pre updated population    Nd -- maximum number of domiantion population
     * output: dominated solution
     */
    public ArrayList<Population> UpdateDomination(ArrayList<Population> B, int Nd) {
        int n = B.size();
        ArrayList<Population> DT = new ArrayList<Population>();
        ArrayList<Population> res = new ArrayList<Population>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
//                Boolean dominant = Dominant(B.get(i),B.get(j));
                int dominant = Dominant(B.get(i),B.get(j));
                if (dominant == 1) {                        //whether B.get(i) is dominated B.get(j)
                    B.get(i).setD(B.get(i).getD() + 1);     //how many individual it dominates
                    B.get(j).setBd(B.get(j).getBd() + 1);   //how many individuals dominate it
                }
                if (dominant == 2) {                        //whether B.get(i) is dominated B.get(j)
                    B.get(j).setD(B.get(j).getD() + 1);     //how many individual it dominates
                    B.get(i).setBd(B.get(i).getBd() + 1);   //how many individuals dominate it
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (B.get(i).getBd() == 0) {                //not dominated by any other individual
                DT.add(B.get(i));
            }
        }
        if (DT.size() <= Nd) {
            return DT;
        } else {
            ComparatorCrowdReverse comparator = new ComparatorCrowdReverse();   //��������
            Collections.sort(DT, comparator);
            for (int i = 0; i < Nd; i++) {
                res.add(DT.get(i));
            }
            return res;
        }
    }
    /**
     * cal x dominated y or not
     * @param x:Populationi
     * @param y:Population
     * @return 1: x dominate y
     *         2: y dominate x
     */
    private static int Dominant(Population x, Population y) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < x.getF().size(); i++) {
            if (x.getF().get(i) >= y.getF().get(i)) {
                right++;//
//                return false;
            }
            if (x.getF().get(i) <= y.getF().get(i)) {//
                left++;//
            }//
        }
//        return true;
        if (left == 2)//
            return 1;//
        else if (right == 2)//
            return 2;//
        else return 3;//
    }

    /**
     * binary string to integer
     * @param cadena
     * @return
     */
    private static int StringToInteger(String cadena) {
        int decimal = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == '1') {
                decimal = decimal + (int)Math.pow(2,cadena.length() - 1 - i);
            }
        }
        return decimal;
    }

    /**
     * int ת �������ַ���
     * @param numero
     * @param numero2
     * @return
     */
    private static ArrayList<String> IntergerToString(int numero, int numero2) {
        ArrayList<String> cadenas = new ArrayList<String>();
        cadenas.add(Integer.toBinaryString(numero));
        cadenas.add(Integer.toBinaryString(numero2));
        if (cadenas.get(0).length() > cadenas.get(1).length()) {
            StringBuilder tmp = new StringBuilder();
            int len = cadenas.get(0).length() - cadenas.get(1).length();
            for (int i = 0; i < len; i++) {
                tmp.append("0");
            }
            tmp.append(cadenas.get(1));
            cadenas.set(1,tmp.toString());
        } else {
            StringBuilder tmp = new StringBuilder();
            int len = cadenas.get(1).length() - cadenas.get(0).length();
            for (int i = 0; i < len; i++) {
                tmp.append("0");
            }
            tmp.append(cadenas.get(0));
            cadenas.set(0,tmp.toString());
        }
        return cadenas;
    }

    private ArrayList<String> IntergerToString(ArrayList<Integer> listX) {
        ArrayList<String> cadenas = new ArrayList<String>();
        for (int k = 0; k < listX.size(); k++) {
            cadenas.add(Integer.toBinaryString(listX.get(k)));
            StringBuilder tmp = new StringBuilder();
            int len = vmSize - cadenas.get(k).length();
            for (int i = 0; i < len; i++) {
                tmp.append("0");
            }
            tmp.append(cadenas.get(k));
            cadenas.set(k,tmp.toString());
        }
        return cadenas;
    }

    /**
     * ��дsubmitCloudlets()����
     * ʹ�õ����������ύ���
     */
    @Override
    protected void submitCloudlets() {
        int vmIndex = 0;
        for (Cloudlet cloudlet : getCloudletList()) {
            Vm vm;
            double delay = cloudlet.getExecStartTime();
            System.out.println("delay:"+delay);
            // if user didn't bind this cloudlet and it has not been executed yet
            if (cloudlet.getVmId() == -1) {
                vm = getVmsCreatedList().get(vmIndex);
            } else { // submit to the specific vm
                vm = VmList.getById(getVmsCreatedList(), cloudlet.getVmId());
                if (vm == null) { // vm was not created
                    continue;
                }
            }

            cloudlet.setVmId(vm.getId());
            schedule(getVmsToDatacentersMap().get(vm.getId()),delay , CloudSimTags.CLOUDLET_SUBMIT, cloudlet);
            cloudletsSubmitted++;
            vmIndex = (vmIndex + 1) % getVmsCreatedList().size();
            getCloudletSubmittedList().add(cloudlet);
        }
        // remove submitted cloudlets from waiting list
        for (Cloudlet cloudlet : getCloudletSubmittedList()) {
            getCloudletList().remove(cloudlet);
        }
    }
}
