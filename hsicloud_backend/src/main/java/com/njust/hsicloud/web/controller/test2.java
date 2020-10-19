package com.njust.hsicloud.web.controller;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import com.njust.hsicloud.core.assist.runc.RemoteShellTool;
import com.njust.hsicloud.core.assist.runc.sqloperaters;
import com.njust.hsicloud.core.util.runmain;
import org.apache.commons.lang3.StringUtils;
import org.apache.ivy.plugins.repository.ssh.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
public class test2 {

    public static void main(String[] args) throws Exception{
//        RemoteShellTool t=new RemoteShellTool();
//        Connection conn= RemoteShellTool.login("10.10.10.76","hadoop","admin");
//        String cmd="java -jar /home/hadoop/data/g_pso.jar /home/hadoop/data/result.xml /home/hadoop/data/result11.xml 4 1";
//        String res=t.execute(conn,cmd);
//        System.out.println(res);
        String[] vms = {"Slave1","Slave2","Slave3","Slave4"};

            runmain tool = new runmain("10.37.129.10", "root",
                    "admin", "utf-8");
            runmain tool2=new runmain("10.10.10.101", "root",
                    "admin", "utf-8");
            String result1= tool2.exec("sh stop.sh");
            System.out.println(result1);
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException ie){}
            for(int i=0;i<vms.length;i++){
                String res=tool.exec("source /root/keystonerc_435;openstack server delete"+vms[i]);
                System.out.println(res);
            }



    }
}
