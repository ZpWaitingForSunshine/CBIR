package com.njust.cbir.web.controller;

import java.io.*;
import java.util.Scanner;

class test {
    public static void main(String[] args) throws Exception {

        //获取这个类的路径path
//        String path = "/home/hadoop/c350bip";
//
//        InputStream inputStream = new FileInputStream(new File(path));
//        byte tempbyte[] = new byte[2];
//        while ((inputStream.read(tempbyte)) != -1){
//            System.out.println((int)(tempbyte[0] & 0xff | tempbyte[1] << 8));
//            inputStream.skip(191  * 2);
//        }
//        inputStream.close();

        Scanner scanner = new Scanner(System.in);
        int a[] = new int[1000];
        int i = 0;
        while(scanner.hasNextInt()){
            a[i] = scanner.nextInt();
            i ++;
        }
        System.out.println(a[1]);
    }
}
