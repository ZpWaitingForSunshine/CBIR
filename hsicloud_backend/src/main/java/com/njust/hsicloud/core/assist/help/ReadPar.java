package com.njust.hsicloud.core.assist.help;

import com.njust.hsicloud.core.assist.algrithm.Initializtion;


public class ReadPar {
    private double [][] phe_mat;//��ʼ��Ϣ�ؾ���
    private int vmnum;
    private int tasknum;

    //��ʼ��
    public ReadPar() {

    	
 //   	this.tasknum = 14;
    	this.tasknum = Initializtion.nodecount;
    	System.out.println(tasknum);
//        this.vmnum = 5;
    	this.vmnum = Initializtion.VMnum;
    	System.out.println(vmnum);
        phe_mat = new double[tasknum][vmnum];


        for (int i = 0; i < tasknum; i++) {//��ʼ����Ϣ��
            for (int j = 0; j < vmnum; j++) {
                phe_mat[i][j] = 10;
            }
        }

    }
    //������Ϣ�ؾ���
    public double[][] getpht_mat(){
        return this.phe_mat;
    }

    //�������������
    public int getvmnum(){
        return this.vmnum;
    }
    //������������
    public int gettasknum(){
        return this.tasknum;
    }
}

