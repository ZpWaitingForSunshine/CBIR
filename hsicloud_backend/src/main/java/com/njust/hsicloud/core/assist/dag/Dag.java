package com.njust.hsicloud.core.assist.dag;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Dag {
	public static String nodeid;
	public String name;
	public String flag;
//	private ArrayList<String> NodeList;
	public static Hashtable<String,Node> NodeHT;
	public String dagDataSource;
//	private ArrayList<ArrayList<String>> NodeArrayList;
	public static long[]  arrayLength;
//	public static ArrayList<Long> arrayLength;
	
	public Dag(String dagDataSource) {
		super();
		this.dagDataSource = dagDataSource;
		this.initital();
	}
	
	//���ÿ���ڵ�Ĳ��жȺͲ�ͬ���ж��µ�ִ��ʱ��
	//����NodeHT����
	private void initital()
	{
		this.NodeHT = new Hashtable<String, Node>();
//		this.arrayLength = new  ArrayList<Long>();
		//�˴��ĳ���д���ˣ������Ż�ʱ������arraylist
		this.arrayLength = new long[4];
//		this.NodeList = new ArrayList<String>();
		try
		{
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(this.dagDataSource));
			Element root = document.getRootElement();
			this.name = root.element("name").getTextTrim();
//			System.out.println("name"+name);
			this.nodeid = root.element("id").getTextTrim();
//	        System.out.println("id"+id);
			this.flag = root.element("flag").getTextTrim();
			//System.out.println(this.ID+"\t"+this.cpuCapacity+"\t"+this.MemCapacity);
			List<Element> rdmt = root.element("RDMT_description").elements("parallel_processing");
//			double instanceTypeIncome = 0;
			for(int i = 0; i < rdmt.size(); i++)
			{

				Element rdmtElement = rdmt.get(i);
				double nodeParallelism = Double.valueOf(rdmtElement.element("parallelism").getTextTrim());
				long nodeDuration = Integer.valueOf(rdmtElement.element("duration").getTextTrim());
//				System.out.println("����ִ��ʱ��Ϊ"+nodeDuration);
				arrayLength[i] = nodeDuration;
//				arrayLength.add(nodeDuration);
//				System.out.println("parallelism"+nodeParallelism+"\t"+"duration"+nodeDuration);
				this.addNodeTH(this.nodeid, this.name,this.flag,nodeParallelism, nodeDuration);
  
			}
		}
		catch(Exception ex)
		{
			 ex.printStackTrace();
		}

}
	

	 private void addNodeTH(Node n) {
		 this.NodeHT.put(n.getID(), n);
	 }
	 
	 private void addNodeTH(String  nId,String name,String flag,double nodeParallelism,long nodeDuration) {
		Node n= new Node(nId,name,flag,nodeParallelism,nodeDuration);
		this.NodeHT.put(name, n);
		
	 }


	 

	 
	 public String getId() {
		return nodeid;
	}

	public void setId(String id) {
		this.nodeid = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hashtable<String, Node> getNodeHT() {
		return NodeHT;
	}

	public void setNodeHT(Hashtable<String, Node> nodeHT) {
		NodeHT = nodeHT;
	}

	public String getDagDataSource() {
		return dagDataSource;
	}

	public void setDagDataSource(String dagDataSource) {
		this.dagDataSource = dagDataSource;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

//	public static void main(String[] args) {
//		 Dag d=new Dag("src\\Xmlfile\\K_means.xml");
//		 for(Iterator<String> itr = NodeHT.keySet().iterator();itr.hasNext();){		
//			 String key = itr.next();	
//			 System.out.println("��ӡ������е�idֵ"+key);
//			 Node value = NodeHT.get(key);			
//			 System.out.println(key+" (for) "+value);	
//			 }
//		
//		 System.out.println("sussess");
//		 for(int i=0;i<arrayLength.length;i++) {
//			 System.out.println(arrayLength[i]);
//		 }
//	}
	
}