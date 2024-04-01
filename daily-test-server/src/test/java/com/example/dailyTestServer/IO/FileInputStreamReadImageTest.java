package com.example.dailyTestServer.IO;

import java.io.*;

public class FileInputStreamReadImageTest {

    public static void main(String[] args) throws IOException {
//        readMultipleBytes();
        readMultipleBytesByBuffered();
    }

    /**
     * 多个字节同时读取
     * @throws IOException
     */
    private static void readMultipleBytes() throws IOException {
        //1、定义File类
        File file=new File("/Users/cherry/data/dog.png");
        //2、通过流读取File文件
        FileInputStream fileInputStream=new FileInputStream(file);

        //1、定义File类
        File out=new File("/Users/cherry/data/dog01.png");
        //2、通过流读取File文件
        FileOutputStream fileOutputStream=new FileOutputStream(out);
        //3、输出读取的内容
        long begin = System.currentTimeMillis();
        byte[] bytes=new byte[1024];
        int len ;
        while ((len=fileInputStream.read(bytes))!=-1){
            fileOutputStream.write(bytes,0,len);
        }
        System.out.println(System.currentTimeMillis()-begin);
        //4、读取完成，关闭流
        fileOutputStream.close();
        fileInputStream.close();
    }

    /**
     * 多个字节同时读取
     * @throws IOException
     */
    private static void readMultipleBytesByBuffered() throws IOException {
        //1、定义File类
        File file=new File("/Users/cherry/data/dog.png");
        //2、通过流读取File文件
        FileInputStream fileInputStream=new FileInputStream(file);
        //3、输入流套管
        BufferedInputStream bufferedInputStreamStream=new BufferedInputStream(fileInputStream);

        //4、定义File类
        File out=new File("/Users/cherry/data/dog01.png");
        //5、绑定输出流
        FileOutputStream fileOutputStream=new FileOutputStream(out);
        //输出流套管
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
        //6、输出读取的内容
        long begin = System.currentTimeMillis();
        byte[] bytes=new byte[1024];
        int len ;
        while ((len=bufferedInputStreamStream.read(bytes))!=-1){
            bufferedOutputStream.write(bytes,0,len);
        }
        System.out.println(System.currentTimeMillis()-begin);
        //7、读取完成，关闭流
        bufferedOutputStream.close();
        bufferedInputStreamStream.close();
    }
}
