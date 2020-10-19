package com.njust.hsicloud.core.assist.gantchart;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class displayGanttChart extends JFrame{
    public void demo(String startfilepath,String outfilepath)throws Exception{
        int width =600;
        int height = 500;
        drawGanttChart mp = new drawGanttChart(width,height);
        mp.getInfoSchu(startfilepath);
        this.add(mp);
        this.setSize(width,height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        BufferedImage  bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D  g2d = bi.createGraphics();
        this.paint(g2d);
        ImageIO.write(bi, "PNG", new File(outfilepath));
        this.setVisible(true);
    }




}
