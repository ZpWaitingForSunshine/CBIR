package njust.tools;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import scala.Tuple2;
import scala.Tuple3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Random;

/**
 * Created by lyl on 15-1-24.
 * modified by jingle on 15-6-23.
 */
public class JTool {
    //base-pair._1
    //data[][]-pair._2
    public static double[][] calcMaxMin(double[][] data, double [][] mat, int base){
        int bands = data[0].length;
        int pixes = data.length;
        int nums = mat.length;    //随机单位向量个数

        double [][] maxMin = new double[nums][4];
        for(int i = 0; i < nums; i++){
            double max = 0 - Float.MAX_VALUE + 1;
            double min = Float.MAX_VALUE;
            double maxk = base;
            double mink = base;
            for(int j = 0; j < pixes; j++) {
                double temp = 0;
                for (int k = 0; k < bands; k++) {
                    temp += data[j][k] * mat[i][k];//向量点乘，垂直则为0
                }
                if (temp > max) {
                    max = temp;
                    maxk = base + j;
                }
                if (temp < min) {
                    min = temp;
                    mink = base + j;
                }
            }
            maxMin[i][0] = mink; //记录当前分片最小值的id
            maxMin[i][1] = maxk; //记录当前分片最大值的id
            maxMin[i][2] = min; //记录当前分片最小值
            maxMin[i][3] = max; //记录当前分片最大值
        }
        return  maxMin;
    }

    public static double[][] calFinalMaxMin(double[][] x, double[][] y){
        int len = x.length;
        double[][] tmM = new double[len][4];

        for(int i = 0; i < len; ++i) {
            if(x[i][2] < y[i][2]) {
                tmM[i][0] = x[i][0];
                tmM[i][2] = x[i][2];
            } else {
                tmM[i][0] = y[i][0];
                tmM[i][2] = y[i][2];
            }

            if(x[i][3] >= y[i][3]) {
                tmM[i][1] = x[i][1];
                tmM[i][3] = x[i][3];
            } else {
                tmM[i][1] = y[i][1];
                tmM[i][3] = y[i][3];
            }
        }
        return tmM;
    }
    /*
    v1:
     */
//    public static int countIndex(int k, float [][] maxMin){
//        int t = 0;
//        int len = maxMin[0].length;
//        for(int i = 0; i < len; i++){
//            if(maxMin[0][i] == k || maxMin[1][i] == k){
//                t++;
//            }
//        }
//        return t;
//    }

    /*
    v2:countIndex
     */
    public static int[] countIndex(double[][] maxMin, int pixels){
        int[] index = new int[pixels];
        int len = maxMin.length;
        for(int i = 0; i < len; i++){
            index[(int)maxMin[i][0]]++;  //
            index[(int)maxMin[i][1]]++;
        }
        return index;

    }
    public static double[][] createMat(int nums, int bands){
        double[][] mat = new double[nums][bands];
        double PI = (double)3.1415926535897;
        double u1, u2;
        Random r = new Random();
        double temp;

        for(int i = 0; i < nums; i++){
            for(int j = 0; j < bands; j++){
                u1 = r.nextDouble();
                u2 = r.nextDouble();
                mat[i][j] = (double)(Math.sqrt(-2 * Math.log(u1)) * Math.sin(2 * PI * u2));
            }
        }
        for(int i = 0; i < nums; i++){
            temp = 0;
            for(int j = 0; j < bands; j++){
                temp += mat[i][j] * mat[i][j];
            }
            for(int j = 0; j < bands; j++){
                mat[i][j] /= Math.sqrt(temp);
            }
        }
        return mat;
    }

    public static double SAD(double[] v1, double[] v2){
        double t1 = 0, t2 = 0, t3 = 0;
        int bands = v1.length;
        for(int i = 0; i < bands; i++){
            t1 += v1[i] * v2[i];
            t2 += v1[i] * v1[i];
            t3 += v2[i] * v2[i];
        }
        return (double)Math.acos(t1 * t1 / (t2 * t3));
    }

    public static double[][] BtoF(byte[] data,int pixel,int col,int bands, int datasize){
        int row = pixel/col;

        double[][] fdata=new double[pixel][bands];
        System.out.println("Read beginning!!!!!!!!!!!!!!!!"+datasize);
        int n=0;
        switch (datasize){
            case 4:
                for(int i=0;i<row;i++){
                    int p=i*col;
                    for(int j=0;j<bands;j++)
                        for(int k=0;k<col;k++){
                            int t=(int)((data[n++]&0xff)|(data[n++]<<8)&0xffff |
                                    (data[n++]<<16)&0xffffff | (data[n++]<<24));
                            fdata[p+k][j]= (double) Float.intBitsToFloat(t);
                        }
                }
                System.out.println("Read ok!!!!!!!!!!!!!!!!");
                System.out.println(fdata[61][0]);
                break;
            case 2:
                for(int i = 0; i < row; i++){
                    int p = i * col;
                    for(int j = 0; j < bands; j++){
                        for(int k = 0; k < col; k++){
                            int t = (int)((data[n++] & 0xff) | (data[n++] << 8));
                            //System.out.println(t);
                            fdata[p + k][j] =t;
                        }
                    }
                }
                System.out.println("Read ok!!!!!!!!!!!!!!!!");
                System.out.println(fdata[0][0]);
                break;
            default:
                System.out.println("不支持该格式！");
        }

        return fdata;
    }

//    public static boolean drawResult(int[][] result, String path, int row, int col) {
//        boolean flag = false;
//        int len = result.length;
//        BufferedImage bi = new BufferedImage(col, row, 1);
//        Graphics2D g2d = (Graphics2D)bi.getGraphics();
//        g2d.setColor(Color.WHITE);
//
//        for(int e = 0; e < len; ++e) {
//            g2d.drawOval(result[e][0], result[e][1], 1, 1);
//        }
//
//        try {
//            ImageIO.write(bi, "JPG", new FileOutputStream(path));
//            flag = true;
//        } catch (IOException var9) {
//            var9.printStackTrace();
//        }
//
//        return flag;
//    }

