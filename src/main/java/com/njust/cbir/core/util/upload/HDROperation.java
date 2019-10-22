package com.njust.cbir.core.util.upload;

import com.njust.cbir.web.model.Image;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;


public class HDROperation {
    @Value("${hdfs.path}")
    private String hdfspath;

    /**
     * get hdr metadata
     * @param inputStream
     * @return
     */
    public static Image getImageMeta(InputStream inputStream){
        Image image = new Image();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String strline;
        String strleft;
        String strright;
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
                        image.setBands( Integer.parseInt(strright));
                        continue;
                    }

                    if(strleft.equals("header offset")){
                        image.setHeaderoffset(Integer.parseInt(strright));
                        continue;
                    }

                    if(strleft.equals("file type")){
                        image.setFiletype(strright);
                        continue;
                    }

                    if(strleft.equals("data type")){
                        image.setDatatype(Byte.parseByte(strright));
                        continue;
                    }

                    if(strleft.equals("interleave")){
                        image.setInterleave(strright);
                        continue;
                    }

                    if(strleft.equals("Image Bin")){
                        image.setImagebin(Integer.parseInt(strright));
                        continue;
                    }

                    if(strleft.equals("band names")){
                        image.setBandnames(strright);
                        continue;
                    }

                    if(strleft.equals("wavelength")){

                        if(strleft.contains("}"))
                            image.setWavelength(strright);
                        else{
                            String wavelent=strright, temp;
                            while((temp = reader.readLine())!=null){
                                wavelent += temp;
                                if(temp.contains("}")){
                                    break;
                                }
                            }
                            wavelent.replaceAll("\r","");
                            wavelent.replaceAll("\n","");
                            image.setWavelength(wavelent);
                        }
                        continue;
                    }

                    if(strleft.equals("Time of exposure")){
                        image.setTimeofexposure(strright);
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
                        image.setGain(Double.parseDouble(strright));
                        continue;
                    }

                    if(strleft.equals("Exposure coefficient")){
                        image.setExposurecoefficient(Double.parseDouble(strright));
                        continue;
                    }

                    if(strleft.equals("LCTF Temperature")){
                        image.setLctftemperature(strright);
                        continue;
                    }

                    if (strleft.equals("byte order")){
                        image.setByteorder( Byte.parseByte(strright));
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


    public static Integer datatype2Bit(int datatype){
        switch (datatype) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 4;
            case 5:
                return 8;
            case 12:
                return 8;
        }
        return null;

    }
}