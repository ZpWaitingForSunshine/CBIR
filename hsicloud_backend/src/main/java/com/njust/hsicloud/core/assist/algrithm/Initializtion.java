package com.njust.hsicloud.core.assist.algrithm;


import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cloudbus.cloudsim.core.CloudSim;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.njust.hsicloud.core.assist.dag.Dag;
import com.njust.hsicloud.core.assist.dag.Node;
import flanagan.analysis.PCA;
import java.util.HashMap;


//create by shell
//2019.03.22
//����xml�ļ�������DAGͼ��ɶ�Ӧ�ĸ�ʽ
public class Initializtion {
    
   
	/*
	 * ��xml�ļ��е����ݶ�ȡ���ڴ浱��
	 * ��ȡDAGdescription
	 * ��DAG����ڽӾ���
	 */

	public static String  dagname;
	public static int VMnum;
	public static String ImageId;
	public static  String object;
    //�ڽӾ���洢DAGͼ
    //��СӦ���ǽڵ��������Դ�xml���ж�����
    int matrix[][]; 
    //�ڵ���
    public static int nodecount;
    //�洢node
	public  Hashtable<String,Node> NodeHT1;
	public HashMap<Integer,Integer> idfilter = new HashMap();
	public void readDAG(String filePath) throws Exception{
//		System.out.println("???");
		this.NodeHT1 = new Hashtable<String, Node>();

//		for(int i=0;i<7;i++){
//			for(int j=0;j<7;j++) {
//				arrays[i][j]=0;
//			}
//		}

//		System.out.println("����ĳ���"+matrix.length);
		 SAXReader reader = new SAXReader();
	        File file = new File(filePath);
	        Document document = reader.read(file);
	        Element root = document.getRootElement();
	        //���ĸ�Ԫ����Ҫ���д�������xml����ȥ
	         this.dagname = root.element("dagname").getTextTrim();
	         this.VMnum =  Integer.valueOf(root.element("VMnum").getTextTrim());
	         this.ImageId =root.element("ImageID").getTextTrim();
	         this.object = root.element("object").getTextTrim();
	         
	         List<Element> nodeList = root.element("nodes").elements("node");
	         nodecount = nodeList.size();
//	         System.out.println("nodeList����"+nodeList.size());
			int flag = 0;
			/*nwk create and alter*/
			for(int i = 0; i <nodeList.size();i++)
			{
				Element nodeListElement = nodeList.get(i);
				String  nodeid = nodeListElement.element("id").getTextTrim();
				int id = Integer.parseInt(nodeid);
				idfilter.put(id,flag);
				flag++;
			}
			int[][] arrays=new int[flag][flag];
			this.matrix=arrays;
			/*nwk alter end */
			/*hash map �洢id �� �ڵ��ŵĹ�ϵ ���ڽӾ���洢*/

			flag = 0;
				for(int i = 0; i < nodeList.size(); i++)
				{
					Element nodeListElement = nodeList.get(i);
					String  nodeid = nodeListElement.element("id").getTextTrim();

					String  name =nodeListElement.element("name").getTextTrim();
					String  url =nodeListElement.element("url").getTextTrim();

					this.addNodeTH1(nodeid, name,url);
//                    System.out.println("�ڵ�id"+nodeid);+

					Dag d=new Dag(url);
					
					//List<Element> Successorelementlist = nodeListElement.elements("Successor");
					String  succesor =nodeListElement.element("successor").getTextTrim();
					if(succesor.length() !=0 )
					{
						String[] succesors ;
						succesors = succesor.trim().split(",");
						for(int j=0;j<succesors.length;j++)
						{
							int key = idfilter.get(Integer.parseInt(succesors[j]));
							matrix[flag][key]=1;
							//System.out.println(flag+"  "+j+"  "+key);
						}
					}
					flag++;
		        	/*for(Element ele: Successorelementlist){
//	        		 System.out.println(ele.getName()+" "+ele.getText());
	        		 String successor=ele.getTextTrim();
//	        		 System.out.println("�����ڵ�Ϊ"+successor);
	        		 int j = Integer.parseInt(successor);
	        		 matrix[id][j]=1;
//	        		 System.out.println( matrix[i][j]);
	        	    }*/

	  
	           }
//				 for(int i=0;i<7;i++) {
//		        	 for(int j=0;j<7;j++) {
//		             System.out.print(matrix[i][j]);
//		        	 }
//		        	 System.out.println();
//		        	
//		        }
				
	}
	      	        
	

	
	private void addNodeTH1(String id, String name, String url) {
		Node n= new Node(id,name,url);
		this.NodeHT1.put(id, n);
		
	}


	public  Initializtion() throws Exception  {
	   System.out.println("here");
       readDAG("/home/hadoop/Desktop/quick4j-master 2/target/quick4j/assets/XmlFile/result1.xml");
       System.out.println("gouzaofangfa ");
       
        
    }
//	public static void main(String[] args) throws Exception {
//		Initializtion in= new Initializtion();
//	
//}
}
