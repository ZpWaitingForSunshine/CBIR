package com.njust.hsicloud.core.util.upload;


import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import com.njust.hsicloud.web.model.Image;

public class ImageOperation {

    /**
     * generate thumbnail
     */
    public String generateThumbnail(InputStream in, Image image) throws IOException {
        int[] data = null; //
        int WIDTH = 100;
        int bands = image.getBands();
        int samples = image.getSamples();
        int rows = image.getRows();
        int datatype = image.getDatatype();
        String interleave = image.getInterleave();
        int pixels = samples * rows;
        data = new int[pixels];
        double max = 0, min = 0;
        String imgpath = null;
        byte[] bt = null;
        switch (interleave){
            case "bil": break;
            case "bsq":break;
            case "bip":
                int n = 0;
                switch (datatype){
                    case 2:
                        bt = new byte[2];
                        for (int i = 0; i < pixels; i++) {
                            in.read(bt);
                            data[i] = (bt[0] & 0xff | bt[1] << 8);
                            in.skip(2 * (bands - 1));
                            if (i == 0) {
                                max = data[0];
                                min = data[0];
                            }
                            if (max < data[i]) {
                                max = data[i];
                            }
                            if (min > data[i]) {
                                min = data[i];
                            }
                        }
                        break;
                    case  4:
                        bt = new byte[4];
                        for (int i = 0; i < pixels; i++) {
                            in.read(bt);
                            data[i] = (bt[0] & 0xff | bt[1] << 8 | bt[2] << 16 | bt[3] << 24);
                            in.skip(2 * (bands - 1));
                            if (i == 0) {
                                max = data[0];
                                min = data[0];
                            }
                            if (max < data[i]) {
                                max = data[i];
                            }
                            if (min > data[i]) {
                                min = data[i];
                            }
                        }
                        break;
                    default:
                        break;
                }
        }

        double gap = max - min;
        int HEIGHT = WIDTH * rows / samples;
        BufferedImage bi = new BufferedImage(rows, samples, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        for (int i = 0; i < pixels; i++) {
            data[i] = (int) ((data[i] - min) / (float) gap * 255);
            g.setColor(new Color(data[i],  data[i],data[i] / 2));
            g.fillRect(i % samples, i / samples, 1, 1);
        }
         try {

             WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
             ServletContext servletContext = webApplicationContext.getServletContext();
             // 获取路径
             imgpath = servletContext.getRealPath("/") + "/thumbnail/" ;

             // 创建文件夹
             if(!new File(imgpath).exists()){
                 new File(imgpath).mkdirs();
             }

             ImageIO.write(bi, "jpg", new File(imgpath + image.getId() + ".jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/thumbnail/"+ image.getId() + ".jpg";
    }

    public static BufferedImage toGrayScale(BufferedImage master) {
        BufferedImage gray = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        // Automatic converstion....
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB), null);
        op.filter(master, gray);

        return gray;
    }
}
