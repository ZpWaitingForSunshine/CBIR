package com.njust.hsicloud.core.assist.gantchart;

import java.awt.*;
import javax.swing.*;
public class drawGanttChart extends  JPanel{
    public int VMnum;
    public int width;
    public int height;
    drawGanttChart(int width,int height){
        this.width = width;
        this.height = height;
    }

    //画图的起始逻辑
    public void paint(Graphics g) {
        // 1.调用父类函数完成初始化任务
        //这句话不可以少
        super.paint(g);

        //先画一个圆圈

        //g.drawOval(100,100,30,30);
        //画表示 节点数量的的线
        //暂定上下位置-110，平均画线
        int turewidth = this.width-200;
        //设置开始点 和 结束点 每个线条的 长度
        int startline = 90;
        int stopline = height-100;
        int linelong = stopline-startline;
        scale(g,startline,stopline,linelong);
        g.setColor(Color.BLACK);
        for(int i = 1;i< VMnum+1;i++){
            g.drawLine(startline,turewidth/4*i,stopline,turewidth/4*i);
            //画箭头
            int[] xPoints= new int[3];
            int[] yPoints= new int[3];
            xPoints[0] = height-100;
            xPoints[1]=height-100;
            xPoints[2] = height-90;
            yPoints[0] = turewidth/4*i+10;
            yPoints[1] = turewidth/4*i-10;
            yPoints[2] = turewidth/4*i;
            g.drawPolygon(xPoints,yPoints, 3);
            g.fillPolygon(xPoints,yPoints, 3);
            //写节点
            g.setFont(new Font("", Font.BOLD, 15));
            g.drawString("Core"+i, 10,turewidth/4*i);
        }

    }

    //画每个算法的矩形
    public void scale(Graphics g,int startline ,int stopline ,int linelong ){
        //得到最后完成时间 目的得到每个矩形大小 按比例画出来
        double time = this.FinishTime();
        //画矩形
        for(int i = 0; i< ReadDispatch.smallAlgrithms.length; i++){
            int vmid = ReadDispatch.smallAlgrithms[i].VMID;
            double start = ReadDispatch.smallAlgrithms[i].StartTime;
            double finish = ReadDispatch.smallAlgrithms[i].FinishTime;
        //    System.out.println(start+"  AAA "+finish);
            start = start / time * linelong;
            finish =finish / time * linelong;
            int[] xPoints= new int[4];
            int[] yPoints= new int[4];
            int Y =  whereY(vmid);
            xPoints[0] = startline + (int)start;
            xPoints[1] = startline + (int)finish;
            xPoints[2] =startline + (int)start;
            xPoints[3] = startline + (int)finish;
            yPoints[0] = Y - 20;
            yPoints[1] = Y - 20;
            yPoints[2] = Y;
            yPoints[3] = Y;


            g.setColor(Color.cyan);
            g.fillRect(xPoints[0],yPoints[1],(int)(finish-start),20);
            g.setColor(Color.BLACK);
            g.drawRect(xPoints[0],yPoints[1],(int)(finish-start),20);
            g.setFont(new Font("", Font.PLAIN, 15));
            g.setColor(Color.BLACK);
            if(!ReadDispatch.smallAlgrithms[i].CloudletID.equals("root")) {
                System.out.println(ReadDispatch.smallAlgrithms[i].CloudletID);
                g.drawString(ReadDispatch.smallAlgrithms[i].CloudletID, xPoints[0]+2, Y);
            }
        }

    }
    public void getInfoSchu(String filepath)throws Exception{
        ReadDispatch readDispatch = new ReadDispatch();
        readDispatch.ReadDis(filepath);
        this.VMnum = readDispatch.VMnum;
    }

    //读取最后的完成时间
    public double FinishTime(){
        double tmp=0.0;
        for(int i = 0; i< ReadDispatch.smallAlgrithms.length; i++){
            if(tmp< ReadDispatch.smallAlgrithms[i].FinishTime)
                tmp = ReadDispatch.smallAlgrithms[i].FinishTime;
        }
        return tmp;
    }
    //得到画矩形时候的坐标 输入为节点号，输出为表示上下的坐标
    public int whereY(int id){
       return (this.width-200)/4*(id+1);
    }

}
