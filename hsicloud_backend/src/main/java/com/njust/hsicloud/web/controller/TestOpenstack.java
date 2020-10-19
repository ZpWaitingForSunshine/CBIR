package com.njust.hsicloud.web.controller;

import ch.ethz.ssh2.Connection;
import com.njust.hsicloud.core.assist.runc.RemoteShellTool;
import com.njust.hsicloud.core.util.runmain;

import java.util.concurrent.TimeUnit;

public class TestOpenstack {
    public static void main(String[] args) {
        String[] vms = {"Slave2","Slave3","Slave4"};
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
            String res=tool.exec("source /root/keystonerc_435;openstack server delete "+vms[i]);
            System.out.println(res);
        }



    }
}
