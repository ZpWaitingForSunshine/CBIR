package com.njust.hsicloud.core.util;


import com.njust.hsicloud.web.model.Envi;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;

import javax.imageio.ImageIO;

import static java.awt.image.ImageObserver.WIDTH;


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
    public static void   GenerateThumbnail(InputStream in, Envi image) throws IOException {
        int samples = image.getSamples();
        int lines = image.getRows();
        int bands = image.getBands();
        int datatype = image.getDatatype();
        int pixels = lines * samples;
        String interleave = image.getInterleave();
        double[] data = new double[pixels];
        byte[] tmp = null;
        byte[] bt = null;

        switch (interleave){
            case "bil":
                switch (datatype) {
                    case 2:
                        bt = new byte[2];
                        for (int i = 0; i < lines; i++) {
                            int k = i * samples;
                            for (int j = 0; j < samples; j++) {
                                in.read(bt);
                                data[k + j] = bt[0] & 0xff | bt[1] << 8;
                            }
                            in.skip(2 * (samples - 1) * bands);
                        }
                        break;
                    case 4:
                        bt = new byte[4];
                        for (int i = 0; i < lines; i++) {
                            int k = i * samples;
                            for (int j = 0; j < samples; j++) {
                                in.read(bt);
                                data[k + j] = (bt[0] & 0xff | bt[1] << 8 | bt[2] << 16 | bt[3] << 24);
                            }
                            in.skip(4 * (samples - 1) * bands);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case "bsq":
                switch (datatype) {
                    case 2:
                        bt = new byte[2];
                        for (int i = 0; i < pixels; i++) {
                            in.read(bt);
                            data[i] = bt[0] & 0xff | bt[1] << 8;
                        }
                        break;
                    case 12:
                        bt = new byte[2];
                        for (int i = 0; i < pixels; i++) {
                            in.read(bt);
                            data[i] = bt[0] & 0xff | bt[1] << 8;
                        }
                        break;
                    case 4:
                        bt = new byte[4];
                        for (int i = 0; i < pixels; i++) {
                            in.read(bt);
                            data[i] = (bt[0] & 0xff | bt[1] << 8 | bt[2] << 16 | bt[3] << 24);
                            in.skip(4 * (bands - 1));
                        }
                        break;
                    default:
                        break;
                }
                break;
            case "bip":
                int n = 0;
                switch (datatype){
                    case 2:
                        bt = new byte[2];
                        for (int i = 0; i < pixels; i++) {
                            in.read(bt);
                            data[i] = (bt[0] & 0xff | bt[1] << 8);
                            in.skip(2 * (bands - 1));
                        }
                        break;
                    case  4:
                        bt = new byte[4];
                        for (int i = 0; i < pixels; i++) {
                            in.read(bt);
                            data[i] = (bt[0] & 0xff | bt[1] << 8 | bt[2] << 16 | bt[3] << 24);
                            in.skip(4 * (bands - 1));
                        }
                        break;
                    default:
                        break;
                }

                break;

        }

        int max = 0, min = 0;
        for(int i = 0; i < pixels; i ++){
            if(data[i] > max) max = (int) data[i];
            if(data[i] < min) min = (int) data[i];
        }

        double gap = max - min;
        BufferedImage bi = new BufferedImage(samples, lines, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        for (int i = 0; i < pixels; i++) {
            int tc = (int) ((data[i] - min) / (float) gap * 255);
            tc = Math.max(0, tc);
            tc = Math.min(255, tc);
            g2d.setColor(new Color(tc, tc, tc));
           // g2d.drawOval(i / samples - 1, i % samples - 1, 1, 1);
            g2d.drawOval(i % samples - 1, i / samples - 1, 1, 1);
        }
        g2d = (Graphics2D) bi.getGraphics();
        Image image1 = bi.getScaledInstance(samples,lines,Image.SCALE_SMOOTH);
        g2d.drawImage(image1,0,0,null);

        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        path = path.substring(5, path.length() - 16) + "app/hdr/";
        System.out.println(path);
        String Thumbnailname = path + image.getFilename() +".jpg";
        ImageIO.write(bi, "jpg", new File(Thumbnailname));

//        double[][] mM = HsiUtils.linearMax(data, 0.01);
//        max = mM[1][0];
//        min = mM[0][0];
//        double gap = max - min;
//        int HEIGHT = WIDTH * lines / samples;
//        BufferedImage bi = new BufferedImage(lines, samples, BufferedImage.TYPE_INT_RGB);
//        BufferedImage bi2 = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2d = (Graphics2D) bi.getGraphics();
//
//        for (int i = 0; i < pixels; i++) {
//            int tc = (int) ((data[i] - min) / (float) gap * 255);
//            tc = Math.max(0, tc);
//            tc = Math.min(255, tc);
//            g2d.setColor(new Color(tc, tc, tc));
//           // g2d.drawOval(i / samples - 1, i % samples - 1, 1, 1);
//            g2d.drawOval(i % samples - 1, i / samples - 1, 1, 1);
//        }
//
//        Image image = bi.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
//        g2d = (Graphics2D) bi2.getGraphics();
//        g2d.drawImage(image, 0, 0, null);
//
//       // ImageIO.write(bi, "jpg", new File(header.getName() + ".jpg"));
//       ImageIO.write(bi, "jpg", new File("/home/jingle/Data/350.jpg"));

//        double max = Double.MIN_VALUE;
//        double min = Double.MAX_VALUE;
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                if (data[i][j] > max) {
//                    max = data[i][j];
//                }
//                if (data[i][j] < min) {
//                    min = data[i][j];
//                }
//            }
//        }
//
//        BufferedImage bufferedImage = new BufferedImage(cols,rows, BufferedImage.TYPE_BYTE_GRAY);
//
//        for(int i=0; i<rows; i++)
//            for(int j=0;j<cols;j++)
//            {
//                int rgb = (int) ((data[i][j] - min) / (max - min) * 255);
//                bufferedImage.setRGB(j,i,rgb);
//            }
//
//        try {
//            String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
//            path = path.substring(5, path.length() - 16) + "app/hdr/";
//            System.out.println(path);
//            String Thumbnailname = path + image.getFilename() +".jpg";
//            File file= new File(Thumbnailname);
//            OutputStream out = new FileOutputStream(new File(Thumbnailname));
//            ImageIO.write(bufferedImage, "jpg", out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
