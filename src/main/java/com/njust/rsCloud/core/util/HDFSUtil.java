package com.njust.rsCloud.core.util;


import com.njust.rsCloud.web.model.Envi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;



public class HDFSUtil {
    public static Envi getImageMeta(InputStream inputStream, String filename){
        Envi image = new Envi();
//        image.setImagename();
        image.setFilename(filename.split("\\.")[0]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String strline,strleft,strright;
        try{
            while((strline = reader.readLine())!=null){
                int pos = strline.indexOf('=');
                if(pos >= 0){
                    strleft = strline.substring(0,pos).trim();
                    strright = strline.substring(pos+1,strline.length()).trim();

                    if(strleft.equals("samples")){
                        image.setSamples(Integer.parseInt(strright));
                        continue;
                    }
                    if(strleft.equals("lines")){
                        image.setRows(Integer.parseInt(strright));
                        continue;
                    }

                    if(strleft.equals("bands")){
                        image.setBands(Integer.parseInt(strright));
                        continue;
                    }

                    if(strleft.equals("header offset")){
                        image.setHeaderoffset(Integer.parseInt(strright));
                        continue;
                    }

                    if(strleft.equals("file type")){
                        image.setFiletype(strright.toString());
                        continue;
                    }

                    if(strleft.equals("data type")){
                        image.setDatatype((int) Byte.parseByte(strright));
                        continue;
                    }

                    if(strleft.equals("interleave")){
                        image.setInterleave(strright.toString());
                        continue;
                    }

                    if(strleft.equals("Image Bin")){
                        image.setImagebin(Integer.parseInt(strright));
                        continue;
                    }

                    if(strleft.equals("band names")){
                        image.setBandnames(strright.toString());
                        continue;
                    }

                    if(strleft.equals("wavelength")){
                        image.setWavelength(strright.toString());
                        continue;
                    }

                    if(strleft.equals("Time of exposure")){
                        image.setTimeofexposure(strright.toString());
                        continue;
                    }

                    if(strleft.equals("Image Bin")){
                        image.setImagebin(Integer.parseInt(strright));
                        continue;
                    }

                    if(strleft.equals("AutoExp ROI X")){
                        image.setAutoexproix(Integer.parseInt(strright));
                        continue;
                    }

                    if(strleft.equals("AutoExp ROI Width")){
                        image.setAutoexproiwidth(Integer.parseInt(strright));
                        continue;
                    }

                    if(strleft.equals("AutoExp ROI Y")){
                        image.setAutoexproiy(Integer.parseInt(strright));
                        continue;
                    }

                    if(strleft.equals("AutoExp ROI Height")){
                        image.setAutoexproiheight(Integer.parseInt(strright));
                        continue;
                    }

                    if(strleft.equals("Gain")){
                        image.setGain(Float.parseFloat(strright));
                        continue;
                    }

                    if(strleft.equals("Exposure coefficient")){
                        image.setExposurecoefficient(Float.parseFloat(strright));
                        continue;
                    }

                    if(strleft.equals("LCTF Temperature")){
                        image.setLctftemperature(Float.parseFloat(strright));
                        continue;
                    }

                    if (strleft.equals("byte order")){
                        image.setByteorder((int) Byte.parseByte(strright));
                        continue;
                    }
                }

            }
        }catch (FileNotFoundException e){
            System.out.println("failed to read");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static void GenerateThumbnail(InputStream in, Envi image) throws IOException {
        int WIDTH = 80;
        int cols = image.getSamples();
        int rows = image.getRows();
        int bands = image.getBands();
        int datatype = image.getDatatype();
        String interleave = image.getInterleave();
        int HEIGHT = WIDTH * rows / cols;
        double[][] data = new double[rows][cols];
        byte[] tmp = null;
        DataInputStream dis = new DataInputStream(in);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = dis.readShort() ;
            }
        }

        if (dis != null) {
            dis.close();
        }
        if (in != null) {
            in.close();
        }

        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (data[i][j] > max) {
                    max = data[i][j];
                }
                if (data[i][j] < min) {
                    min = data[i][j];
                }
            }
        }

        BufferedImage bi = new BufferedImage(cols, rows, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D)bi.getGraphics();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int rgb = (int) ((data[i][j] - min) / (max - min) * 255);
                g2d.setColor(new Color(rgb, rgb, rgb));
                g2d.drawOval(j - 1, i - 1, 1, 1);
            }
        }

//        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
//        path = path.substring(5, path.length() - 16) + "/app/img/";
        String path = "/home/hadoop/test1/hdr/";

        BufferedImage bi2 = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        java.awt.Image image1 = bi.getScaledInstance(WIDTH, HEIGHT, java.awt.Image.SCALE_SMOOTH);
        g2d = (Graphics2D) bi2.getGraphics();
        g2d.drawImage(image1, 0, 0, null);
        ImageIO.write(bi2, "jpg", new File(path + image.getFilename()));
    }
}