    public static double[][] BtoFBil(byte[] data, int pixel, int col, int bands, int datasize) {
        int row = pixel / col;
        double[][] fdata = new double[pixel][bands];
        int n = 0;
        int t;
        int i;
        int p;
        int j;
        int k;
        switch(datasize) {
            case 2:
                for(i = 0; i < row; ++i) {
                    p = i * col;

                    for(j = 0; j < bands; ++j) {
                        for(k = 0; k < col; ++k) {
                            t = data[n++] & 0xff | data[n++] << 8;
                            fdata[p + k][j] = (double)t;
                        }
                    }
                }
                System.out.println("Read ok!!!!!!!!!!!!!!!!");
                System.out.println(fdata[0][0]);
                return fdata;
            case 4:
                for(i = 0; i < row; ++i) {
                    p = i * col;

                    for(j = 0; j < bands; ++j) {
                        for(k = 0; k < col; ++k) {
                            t = data[n++] & 0xff | data[n++] << 8 & 0xffff | data[n++] << 16 & 0xffffff | data[n++] << 24;
                            fdata[p + k][j] = (double)Float.intBitsToFloat(t);
                        }
                    }
                }

                return fdata;
            default:
                System.out.println("不支持该格式！");
                return fdata;
        }
    }

    public static double[][] BtoFBip(byte[] data, int pixel, int bands, int datasize) {
        double[][] fdata = new double[pixel][bands];
        int n = 0;
        int t;
        int i;
        int j;
        switch(datasize) {
            case 2:
                for(i = 0; i < pixel; ++i) {
                    for(j = 0; j < bands; ++j) {
                        t = data[n++] & 0xff | data[n++] << 8;
                        fdata[i][j] = (double)t;
                    }
                }
                return fdata;
            case 4:
                for(i = 0; i < pixel; ++i) {
                    for(j = 0; j < bands; ++j) {
                        t = data[n++] & 0xff | data[n++] << 8 & 0xffff | data[n++] << 16 & 0xffffff | data[n++] << 24;
                        fdata[i][j] = (double)Float.intBitsToFloat(t);
                    }
                }

                return fdata;
            default:
                System.out.println("不支持该格式！");
                return fdata;
        }
    }
    //    public static void saveTxtArray(double[][] data,String path) throws IOException {
//        File file = new File(path);  //存放数组数据的文件
//        FileWriter out = new FileWriter(file);  //文件写入流
//
//        //将数组中的数据写入到文件中。每行各数据之间TAB间隔
//        for(int i=0;i<data.length;i++){
//            for(int j=0;j<data[0].length;j++){
//                    String t=(double)Float.toString(data[i][j]);
//                    out.write(t+","+"\t");
//            }
//            out.write("\r\n");
//        }
//        out.close();
//
//    }
    public static void savePosTxt(int[] data,String path) throws IOException {
        File file = new File(path);  //存放数组数据的文件
        FileWriter out = new FileWriter(file);  //文件写入流
        //将数组中的数据写入到文件中。每行各数据之间TAB间隔
        for(int i=0;i<data.length;i++){
            String t=Integer.toString(data[i]);
            out.write(t+"\r\n");
        }
        out.close();

    }
    public static void writeImageFromArrayGray(String imageFile,int[] emArray,int width,int height){

        BufferedImage bf = new BufferedImage(width,height , BufferedImage.TYPE_INT_RGB);
        for (int i=0;i<width*height;i++) {
            //   rgbArray[i]=rgbArray[i]+ 256 * rgbArray[i]+65536 *rgbArray[i];
            if (emArray[i] != 0) {
                emArray[i] = Color.white.getRGB();

            }
            else if(emArray[i]==0){
                emArray[i] = Color.black.getRGB();
            }

//       for (int i=0;i<allcolor.length;i++)
//       {
//           for (int j=0;j<allcolor[i].length;j++)
//       {
//           int t=-allcolor[i][j];
//           Color c= new Color(0,0,t/100000);
//           g.setColor(c);
//           g.drawLine(j+300,i+150, j+300, i+150);
//       }
//       }

        }
        //   bf.setRGB(0, 0, width, height, rgbArray, 0, width);
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
            {

                bf.setRGB(j,i,emArray[i*width+j]);
            }
        try {
            File file= new File(imageFile);
            ImageIO.write((RenderedImage)bf, "jpg", file);
            System.out.println("draw sucessfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
