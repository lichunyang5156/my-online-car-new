package com.example.dailyTestServer.IO;

import java.io.*;
import java.nio.channels.FileChannel;

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
     * 多个字节同时读取和写入
     * @throws IOException
     */
    private static void readMultipleBytes() throws IOException {
        //1、定义File类
        File file=new File("/Users/cherry/data/out.txt");
        //2、通过流读取File文件
        FileInputStream fileInputStream=new FileInputStream(file);

        File out=new File("/Users/cherry/data/out1.txt");

        FileOutputStream fileOutputStream=new FileOutputStream(out);
        //3、输出读取的内容
        byte[] bytes=new byte[1024*8];
        int len ;
        while ((len=fileInputStream.read(bytes))!=-1){
            fileOutputStream.write(bytes,0,len);
        }
        //4、读取完成，关闭流
        fileOutputStream.close();
        fileInputStream.close();

        FileChannel channel = fileInputStream.getChannel();


    }
}
