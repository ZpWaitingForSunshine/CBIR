package com.njust.hsicloud.core.assist.help;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Parse {
    public static void txtToXML(String txtPath)throws IOException {
    	File file = new File(txtPath);
        
        Document document = DocumentHelper.createDocument();
         
         
        Element list = document.addElement("timetable");
         
         
        BufferedReader br = new BufferedReader(new FileReader(file));
         
        String line = br.readLine();
         
        while(line!=null)
        {
            String temp[]  = line.split("		");
            Element log = list.addElement("TaskInfo"); 
            Element CloudletID = log.addElement("CloudletID");
            Element STATUS = log.addElement("STATUS");
            Element DatacenterID = log.addElement("DatacenterID ");
            Element VMID = log.addElement("VMID");
            Element Time = log.addElement("Time ");
            Element StartTime = log.addElement("StartTime ");
            Element FinishTime = log.addElement("FinishTime");
        
            for(int i = 0;i<temp.length;i++)
            {
            	CloudletID.setText(temp[0]);
            	STATUS.setText(temp[1]);
            	DatacenterID.setText(temp[2]);
            	VMID.setText(temp[3]);
            	Time.setText(temp[4]);
            	StartTime.setText(temp[5]);
            	FinishTime.setText(temp[6]);

            }
            line = br.readLine();
        }
        //����xml�ĸ�ʽ�ͱ����ʽ
        OutputFormat format = OutputFormat.createPrettyPrint(); 
        format.setEncoding("UTF-8");
        Writer filewriter = new FileWriter("C:\\Users\\think\\Desktop\\timetable.xml");
        XMLWriter xmlWriter = new XMLWriter(filewriter);
        xmlWriter.setEscapeText(false);
        xmlWriter.write(document);
        xmlWriter.close();
    }


    public static void main(String[] args) throws IOException {
		Parse.txtToXML("C:/Users/think/Desktop/output.txt");
	}

}
