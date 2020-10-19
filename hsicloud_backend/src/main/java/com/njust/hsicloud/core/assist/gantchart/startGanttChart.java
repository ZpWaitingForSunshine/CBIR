package com.njust.hsicloud.core.assist.gantchart;

import javax.swing.*;
import java.awt.*;
public class startGanttChart {
/*    public static void main(String[] args) throws Exception{

    ReadDispatch hh = new ReadDispatch();
    hh.ReadDis();
    displayGanttChart gugugu = new displayGanttChart();
    gugugu.demo(1000,1000);
}*/
    public static void main(String[] args) throws Exception{
        String startfilepath = args[0];
        String outfilepath = args[1];
    //public  void main(String filepath) throws Exception {

        /*ReadDispatch hh = new ReadDispatch();
        hh.ReadDis(filepath);*/
        displayGanttChart gugugu = new displayGanttChart();
        //gugugu.demo(1000, 1000,startfilepath,outfilepath);
    }
}
