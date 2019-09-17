package com.njust.cbir.core.util.upload;


import com.njust.cbir.web.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageOperation {

//    @Value("${spark.cores}")
    private int sparkCores = 32;

//    @Value("${spark.partitions}")
    private int partitions = 2;


    @Autowired
    private HDFSOperation hdfsOperation;


    /**
     * generate thumbnail
     */
    public String generateThumbnail(InputStream in, Image image, MultipartHttpServletRequest request) throws IOException {
        int[] data = null; //
        int WIDTH = 100;
        int bands = image.getBands();
        int samples = image.getSamples();
        int rows = image.getRows();
        int datatype = image.getDatatype();
        String interleave = image.getInterleave();
        double SCALE = WIDTH/samples;
        int pixels = samples * rows;
        data = new int[pixels];
        double max = 0, min = 0;
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
        String path = request.getRealPath("/") + "/app/img/" + image.getId()+".jpg";
        try {
            File file= new File(path );
            ImageIO.write((RenderedImage)bi, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return request.getScheme() +"://" + request.getServerName()
                + ":" +request.getServerPort()
                + request.getServletPath() + "/img/" + image.getId() + ".jpg";
    }

    /**
     * entry of spectral
     * get number of pixels in per block
     * @param bands
     * @param datatype
     * @return
     */
    public long getBlockSize(int bands, int datatype, long fileSize){

        int NumberOfPixels = (int) Math.floor(fileSize / bands / datatype / sparkCores / partitions );
        return NumberOfPixels * bands * datatype;
    }

    /**
     * get the number of file blocks according spark cores
     * @param
     * @param fileSize
     * @return
     */
    public int getBlockNumber(long blockSize,long fileSize){
        return (int) (fileSize / blockSize);
    }

    public static BufferedImage toGrayScale(BufferedImage master) {
        BufferedImage gray = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        // Automatic converstion....
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB), null);
        op.filter(master, gray);

        return gray;
    }



}
