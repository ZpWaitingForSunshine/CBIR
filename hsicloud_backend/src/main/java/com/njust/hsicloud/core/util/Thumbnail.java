//package com.njust.hsicloud.core.util;
//
//import com.njust.hsicloud.core.util.HSIhdr;
//import com.jingle.cbir.core.util.HsiUtils;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * Created by jingle on 16-3-18.
// */
//public class Thumbnail {
//    private InputStream in = null;
//    private HSIhdr header = null;
//    private double[] data = null;
//    private int WIDTH = 100;
//    public Thumbnail(InputStream in, HSIhdr header) throws IOException{
//        this.in = in;
//        this.header = header;
//    }
//
//    public Thumbnail(InputStream in, HSIhdr header, int width) throws IOException{
//        this(in, header);
//        this.WIDTH = width;
//    }
//
//    public void process() throws IOException{
////        Inter inter = header.getInter();
//        int bands = header.getBands();
//        int samples = header.getCol();
//        int lines = header.getRow();
//        short type = header.getDatatype();
//        int pixels = samples * lines;
//        data = new double[pixels];
//        double max = 0, min = 0;
//        byte[] bt = null;
//        switch (inter){
//            case BIL:
//                switch (type) {
//                    case 2:
//                        bt = new byte[2];
//                        for (int i = 0; i < lines; i++) {
//                            int k = i * samples;
//                            for (int j = 0; j < samples; j++) {
//                                in.read(bt);
//                                data[k + j] = bt[0] & 0xff | bt[1] << 8;
//                            }
//                            in.skip(2 * (samples - 1) * bands);
//                        }
//                        break;
//                    case 4:
//                        bt = new byte[4];
//                        for (int i = 0; i < lines; i++) {
//                            int k = i * samples;
//                            for (int j = 0; j < samples; j++) {
//                                in.read(bt);
//                                data[k + j] = (bt[0] & 0xff | bt[1] << 8 | bt[2] << 16 | bt[3] << 24);
//                            }
//                            in.skip(4 * (samples - 1) * bands);
//                        }
//                        break;
//                    default:
//                        break;
//                }
//                break;
//            case BSQ:
//                switch (type) {
//                    case 2:
//                        bt = new byte[2];
//                        for (int i = 0; i < pixels; i++) {
//                            in.read(bt);
//                            data[i] = bt[0] & 0xff | bt[1] << 8;
//                        }
//                        break;
//                    case 4:
//                        bt = new byte[4];
//                        for (int i = 0; i < pixels; i++) {
//                            in.read(bt);
//                            data[i] = (bt[0] & 0xff | bt[1] << 8 | bt[2] << 16 | bt[3] << 24);
//                            in.skip(4 * (bands - 1));
//                        }
//                        break;
//                    default:
//                        break;
//                }
//                break;
//            case BIP:
//                int n = 0;
//                switch (type){
//                    case 2:
//                        bt = new byte[2];
//                        for (int i = 0; i < pixels; i++) {
//                            in.read(bt);
//                            data[i] = (bt[0] & 0xff | bt[1] << 8);
//                            in.skip(2 * (bands - 1));
//                        }
//                        break;
//                    case  4:
//                        bt = new byte[4];
//                        for (int i = 0; i < pixels; i++) {
//                            in.read(bt);
//                            data[i] = (bt[0] & 0xff | bt[1] << 8 | bt[2] << 16 | bt[3] << 24);
//                            in.skip(4 * (bands - 1));
//                        }
//                        break;
//                    default:
//                        break;
//                }
//
//                break;
//
//        }
//
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
//    }
//
//    public int[][] test() throws IOException{
//        FileInputStream in = new FileInputStream(new File("/home/jingle/Data/test.img"));
//        BufferedImage bi = new BufferedImage(350, 350, BufferedImage.TYPE_INT_RGB);
//        BufferedImage bi2 = new BufferedImage(350, 350, BufferedImage.TYPE_BYTE_GRAY);
//        Graphics2D g2d = (Graphics2D) bi2.getGraphics();
//        int[][] data = new int[350][350];
//        for (int i = 0; i < 350; i++) {
//            for (int j = 0; j < 350; j++) {
//                data[i][j] = in.read();
//                //g2d.setColor(new Color(data[i][j], data[i][j], data[i][j]));
//                bi2.setRGB(j, i, data[i][j]);
//                //g2d.drawOval(j, i, 1, 1);
//            }
//        }
//
//        //ImageIO.write(bi, "jpg", new File(header.getName() + ".jpg"));
//        ImageIO.write(bi2, "jpg", new File("/home/jingle/Data/test.jpg"));
//        System.out.println();
//        return data;
//    }
//}
