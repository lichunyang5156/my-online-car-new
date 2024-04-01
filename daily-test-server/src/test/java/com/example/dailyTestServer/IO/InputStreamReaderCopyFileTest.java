package com.example.dailyTestServer.IO;

import java.io.*;

/**
 * InputStreamReader和OutputStreamWriter实现文件的复制
 *
 */
public class InputStreamReaderCopyFileTest {
    public static void main(String[] args) throws IOException {
        File inFile=new File("/Users/cherry/data/out.txt");
        File outFile=new File("/Users/cherry/data/out_copy01.txt");

        FileInputStream fileInputStream=new FileInputStream(inFile);
        InputStreamReader isr=new InputStreamReader(fileInputStream);

        FileOutputStream fileOutputStream=new FileOutputStream(outFile);
        OutputStreamWriter osw=new OutputStreamWriter(fileOutputStream);

        //todo 方式1:利用缓冲数组：
        char[] chars=new char[8];
        int len = isr.read(chars);
        while (len!=-1){
            osw.write(chars,0,len);
            len= isr.read(chars);
        }

        osw.close();
        isr.close();
    }
}
