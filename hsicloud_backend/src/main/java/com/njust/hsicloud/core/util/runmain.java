package com.njust.hsicloud.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

public class runmain {

    private Connection conn;
    private String ipAddr;
    private String charset = Charset.defaultCharset().toString();
    private String userName;
    private String password;

    public runmain(String ipAddr, String userName, String password,
                   String charset) {
        this.ipAddr = ipAddr;
        this.userName = userName;
        this.password = password;
        if (charset != null) {
            this.charset = charset;
        }
    }

    public boolean login() throws IOException {
        conn = new Connection(ipAddr);
        conn.connect(); // ????
        boolean res=conn.authenticateWithPassword(userName, password);
        System.out.println(res);
        return res ; // ???
    }

    public String exec(String cmds) {
        InputStream in = null;
        String result = "";
        try {
            if (this.login()) {
                Session session = conn.openSession(); // ???????
                session.execCommand(cmds);

                in = session.getStdout();
                result = this.processStdout(in, this.charset);
                session.close();
                conn.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return result;
    }

    public String processStdout(InputStream in, String charset) {

        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        try {
            while (in.read(buf) != -1) {
                sb.append(new String(buf, charset));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {


        runmain tool = new runmain("10.37.129.10", "root",
                "admin", "utf-8");
        runmain tool2=new runmain("10.10.10.166", "root",
                "admin", "utf-8");
        String[] Sname = {"Slave1","Slave2","Slave3","Slave4"};
        int n = 4;
        for (int i = 0; i <n ; i++) {
            String result1 = tool.exec("source /root/keystonerc_435;openstack server create --image Spark --security-group 435 --security-group cluster --flavor computer "+Sname[i]);
            System.out.println(result1);

        }
        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException ie){}

        String res2=tool2.exec("sh main.sh");
        System.out.println(res2);


    }
}
