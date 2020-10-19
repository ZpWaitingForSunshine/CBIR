package com.njust.hsicloud.core.assist.algrithm;
//package IA;

import org.cloudbus.cloudsim.*;

import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

import com.njust.hsicloud.core.assist.dag.*;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.*;

import javax.management.MalformedObjectNameException;

/**
 * Created by Tao on 2017/11/15.
 */
public class IAMain {
    private static ArrayList<Cloudlet> cloudletList;
    private static List<Vm> vmList;

  //  public static void main(String[] args, Object iString) throws Exception{
    public static void main(String[] args) throws Exception{	
    
    	 
        LinkedHashMap<Cloudlet, Integer> result = new LinkedHashMap<>();
        int num_user = 1; // number of cloud users
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = false; // mean trace events
        // Initialize the CloudSim library
        CloudSim.init(num_user, calendar, trace_flag);
        
       
        Datacenter datacenter0 = createDatacenter("datacenter0");//create a datacenter equals ������
        IA broker = new IA("IAbroker");
        //��������
   	    Initializtion initializtion=new Initializtion();
        int brokerId = broker.getId();
  
        vmList = new ArrayList<>();
        int vmid = 0;
        int mips = 1;
        long size = 10000; // image size (MB)
        int ram = 512; // vm memory (MB)
        long bw = 1000;
        int pesNumber = 1; // number of cpus
        String vmm = "Xen"; // VMM name
        for (int i = 0; i < 8; i++) {
            // create VM
            Vm vm = new Vm(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerSpaceShared());
            // add the VM to the vmList
            vmList.add(vm);
            vmid++;
        }
        broker.submitVmList(vmList);

        cloudletList = new ArrayList<>();
        int id = 0;
        pesNumber=1;
        long fileSize = 300;
        long outputSize = 300;
        UtilizationModel utilizationModel = new UtilizationModelFull();

        /**
         * �ȳ�ʼ�����ܲ��еĽڵ�
         */
        //���г�ʼ��
//        Map<String, String> mapName=initializtion.getMap();
//        String string= mapName.get("0");
//        Cloudlet sCloudlet=string;

        
        Cloudlet root = new Cloudlet(id, 1, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        root.setUserId(brokerId);root.setName("root");

        id++;
        Cloudlet Read = new Cloudlet(id, 10500, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Read.setUserId(brokerId);Read.setName("Read");
        System.out.println("�˴���cloudlet"+Read);
        id++;
        Cloudlet Nor_Gettrain = new Cloudlet(id, 4080, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Nor_Gettrain.setUserId(brokerId);Nor_Gettrain.setName("Nor_Gettrain");

        id++;
        Cloudlet Repartition = new Cloudlet(id, 10452, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Repartition.setUserId(brokerId);Repartition.setName("Repartition");

        id++;
        Cloudlet ERS = new Cloudlet(id, 20100, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        ERS.setUserId(brokerId);ERS.setName("ERS");

        id++;
        Cloudlet ERS_mean = new Cloudlet(id, 6400, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        ERS_mean.setUserId(brokerId);ERS_mean.setName("ERS_mean");

        id++;
        Cloudlet Repart_suppix = new Cloudlet(id, 10452, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Repart_suppix.setUserId(brokerId);Repart_suppix.setName("Repart_suppix");

        id++;
        Cloudlet Nor_std_trainsub = new Cloudlet(id, 350, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Nor_std_trainsub.setUserId(brokerId);Nor_std_trainsub.setName("Nor_std_trainsub");
        id++;
        Cloudlet Nor_std_trainpix = new Cloudlet(id, 3700, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Nor_std_trainpix.setUserId(brokerId);Nor_std_trainpix.setName("Nor_std_trainpix");
        id++;
        Cloudlet Nor_std_trainsup = new Cloudlet(id, 7520, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Nor_std_trainsup.setUserId(brokerId);Nor_std_trainsup.setName("Nor_std_trainsup");

        id++;
        Cloudlet Fusion = new Cloudlet(id, 360, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Fusion.setUserId(brokerId);Fusion.setName("Fusion");

        id++;
        Cloudlet train_parofsvm = new Cloudlet(id, 44240, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        train_parofsvm.setUserId(brokerId);train_parofsvm.setName("train_parofsvm");

        id++;
        Cloudlet train_svm = new Cloudlet(id, 332, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        train_svm.setUserId(brokerId);train_svm.setName("train_svm");

        id++;
        Cloudlet makek1_train = new Cloudlet(id, 32, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        makek1_train.setUserId(brokerId);makek1_train.setName("makek1_train");

        id++;
        Cloudlet makek2_train = new Cloudlet(id, 54, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        makek2_train.setUserId(brokerId);makek2_train.setName("makek2_train");

        id++;
        Cloudlet makek3_train = new Cloudlet(id, 120, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        makek3_train.setUserId(brokerId);makek3_train.setName("makek3_train");

        id++;
        Cloudlet makek123_train = new Cloudlet(id, 10, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        makek123_train.setUserId(brokerId);makek123_train.setName("makek123_train");


        id++;
        Cloudlet tail = new Cloudlet(id, 1, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        tail.setUserId(brokerId);tail.setName("tail");

        /**
         * ��ʼ���ɲ��нڵ�
         */
        //mnf
        id++;
        Cloudlet MNF = new Cloudlet(id,29625, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        MNF.setUserId(brokerId);MNF.setName("MNF");
        MNF.initnum(vmList.size());MNF.setflag(true);
        for (int i = 0; i < vmList.size(); i++) {
            String name = "MNF_" + (i + 1) + "_";
            int[] length = {29625,20792,17634,14969,15139,14894,14823,14720};
            for (int j = 0; j <= i; j++) {
                id++;
                Cloudlet mnf = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                mnf.setUserId(brokerId);
                String name1 = name + (j+1);
                mnf.setName(name1);
                MNF.addnum(i, mnf);
            }
        }
        //k_means++
        id++;
        Cloudlet K_meanspp = new Cloudlet(id, 23155, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        K_meanspp.setUserId(brokerId);K_meanspp.setName("K_means++");K_meanspp.initnum(vmList.size());
        K_meanspp.setflag(true);
        for(int i = 0; i< vmList.size(); i++) {
            String name = "K_means++_" + (i+1) + "_";
            int[] length = {23204,23155,21490,20690,21507,21405,21039,20474};
            for(int j = 0;j <= i; j++) {
                id++;
                Cloudlet k_meanspp = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                k_meanspp.setUserId(brokerId);
                K_meanspp.addnum(i, k_meanspp);
                String name1 = name + (j+1);
                k_meanspp.setName(name1);
            }
        }
        //CEM
        id++;
        Cloudlet CEM = new Cloudlet(id, 3106, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        CEM.setUserId(brokerId);CEM.setName("CEM");CEM.initnum(vmList.size());
        CEM.setflag(true);
        for(int i = 0;i < vmList.size();i++) {
            String name = "CEM_"+(i+1)+"_";
            int[] length = {3106,1697,1176,735,758,693,639,607};
            for(int j = 0;j<=i;j++) {
                id++;
                Cloudlet cem = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                cem.setUserId(brokerId);
                CEM.addnum(i, cem);
                String name1 = name+(j+1);
                cem.setName(name1);
            }
        }
        //PCA
        id++;
        Cloudlet PCA = new Cloudlet(id, 6841, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        PCA.setUserId(brokerId);PCA.setName("PCA");PCA.initnum(vmList.size());
        PCA.setflag(true);
        for(int i = 0;i < vmList.size();i++) {
            String name = "PCA_"+(i+1)+"_";
            int[] length = {6841,3972,2742,2022,1976,1966,1895,1872};
            for(int j = 0;j<=i;j++) {
                id++;
                Cloudlet pca = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                pca.setUserId(brokerId);
                PCA.addnum(i, pca);
                String name1 = name+(j+1);
                pca.setName(name1);
            }
        }
        //corrode
        id++;
        Cloudlet Corrode = new Cloudlet(id, 48920, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Corrode.setUserId(brokerId);Corrode.setName("Corrode");Corrode.initnum(vmList.size());
        Corrode.setflag(true);
        for(int i=0;i<vmList.size();i++) {
            String name = "Corrode_"+(i+1)+"_";
            int[] length = {48920,26876,23203,16270,16129,15961,15850,15832};
            for(int j = 0;j<=i;j++) {
                id++;
                Cloudlet corrode = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                corrode.setUserId(brokerId);
                Corrode.addnum(i, corrode);
                String name1 = name+(j+1);
                corrode.setName(name1);
            }
        }
        //imreconstruct
        id++;
        Cloudlet Imreconstruct = new Cloudlet(id, 22460, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Imreconstruct.setUserId(brokerId);Imreconstruct.setName("Imreconstruct");Imreconstruct.initnum(vmList.size());
        Imreconstruct.setflag(true);
        for(int i=0;i<vmList.size();i++) {
            String name = "Imreconstruct_"+(i+1)+"_";
            int[] length = {22460,13427,11632,8142,8052,7975,7904,7901};
            for(int j = 0;j<=i;j++) {
                id++;
                Cloudlet imreconstruct = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                imreconstruct.setUserId(brokerId);
                Imreconstruct.addnum(i, imreconstruct);
                String name1 = name+(j+1);
                imreconstruct.setName(name1);
            }
        }
        //Mean_band
        id++;
        Cloudlet Mean_band = new Cloudlet(id, 785, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Mean_band.setUserId(brokerId);Mean_band.setName("Mean_band");Mean_band.initnum(vmList.size());
        Mean_band.setflag(true);
        for(int i=0;i<vmList.size();i++) {
            String name = "Mean_band_"+(i+1)+"_";
            int[] length = {885,807,570,2730,260,254,251,249};
            for(int j = 0;j<=i;j++) {
                id++;
                Cloudlet mean_band = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                mean_band.setUserId(brokerId);
                Mean_band.addnum(i, mean_band);
                String name1 = name+(j+1);
                mean_band.setName(name1);
            }
        }
        //NOR_Std_1
        id++;
        Cloudlet Nor_Std_1 = new Cloudlet(id, 848, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Nor_Std_1.setUserId(brokerId);Nor_Std_1.setName("Nor_Std_1");Nor_Std_1.initnum(vmList.size());
        Nor_Std_1.setflag(true);
        for(int i=0;i<vmList.size();i++) {
            String name = "NOR_Std_1_"+(i+1)+"_";
            int[] length = {848,759,593,546,577,576,575,491};
            for(int j = 0;j<=i;j++) {
                id++;
                Cloudlet nor_std_1 = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                nor_std_1.setUserId(brokerId);
                Nor_Std_1.addnum(i, nor_std_1);
                String name1 = name+(j+1);
                nor_std_1.setName(name1);
            }
        }
        //NOR_Std_2
        id++;
        Cloudlet Nor_Std_2 = new Cloudlet(id, 3266, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Nor_Std_2.setUserId(brokerId);Nor_Std_2.setName("Nor_Std_2");Nor_Std_2.initnum(vmList.size());
        Nor_Std_2.setflag(true);
        for(int i=0;i<vmList.size();i++) {
            String name = "NOR_Std_2_"+(i+1)+"_";
            int[] length = {3266,2235,1197,816,754,742,725,675};
            for(int j = 0;j<=i;j++) {
                id++;
                Cloudlet nor_std_2 = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                nor_std_2.setUserId(brokerId);
                Nor_Std_2.addnum(i, nor_std_2);
                String name1 = name+(j+1);
                nor_std_2.setName(name1);
            }
        }
        //NOR_Std_3
        id++;
        Cloudlet Nor_Std_3 = new Cloudlet(id, 4464, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        Nor_Std_3.setUserId(brokerId);Nor_Std_3.setName("Nor_Std_3");Nor_Std_3.initnum(vmList.size());
        Nor_Std_3.setflag(true);
        for(int i=0;i<vmList.size();i++) {
            String name = "NOR_Std_3_"+(i+1)+"_";
            int[] length = {4464,2604,1650,938,914,899,887,856};
            for(int j = 0;j<=i;j++) {
                id++;
                Cloudlet nor_std_3 = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                nor_std_3.setUserId(brokerId);
                Nor_Std_3.addnum(i, nor_std_3);
                String name1 = name+(j+1);
                nor_std_3.setName(name1);
            }
        }

        //MakeK1
        id++;
        Cloudlet MakeK1 = new Cloudlet(id, 29749, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        MakeK1.setUserId(brokerId);MakeK1.setName("MakeK1");MakeK1.initnum(vmList.size());
        MakeK1.setflag(true);
        for(int i=0;i<vmList.size();i++) {
            String name = "MakeK1_"+(i+1)+"_";
            int[] length = {29749,15202,7478,1431,1464,1394,1382,1302};
            for(int j = 0;j<=i;j++) {
                id++;
                Cloudlet makeK1 = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                makeK1.setUserId(brokerId);
                MakeK1.addnum(i, makeK1);
                String name1 = name+(j+1);
                makeK1.setName(name1);
            }
        }
        //MakeK2
        id++;
        Cloudlet MakeK2 = new Cloudlet(id, 48780, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        MakeK2.setUserId(brokerId);MakeK2.setName("MakeK2");MakeK2.initnum(vmList.size());
        MakeK2.setflag(true);
        for(int i=0;i<vmList.size();i++) {
            String name = "MakeK2_"+(i+1)+"_";
            int[] length = {48780,28990,12896,4583,4470,4346,38850,3700};
            for(int j = 0;j<=i;j++) {
                id++;
                Cloudlet makeK2 = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                makeK2.setUserId(brokerId);
                MakeK2.addnum(i, makeK2);
                String name1 = name+(j+1);
                makeK2.setName(name1);
            }
        }
        //MakeK3
        id++;
        Cloudlet MakeK3 = new Cloudlet(id, 79513, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        MakeK3.setUserId(brokerId);MakeK3.setName("MakeK3");MakeK3.initnum(vmList.size());
        MakeK3.setflag(true);
        for(int i=0;i<vmList.size();i++) {
            String name = "MakeK3_"+(i+1)+"_";
            int[] length = {79513,43588,23678,7982,7327,7202,7001,6973};
            for(int j = 0;j<=i;j++) {
                id++;
                Cloudlet makeK3 = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                makeK3.setUserId(brokerId);
                MakeK3.addnum(i, makeK3);
                String name1 = name+(j+1);
                makeK3.setName(name1);
            }
        }
        //MakeK123
        id++;
        Cloudlet MakeK123 = new Cloudlet(id, 3634, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        MakeK123.setUserId(brokerId);MakeK123.setName("MakeK123");MakeK123.initnum(vmList.size());
        MakeK123.setflag(true);
        for(int i=0;i<vmList.size();i++) {
            String name = "MakeK123_"+(i+1)+"_";
            int[] length = {3634,2834,2292,1700,1659,1572,1460,1401};
            for(int j = 0;j<=i;j++) {
                id++;
                Cloudlet makeK123 = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                makeK123.setUserId(brokerId);
                MakeK123.addnum(i, makeK123);
                String name1 = name+(j+1);
                makeK123.setName(name1);
            }
        }
        //predict
        id++;
        Cloudlet Predict = new Cloudlet(id,54330,pesNumber,fileSize,outputSize,utilizationModel,utilizationModel,utilizationModel);
        Predict.setUserId(brokerId);Predict.setName("Predict");Predict.initnum(vmList.size());
        Predict.setflag(true);
        for(int i=0;i<vmList.size();i++) {
            String name = "Predict_"+(i+1)+"_";
            int[] length = {54330,27746,14222,4183,3971,3810,3808,3785};
            for(int j = 0;j<=i;j++) {
                id++;
                //���ڵ��豸������ɢ�ģ���Ҫ������������������������ɢ�ģ�������дһ����������һ�����Բ��
                Cloudlet predict = new Cloudlet(id,length[i],pesNumber,fileSize,outputSize,utilizationModel,utilizationModel,utilizationModel);
                predict.setUserId(brokerId);
                Predict.addnum(i, predict);     //��άArrayList {{1}��{1,2}��{1,2,3}��...}
                String name1 = name+(j+1);
                predict.setName(name1);
            }
        }

        /**
         * ���Ƽ���������ӵ��б���
         */
        cloudletList.add(root);
        cloudletList.add(Read);cloudletList.add(Nor_Gettrain);cloudletList.add(Repartition);
        cloudletList.add(MNF);cloudletList.add(K_meanspp);cloudletList.add(CEM);
        cloudletList.add(PCA);cloudletList.add(Corrode);cloudletList.add(Imreconstruct);cloudletList.add(Mean_band);
        cloudletList.add(ERS);cloudletList.add(ERS_mean);cloudletList.add(Repart_suppix);
        cloudletList.add(Nor_Std_1);cloudletList.add(Nor_Std_2);cloudletList.add(Nor_Std_3);
        cloudletList.add(MakeK1);cloudletList.add(MakeK2);cloudletList.add(MakeK3);cloudletList.add(MakeK123);
        cloudletList.add(Nor_std_trainpix);cloudletList.add(Nor_std_trainsub);cloudletList.add(Nor_std_trainsup);
        cloudletList.add(makek1_train);cloudletList.add(makek2_train);cloudletList.add(makek3_train);cloudletList.add(makek123_train);
        cloudletList.add(Fusion);cloudletList.add(train_parofsvm);cloudletList.add(train_svm);cloudletList.add(Predict);
        cloudletList.add(tail);

        /**
         * ����DAGͼ
         */
        ListDGraph mDG = new ListDGraph();
        ArrayList<Edge> edgeList = new ArrayList<>();

        mDG.add(root);mDG.add(Read);mDG.add(Nor_Gettrain);mDG.add(Repartition);
        mDG.add(MNF);mDG.add(K_meanspp);mDG.add(CEM);
        mDG.add(PCA);mDG.add(Corrode);mDG.add(Imreconstruct);mDG.add(Mean_band);
        mDG.add(ERS);mDG.add(ERS_mean);mDG.add(Repart_suppix);
        mDG.add(Nor_Std_1);mDG.add(Nor_Std_2);mDG.add(Nor_Std_3);
        mDG.add(MakeK1);mDG.add(MakeK2);mDG.add(MakeK3);mDG.add(MakeK123);
        mDG.add(Nor_std_trainpix);mDG.add(Nor_std_trainsub);mDG.add(Nor_std_trainsup);
        mDG.add(makek1_train);mDG.add(makek2_train);mDG.add(makek3_train);mDG.add(makek123_train);
        mDG.add(Fusion);mDG.add(train_parofsvm);mDG.add(train_svm);mDG.add(Predict);
        mDG.add(tail);//33

        boolean[] flag = new boolean[cloudletList.size()];      //��¼�Ƿ�ɲ���
        for (int i = 0; i < flag.length; i++) {
            flag[i] = cloudletList.get(i).getflag();
        }
        //��¼�����ڵ�ĵȼ�
        int[] rank = {1,2,3,4,5,6,7,5,6,7,8,6,7,8,9,9,9,10,10,10,11,9,9,9,10,10,10,11,11,11,12,13,14};

        ListDGraph.BFSIterator bfs = mDG.new BFSIterator(root);
        //��ӱ�,����bfs���ã���ӱ�ʱ��ȷ���Ƿ��л�, ��weight is useless
 //       mDG.add(new Edge(initializtion.getMap(), Read), bfs);  
        mDG.add(new Edge(root, Read), bfs);                 edgeList.add(new Edge(root, Read));
        mDG.add(new Edge(Read, Nor_Gettrain), bfs);         edgeList.add(new Edge(Read, Nor_Gettrain));
        mDG.add(new Edge(Nor_Gettrain, Repartition), bfs);  edgeList.add(new Edge(Nor_Gettrain, Repartition));
        mDG.add(new Edge(Nor_Gettrain, ERS), bfs);          edgeList.add(new Edge(Nor_Gettrain, ERS));
        mDG.add(new Edge(Repartition, MNF), bfs);           edgeList.add(new Edge(Repartition, MNF));
        mDG.add(new Edge(MNF, K_meanspp), bfs);             edgeList.add(new Edge(MNF, K_meanspp));
        mDG.add(new Edge(K_meanspp, CEM), bfs);             edgeList.add(new Edge(K_meanspp, CEM));
        mDG.add(new Edge(Repartition, PCA), bfs);           edgeList.add(new Edge(Repartition, PCA));
        mDG.add(new Edge(PCA, Corrode), bfs);               edgeList.add(new Edge(PCA, Corrode));
        mDG.add(new Edge(PCA, ERS), bfs);                   edgeList.add(new Edge(PCA, ERS));
        mDG.add(new Edge(Corrode, Imreconstruct), bfs);     edgeList.add(new Edge(Corrode, Imreconstruct));
        mDG.add(new Edge(Imreconstruct, Mean_band), bfs);   edgeList.add(new Edge(Imreconstruct, Mean_band));
        mDG.add(new Edge(ERS, ERS_mean), bfs);              edgeList.add(new Edge(ERS, ERS_mean));
        mDG.add(new Edge(ERS_mean, Repart_suppix), bfs);    edgeList.add(new Edge(ERS_mean, Repart_suppix));

        mDG.add(new Edge(CEM, Nor_Std_1), bfs);             edgeList.add(new Edge(CEM, Nor_Std_1));
        mDG.add(new Edge(CEM, Nor_std_trainsub), bfs);      edgeList.add(new Edge(CEM, Nor_std_trainsub));
        mDG.add(new Edge(Mean_band, Nor_Std_2), bfs);       edgeList.add(new Edge(Mean_band, Nor_Std_2));
        mDG.add(new Edge(Mean_band, Nor_std_trainpix), bfs);edgeList.add(new Edge(Mean_band, Nor_std_trainpix));
        mDG.add(new Edge(Repart_suppix, Nor_Std_3), bfs);   edgeList.add(new Edge(Repart_suppix, Nor_Std_3));
        mDG.add(new Edge(Repart_suppix, Nor_std_trainsup), bfs);edgeList.add(new Edge(Repart_suppix, Nor_std_trainsup));
        mDG.add(new Edge(Nor_Std_1, MakeK1), bfs);          edgeList.add(new Edge(Nor_Std_1, MakeK1));
        mDG.add(new Edge(Nor_Std_2, MakeK2), bfs);          edgeList.add(new Edge(Nor_Std_2, MakeK2));
        mDG.add(new Edge(Nor_Std_3, MakeK3), bfs);          edgeList.add(new Edge(Nor_Std_3, MakeK3));
        mDG.add(new Edge(MakeK1, MakeK123), bfs);           edgeList.add(new Edge(MakeK1, MakeK123));
        mDG.add(new Edge(MakeK2, MakeK123), bfs);           edgeList.add(new Edge(MakeK2, MakeK123));
        mDG.add(new Edge(MakeK3, MakeK123), bfs);           edgeList.add(new Edge(MakeK3, MakeK123));
        mDG.add(new Edge(Nor_std_trainsub, makek1_train), bfs);edgeList.add(new Edge(Nor_std_trainsub, makek1_train));
        mDG.add(new Edge(Nor_std_trainpix, makek2_train), bfs);edgeList.add(new Edge(Nor_std_trainpix, makek2_train));
        mDG.add(new Edge(Nor_std_trainsup, makek3_train), bfs);edgeList.add(new Edge(Nor_std_trainsup, makek3_train));
        mDG.add(new Edge(makek1_train, makek123_train), bfs); edgeList.add(new Edge(makek1_train, makek123_train));
        mDG.add(new Edge(makek2_train, makek123_train), bfs); edgeList.add(new Edge(makek2_train, makek123_train));
        mDG.add(new Edge(makek3_train, makek123_train), bfs); edgeList.add(new Edge(makek3_train, makek123_train));
        mDG.add(new Edge(Nor_std_trainsub, Fusion), bfs);     edgeList.add(new Edge(Nor_std_trainsub, Fusion));
        mDG.add(new Edge(Nor_std_trainpix, Fusion), bfs);     edgeList.add(new Edge(Nor_std_trainpix, Fusion));
        mDG.add(new Edge(Nor_std_trainsup, Fusion), bfs);     edgeList.add(new Edge(Nor_std_trainsup, Fusion));
        mDG.add(new Edge(Fusion, train_parofsvm), bfs);       edgeList.add(new Edge(Fusion, train_parofsvm));
        mDG.add(new Edge(train_parofsvm, train_svm), bfs);    edgeList.add(new Edge(train_parofsvm, train_svm));
        mDG.add(new Edge(makek123_train, train_svm), bfs);    edgeList.add(new Edge(makek123_train, train_svm));
        mDG.add(new Edge(MakeK123, Predict), bfs);            edgeList.add(new Edge(MakeK123, Predict));
        mDG.add(new Edge(train_svm, Predict), bfs);           edgeList.add(new Edge(train_svm, Predict));
        mDG.add(new Edge(Predict, tail), bfs);                edgeList.add(new Edge(Predict, tail));


        Compriority cp = new Compriority(mDG,root,tail,cloudletList,vmList.get(0));
//        ArrayList<Cloudlet> rrr = cp.totaltime();
        LinkedHashMap<Cloudlet,Double> estmap = cp.est();   //����ÿ��cloudlet�������ʱ��,��С��������
        System.out.println("kankan zhege yinggai duoda "+estmap.size());
        ArrayList<Cloudlet> est = new ArrayList<>();
        for (Map.Entry<Cloudlet, Double> entry: estmap.entrySet()) {
            est.add(entry.getKey());
            System.out.print(entry.getKey()+"  ");
        }
        System.out.println("");

//        boolean[] flag = new boolean[cloudletList.size()];      //��¼�Ƿ�ɲ���
        for (int i = 0; i < flag.length; i++) {
            flag[i] = est.get(i).getflag();
        }

        //broker -- IA
        broker.start(vmList,est,mDG,200,200,100,200,0.95,0.2,est.size(),edgeList,flag);
        ArrayList<Population> initB = broker.init();
        ArrayList<Population> B = broker.main(initB);

        /**
         * �����д��txt�ļ�
         */
        ArrayList<Population> tmpD = broker.UpdateDomination(B,200);

        /**
         * Simulation
         */
//        broker.getstarttime();
        cloudletList.clear();
        int xxx = broker.getCtSize();
        Iterator<Map.Entry<String,Integer>> it = B.get(xxx-1).getX().entrySet().iterator();
//        Iterator<Map.Entry<String,Integer>> it = B.get(0).getX().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String,Integer> entry = it.next();
            String name = entry.getKey();
            int value = entry.getValue();
            Cloudlet tmpCloudlet = null;
            for (int i = 0; i < est.size(); i++) {          //��ȡ��Ӧ��Cloudlet
                if (est.get(i).toString().equals(name)) {
                    tmpCloudlet = est.get(i);
                }
            }
            ArrayList<Integer> indexOne = Individual.getAttribute(value);//���value��Ӧ��������ʽ1���±�λ��,

            if (tmpCloudlet.getflag()) {                    //�ɲ��е�
            	//���жȡ�������
                int parallel = indexOne.size();             //���ж�
//           	 int index=indexOne.size(); 
//           	 int[] parallellll= {1,2,4,8};
//           	 int parallel=parallellll[index-1];
                ArrayList<Cloudlet> arrCloudlet = tmpCloudlet.getlists(parallel-1);//cloudlet���У�(PCA_2_1,PCA_2_2)
                for (int i = 0; i < arrCloudlet.size(); i++) {
                    arrCloudlet.get(i).setVmId(indexOne.get(i));
//                    broker.bindCloudletToVm(arrCloudlet.get(i).getCloudletId(),indexOne.get(i));
                    arrCloudlet.get(i).setUserId(brokerId);
                    cloudletList.add(arrCloudlet.get(i));
                }
            } else {
                Cloudlet arrCloudlet = tmpCloudlet;
                arrCloudlet.setVmId(indexOne.get(0));
//                broker.bindCloudletToVm(arrCloudlet.getCloudletId(),indexOne.get(0));
                arrCloudlet.setUserId(brokerId);
                cloudletList.add(arrCloudlet);
            }
        }

//        broker.start(vmList,cloudletList,mDG,100,100,50,100,0.95,0.02,est.size(),edgeList,flag);

        broker.submitCloudletList(cloudletList);
        CloudSim.startSimulation();
        List<Cloudlet> newList = broker.getCloudletReceivedList();
        CloudSim.stopSimulation();

        printCloudletList(newList);
        datacenter0.printDebts();
        Log.printLine("CloudSimExample3 finished!");
        /**
        * /////******** Simulation test
        */



        //���ɶ����������� --- ���ڵ����ӽڵ�֮ǰ
        boolean exist;              //�жϱ任��������������ӽڵ��Ƿ��ڸ��ڵ�֮ǰ
        for (int i = 0; i < est.size(); i++) {           //est.size()
            exist = false;
            if (i == 4) {
                System.out.println("");
            }
            for (int j = i + 1; j < est.size(); j++) {
                Collections.swap(est,i,j);
                for (int m = j; m > 0; m--) {
                    for (int n = m-1;n >= 0; n--) {
                        Edge tmpEdge = new Edge(est.get(m),est.get(n));
                        for (int jj = 0; jj < edgeList.size(); jj++) {
                            Edge existEdge = edgeList.get(jj);
                            if (existEdge.equals(tmpEdge)) {
                                exist = true;
                                break;
                            }
                        }
                        if (exist)
                            break;
                    }
                    if (exist)
                        break;
                }
                if (exist) {
                    Collections.swap(est,i,j);
                    continue;
                }
                for (int k = 0; k < flag.length; k++) {
                    flag[k] = est.get(k).getflag();
                }
                broker.start(vmList,est,mDG,100,200,100,200,0.95,0.1,est.size(),edgeList,flag);
                ArrayList<Population> initB2 = broker.init();
                if (initB2.size() == 0) {
                    continue;
                }
                B = broker.main2(initB2,tmpD);
                tmpD = broker.UpdateDomination(B,200);
                Collections.swap(est,i,j);
            }
        }

        ArrayList<Population> D = broker.UpdateDomination(B,200);

        /**
         * д�ļ�
         */
        String path = "data/result.txt";
        BufferedWriter write = null;
        write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"gbk"));
        for (int k = 0; k < tmpD.size(); k++) {
            String str = tmpD.get(k).getF().get(0).toString();
            write.write(tmpD.get(k).getF().get(0).toString() + " ");
            write.write(tmpD.get(k).getF().get(1).toString() + "\r\n");
        }
        write.close();

        System.out.println("end");
    }

    private static Datacenter createDatacenter(String name) {
        // Here are the steps needed to create a PowerDatacenter:
        // 1. We need to create a list to store
        // our machine
        List<Host> hostList = new ArrayList<Host>();

        // 2. A Machine contains one or more PEs or CPUs/Cores.
        // In this example, it will have only one core.
        List<Pe> peList = new ArrayList<Pe>();

        int mips = 100000;

        // 3. Create PEs and add these into a list.
        peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating
        peList.add(new Pe(1, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating
        peList.add(new Pe(2, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating
        peList.add(new Pe(3, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating
        peList.add(new Pe(4, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating
        peList.add(new Pe(5, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating
        peList.add(new Pe(6, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating
        peList.add(new Pe(7, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

        // 4. Create Host with its id and list of PEs and add them to the list
        // of machines
        int hostId = 0;
        int ram = 20480; // host memory (MB)
        long storage = 1000000; // host storage
        int bw = 10000;

        hostList.add(
                new Host(
                        hostId,
                        new RamProvisionerSimple(ram),
                        new BwProvisionerSimple(bw),
                        storage,
                        peList,
                        new VmSchedulerSpaceShared(peList)
                )
        ); // This is our machine

        // 5. Create a DatacenterCharacteristics object that stores the
        // properties of a data center: architecture, OS, list of
        // Machines, allocation policy: time- or space-shared, time zone
        // and its price (G$/Pe time unit).
        String arch = "x86"; // system architecture
        String os = "Linux"; // operating system
        String vmm = "Xen";
        double time_zone = 10.0; // time zone this resource located
        double cost = 3.0; // the cost of using processing in this resource
        double costPerMem = 0.05; // the cost of using memory in this resource
        double costPerStorage = 0.001; // the cost of using storage in this
        // resource
        double costPerBw = 0.0; // the cost of using bw in this resource
        LinkedList<Storage> storageList = new LinkedList<Storage>(); // we are not adding SAN
        // devices by now

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem,
                costPerStorage, costPerBw);

        // 6. Finally, we need to create a PowerDatacenter object.
        Datacenter datacenter = null;
        try {
            datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datacenter;
    }

    /**
     * Prints the Cloudlet objects
     * @param list  list of Cloudlets
     */
    //��������ľ���timetable������
    private static void printCloudletList(List<Cloudlet> list) {
        int size = list.size();
        Cloudlet cloudlet;
        String indent = "    ";
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("Cloudlet ID" + indent + "STATUS" + indent +
                "Data center ID" + indent + "VM ID" + indent + "Time" + indent + "Start Time" + indent + "Finish Time");

        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++) {
            cloudlet = list.get(i);
            Log.print( cloudlet + indent + indent);

            if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS){
                Log.print("SUCCESS");

                Log.printLine( indent + indent + cloudlet.getResourceId() + indent + indent + indent + cloudlet.getVmId() +
                        indent + indent + dft.format(cloudlet.getActualCPUTime()) + indent + indent + dft.format(cloudlet.getExecStartTime())+
                        indent + indent + dft.format(cloudlet.getFinishTime()));
            }
        }
        System.out.println(size);
    }
}
