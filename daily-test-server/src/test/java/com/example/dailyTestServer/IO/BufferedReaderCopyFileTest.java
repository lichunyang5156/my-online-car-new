package com.example.dailyTestServer.IO;

import java.io.*;

/**
 * 使用BufferedReader、BufferedWriter实现文本文件复制
 */
public class BufferedReaderCopyFileTest {

    public static void main(String[] args) throws IOException {
        File inFile=new File("/Users/cherry/data/out.txt");
        File outFile=new File("/Users/cherry/data/out_copy.txt");

        FileReader reader=new FileReader(inFile);
        FileWriter writer=new FileWriter(outFile);

        BufferedReader br=new BufferedReader(reader);
        BufferedWriter bw=new BufferedWriter(writer);

        //todo 方式1：读取一个字符，输出一个字符：
        /*int n = br.read();
        while(n!=-1){
           bw.write(n);
           n = br.read();
        }*/

        //todo 方式2:利用缓冲数组：
        char[] chars=new char[8];
        int len = br.read(chars);
        while (len!=-1){
            bw.write(chars,0,len);
            len= br.read(chars);
        }

        bw.close();
        br.close();

    }
}
