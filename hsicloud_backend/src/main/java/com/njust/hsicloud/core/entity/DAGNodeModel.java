package com.njust.hsicloud.core.entity;

import java.util.ArrayList;
public class DAGNodeModel {
    private int id;
    private String name;
    private String url;
    private boolean flag;
    private ArrayList<String> predecessor;
    private ArrayList<String> successor;
    public DAGNodeModel(int id,String name,String url,boolean flag,ArrayList<String> predecessor,ArrayList<String> successor){
        this.id=id;
        this.name=name;
        this.url=url;
        this.flag=flag;
        this.predecessor=predecessor;
        this.successor=successor;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
    public boolean isFlag(){
        return this.flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag ;
    }
    public ArrayList<String> getPredecessor(){
        return this.predecessor;
    }
    public void setPredecessor(String predecessor) {
        if(successor!=null){
            this.predecessor.add(predecessor);
        }
    }
    public ArrayList<String> getSuccessor(){
        return this.successor;
    }
    public void setSuccessor(String successor) {
        if(successor!=null){
            this.successor.add(successor);
        }
    }
}
