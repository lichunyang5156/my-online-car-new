package com.example.dailyTestServer.IO;

import java.io.*;

public class FileInputStreamReadFileTest {

    public static void main(String[] args) throws IOException {
//       readSingleByte();
       readMultipleBytes();
    }


    /**
     * 单个字节读取
     * @throws IOException
     */
    private static void readSingleByte() throws IOException {
        //1、定义File类
        File file=new File("/Users/cherry/data/out.txt");
        //2、通过流读取File文件
        FileInputStream fileInputStream=new FileInputStream(file);
        //3、输出读取的内容
        int n = fileInputStream.read();
        while (n!=-1){
            System.out.println(n);
            n=fileInputStream.read();
        }
        //4、读取完成，关闭流
        fileInputStream.close();
    }

    /**
     * 多个字节同时读取
     * @throws IOException
     */
    private static void readMultipleBytes() throws IOException {
        //1、定义File类
        File file=new File("/Users/cherry/data/out.txt");
        //2、通过流读取File文件
        FileInputStream fileInputStream=new FileInputStream(file);
        //3、输出读取的内容
        byte[] bytes=new byte[1024*8];
        int len ;
        while ((len=fileInputStream.read(bytes))!=-1){
            for (int i = 0; i < len; i++) {
                System.out.println(bytes[i]);
            }
        }
        //4、读取完成，关闭流
        fileInputStream.close();
    }
}
