package com.njust.hsicloud.core.assist.algrithm;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerSpaceShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerSpaceShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.njust.hsicloud.core.assist.dag.*;

public class testMain {

	private static ArrayList<Cloudlet> cloudletList;
    private static List<Vm> vmList;
//    public static void main(String[] args) throws Exception{
    public static String  helper() throws Exception{

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
        //�˴���VM�ĸ���ҲӦ���Ǵ�xml���ж�������
        for (int i = 0; i < 4; i++) {
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


        
        //ToChange
        /**
         * ��ʼ���ڵ�
         */
        /**
         * ����DAGͼ
         */
        ListDGraph mDG = new ListDGraph();
        ArrayList<Edge> edgeList = new ArrayList<>();
        //���г�ʼ��
        //��Ϊ���һ��root��һ��Tail
		Cloudlet root = new Cloudlet(id, 1, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		root.setUserId(brokerId);root.setName("root");
		cloudletList.add(root);
		mDG.add(root);
		id++;
		Cloudlet tail = new Cloudlet(id, 1, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		tail.setUserId(brokerId);tail.setName("tail");
		cloudletList.add(tail);
		mDG.add(tail);
		//�洢���е�cloudlet
		//��ԭ������õ�cloudletList
        ArrayList<Cloudlet> cloudletArrayList = new ArrayList<Cloudlet> ();
        //HashMap �洢��Ӧ��nodeid��cloudlet
        HashMap<String , Cloudlet>  cloudletHM = new  HashMap<String , Cloudlet>();
    	int countup=0;//���ɲ��еĽڵ���
    	int countp=0;//�ɲ��еĽڵ���
        for (Iterator<String> iterator=initializtion.NodeHT1.keySet().iterator();iterator.hasNext(); ) {
        	
    //    	long[] length = Dag.arrayLength;
        	
        	String key=iterator.next();
        	System.out.println("�������key�Ǹ�ɶ"+key);
        	Node n1=initializtion.NodeHT1.get(key);
        	String path = n1.getPath();
        	String Nname = n1.getName();
            //System.out.println(Nname+ "aaa   "+ path);
        	Dag dag= new Dag(path);
        	long[] length=dag.arrayLength;
        	Node n=dag.NodeHT.get(key);
        	String flag=dag.NodeHT.get(Nname).getFlag();
         	//System.out.println("�������flag�Ǹ�ɶ"+flag);
        	String nodeid = String.valueOf(initializtion.idfilter.get(Integer.parseInt(n1.getID())));
            //String nodeid = n1.getID();
        	String nodename=n1.getName();
        	//System.out.println(nodename+"  AAA"+ nodeid);
 //       	System.out.println("��ӡ����ڵ��Ƿ���ٷ�"+n.getFlag());
        	if(flag.equals("flase")) {
        		//���ɲ��еĽڵ����
        		countup++;
//        		System.out.println("���ɲ��еĽڵ����"+countup);
        		//�ȳ�ʼ�����ܲ��еĽڵ㣬�������нڵ����Cloudlist����       		
        		//ToChange
        		id++;
        		//���cloudlet�����֣���
 //       		System.out.println(length[0]);
        		Cloudlet c = new Cloudlet(id, length[0], pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
        		
        		c.setUserId(brokerId);c.setName(nodename);
        		//System.out.println(brokerId+" aa "+nodename);
        		//�˴���c
 //       		System.out.println("������cloudlet(���ܲ��У�Ӧ��������)"+c);
        		//�洢���е�cloudlet
        		cloudletArrayList.add(c);
        		cloudletList.add(c);
        		
        		//�洢nodeid��cloudletһһ��Ӧ
        		cloudletHM.put(nodeid, c);
        		mDG.add(c);
        		
        	}
        	else {
        		//ToChange
        		//��ʼ�����Բ��еĽڵ�
                //mnf
              id++;
              countp++;
//          	System.out.println("�ɲ��еĽڵ����"+countp);
            
              Cloudlet c1 = new Cloudlet(id,length[0], pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
            
              c1.setUserId(brokerId);c1.setName(nodename);
              //System.out.println(brokerId+" aa 123 "+nodename);
 //             System.out.println("�˴���c1�����Բ��У�Ӧ����5��"+c1);
              c1.initnum(vmList.size());c1.setflag(true);
//              for (int i = 0; i < vmList.size(); i++) {
              for (int i = 0; i < length.length; i++) {
                  String name = nodename+"_" + (i + 1) + "����";
                  //����n.getNodeDuration()ȡ��ִ��ʱ��
 //                 int[] length = {29625,20792,17634,14969,15139,14894,14823,14720};
            
                  for (int j = 0; j <= i; j++) {
                      id++;
                      Cloudlet mnf = new Cloudlet(id, length[i] , pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                      mnf.setUserId(brokerId);
                      String name1 = name + (j+1);
                      mnf.setName(name1);
                      c1.addnum(i, mnf);

                  }
              }
              //�洢���е�cloudlet
        		cloudletArrayList.add(c1);
        		cloudletList.add(c1);
        		//�洢nodeid��cloudletһһ��Ӧ
        		cloudletHM.put(nodeid, c1);
        		System.out.println(mDG.add(c1));
        		
        	}
     	
        }
//      //�����õ�cloudletHM
//        for (Entry<String, Cloudlet> entry : cloudletHM.entrySet()) {
//        	System.out.println("testclouldlet");
//        	System.out.println(entry.getKey());
//        	System.out.println(entry.getValue());
//        	System.out.println("--------------------");
//        	}
        


//       System.out.println("��ʼ�����");
//       System.out.println("----------------------");
        boolean[] flag = new boolean[cloudletList.size()];     //��¼�Ƿ�ɲ���
        for (int i = 0; i < flag.length; i++) {
            flag[i] = cloudletList.get(i).getflag();
            //System.out.println("�������flag"+ flag[i]);
        }
        //��¼�����ڵ�ĵȼ�
//        int[] rank = {1,2,3,4,5,6,7,5,6,7,8,6,7,8,9,9,9,10,10,10,11,9,9,9,10,10,10,11,11,11,12,13,14};
//        int sum=countup +countp;
 //       ListDGraph.BFSIterator bfs = mDG.new BFSIterator(root);
        //��ӱ�,����bfs���ã���ӱ�ʱ��ȷ���Ƿ��л�, ��weight is useless
//        System.out.println("��ӵ�һ����");
//        System.out.println("-----------------");      
        //�ӵ�һ����
      firstoutside:  for(int i=0;i<initializtion.matrix.length;i++) {
       	firstinside: for(int j=0;j<initializtion.matrix[i].length;j++) {
       		if(initializtion.matrix[i][j]==1) {
//       			System.out.println(i);
//       			System.out.println(j);
       			String prenode =String.valueOf(i);
       			Cloudlet c=cloudletHM.get(prenode);
       			
 //   			System.out.println("��õ�һ������"+cloudletHM.get(prenode));
       			Edge e= new Edge(root, cloudletHM.get(prenode)); 
       		    edgeList.add(e);
       		    mDG.add(e);  
//       		    ���о�ϲ�𣿣���������
       		    System.out.println("waiting for surprise");
      	     System.out.println(mDG.getVE(root));
       		 break firstoutside;
       		}
       		
       	   }
       	 }
//        System.out.println("��ӵ�һ�������");
//        System.out.println("-----------------");
        //��ӱߣ�ԭ��ֻ���ĺ����ڵ㣩
        for(int i=0;i<initializtion.matrix.length;i++) {
        	 for(int j=0;j<initializtion.matrix[i].length;j++) {
        		 if(initializtion.matrix[i][j]==1) {
        			 //ǰ��ڵ��Լ� �����ڵ��id
        			 String prenode =String.valueOf(i);
        			 String sunode =String.valueOf(j);
        			 //System.out.println(cloudletHM.get(prenode));
        			 //System.out.println(cloudletHM.get(sunode));
        			 Edge e= new Edge(cloudletHM.get(prenode), cloudletHM.get(sunode));
 //       			 System.out.println("�������զ������");
        			 edgeList.add(e);
 //       			 System.out.println("�������в�");
        			 
//        			 mDG.add(e, bfs);  
        			 mDG.add(e);
 //       			 System.out.println("hhhhhhhhhhhhhhh");
        			
        		 }
        	 }
        	
        }
//        System.out.println("��ӱ�");
 //       System.out.println("-----------------");
        //�����һ����
      lastoutside:  for(int i=initializtion.matrix.length-1;i>0;i--) {
       	lastinside: for(int j=initializtion.matrix[i].length-1;j>0;j--) {
       		if(initializtion.matrix[i][j]==1) {
       			String sunode =String.valueOf(i);
       		
       			Edge e= new Edge( cloudletHM.get(sunode),tail);
       		    edgeList.add(e);
//       		    mDG.add(new Edge(cloudletHM.get(sunode),tail), bfs); 
       		   mDG.add(e); 
       		break lastoutside; 
       		}
       	 
       	   }
       	 }


//        System.out.println("������һ�������");
         System.out.println("-----------------");
         
//        System.out.println(""+mDG);
        
        Compriority cp = new Compriority(mDG,root,tail,cloudletList,vmList.get(0));
        //�������ɵ�cloudletList
//        for(int i=0;i<cloudletList.size();i++) {
//        	System.out.println("�������ɵ�cloudletList"+cloudletList.get(i));
//        }
//        ArrayList<Cloudlet> rrr = cp.totaltime();
        LinkedHashMap<Cloudlet,Double> estmap = cp.est();   //����ÿ��cloudlet�������ʱ��,��С��������
        System.out.println("estmap�Ĵ�С"+estmap.size());
        System.out.println("----------------");
        ArrayList<Cloudlet> est = new ArrayList<>();
        for (Entry<Cloudlet, Double> entry: estmap.entrySet()) {
            est.add(entry.getKey());
            System.out.println("tianjiachenggong ??????");
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
        Iterator<Entry<String, Integer>> it;
        if (xxx > 0) {
            it = B.get(xxx - 1).getX().entrySet().iterator();
        }
        else {
            it = B.get(0).getX().entrySet().iterator();
        }
        //Iterator<Map.Entry<String, Integer>> it = B.get(xxx - 1).getX().entrySet().iterator();

//        Iterator<Map.Entry<String,Integer>> it = B.get(0).getX().entrySet().iterator();
        while (it.hasNext()) {
            Entry<String,Integer> entry = it.next();
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
            	//�˴��Ĳ��ж������⣬��һ�����飬�ĳ�����ķ�ʽ
               int parallel = indexOne.size();             //���ж�
//            	 int index=indexOne.size(); 
//            	 int[] parallellll= {1,2,4,8};
//            	 int parallel=parallellll[index-1];
                System.out.println("---------------------���ж�---------------------------------------");
                System.out.println(parallel);
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
        //д��xml
        writeToXml(newList);
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
        String path = "/home/hadoop/Desktop/quick4j-master 2/src/main/java/com/eliteams/quick4j/core/assist/data/"+System.currentTimeMillis()+".xml" ;
        BufferedWriter write = null;
        write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"gbk"));
        for (int k = 0; k < tmpD.size(); k++) {
            String str = tmpD.get(k).getF().get(0).toString();
            write.write(tmpD.get(k).getF().get(0).toString() + " ");
            write.write(tmpD.get(k).getF().get(1).toString() + "\r\n");
        }
        write.close();

        System.out.println("end");
        return path;
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
    	
    
    private  static void writeToXml(List<Cloudlet> list) throws IOException {
    	 int size = list.size();
         Cloudlet cloudlet;
        //1.����һ��Document����		
         Document doc = DocumentHelper.createDocument(); 		
         //2.����������		
         Element root = doc.addElement("result"); 		
         //3.��result���洴��һ���ӽڵ�	DagInfo
         Element DagInfo = root.addElement("DagInfo"); 	
         Element dagname = DagInfo.addElement("dagname"); 	
         Element VMnum = DagInfo.addElement("VMnum");
         Element ImageId  = DagInfo.addElement("ImageId");
         Element object = DagInfo.addElement("object");
         //4.�����ӽڵ���ı�	
         dagname.setText(Initializtion.dagname);
         VMnum.setText(String.valueOf(Initializtion.VMnum));
         ImageId.setText(Initializtion.ImageId);
         object.setText(Initializtion.object);
         
         Element timetable = root.addElement("timetable");
         for(int i=0;i<size;i++) {
        	 cloudlet=list.get(i);
        	 Element TaskInfo = DagInfo.addElement("TaskInfo"); 
        	  Element CloudletID = TaskInfo.addElement("CloudletID");     
        	  String cloudletid =cloudlet.toString();
        	  Element par = TaskInfo.addElement("par");
        	  if (cloudletid.contains("_")){
        		  CloudletID.setText(cloudletid.substring(0,cloudletid.indexOf("_") ));
        		  String p=cloudletid.substring(cloudletid.indexOf("_")+1, cloudletid.indexOf("����"));
        		  par.setText(p);
        	  }else {
        		  CloudletID.setText(cloudletid);
        		  par.setText("1");
        	  }
        	 
//        	  Element Flag = TaskInfo.addElement("Flag");
        	  //���ж�
        	 	 
        	 
        	 
        	  Element VMID = TaskInfo.addElement("VMID");
        	  VMID.setText(String.valueOf(cloudlet.getVmId()));
        	  Element Time = TaskInfo.addElement("Time");
        	  Time.setText(String.valueOf(cloudlet.getActualCPUTime()));
        	  Element StartTime = TaskInfo.addElement("StartTime");
        	  StartTime.setText(String.valueOf(cloudlet.getExecStartTime()));
        	  Element FinishTime = TaskInfo.addElement("FinishTime");
        	  FinishTime.setText(String.valueOf(cloudlet.getFinishTime()));
         }
	
        	
         //Format��ʽ�����ʽˢ		
         OutputFormat format = OutputFormat.createPrettyPrint();	
         //����xml����		
         format.setEncoding("utf-8"); 	
         Writer out;
         try {
        	 //����һ�����������
        	 out = new FileWriter("/home/hadoop/Desktop/quick4j-master 2/src/main/java/com/eliteams/quick4j/core/assist/data/"+System.currentTimeMillis()+".xml");
        	//����һ��dom4j����xml�Ķ��� 
        	 XMLWriter writer = new XMLWriter(out, format);
        	//����write������doc�ĵ�д��ָ��·��
        	 writer.write(doc);
        	 writer.close();
        	 System.out.print("����XML�ļ��ɹ�");
         } catch (IOException e) {
        	 System.out.print("����XML�ļ�ʧ��");
        	 e.printStackTrace();
         }
        
	
 
    }
}

