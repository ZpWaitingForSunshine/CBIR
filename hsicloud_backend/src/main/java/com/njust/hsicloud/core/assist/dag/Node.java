package com.njust.hsicloud.core.assist.dag;



public class Node {

	private String nodeID;
	private String name;
	private String flag;
	private String path;
	private String Successor;
	private String Predecessor;
	private double nodeParallelism;
	private long nodeDuration; 
	
	public Node(String iD, String name, String url ) {
		super();
		nodeID = iD;
		this.name = name;
		this.path= url;
	}

	public Node(String iD, String name, String flag,String Successor,String Predecessor ) {
		super();
		nodeID = iD;
		this.name = name;
		this.flag = flag;
		this.Successor = Successor;
		this.Predecessor=Predecessor;
	}
	public  Node(String iD,String name,String flag, double nodeParallelism, long nodeDuration){
		nodeID = iD;
		this.name = name;
		this.flag = flag;
		this.nodeParallelism=nodeParallelism;
		this.nodeDuration=nodeDuration;
	}
	
	public Node( String name ) {
		super();
		
		name = name;
	}


	public String getID() {
		return nodeID;
	}



	public void setID(String iD) {
		nodeID = iD;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public double getNodeParallelism() {
		return nodeParallelism;
	}
	public void setNodeParallelism(double nodeParallelism) {
		this.nodeParallelism = nodeParallelism;
	}
	public long getNodeDuration() {
		return nodeDuration;
	}
	public void setNodeDuration(long nodeDuration) {
		this.nodeDuration = nodeDuration;
	}
	public String getSuccessor() {
		return Successor;
	}



	public void setSuccessor(String successor) {
		Successor = successor;
	}



	public String getPredecessor() {
		return Predecessor;
	}



	public void setPredecessor(String predecessor) {
		Predecessor = predecessor;
	}



	public void display()
	{
		System.out.println(nodeID+"\t"+name+"\t"+flag+"\t"+Successor+"\t"+Predecessor);
	}
}

