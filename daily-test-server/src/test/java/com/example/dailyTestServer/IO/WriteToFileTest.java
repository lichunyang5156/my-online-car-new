package com.example.dailyTestServer.IO;

import java.io.*;

public class WriteToFileTest {

    public static void main(String[] args) throws IOException {
        //1、定义File类
        File file=new File("/Users/cherry/data/out.txt");
        //2、通过流读取File文件
        FileReader fileReader=new FileReader(file);
        File of=new File("/Users/cherry/data/out01.txt");
        //todo true:表示在文档最后追加内容
        //todo false或不传：表示覆盖文档
        FileWriter fileWriter=new FileWriter(of);

        //3、输出读取的内容
        char[] chars=new char[5];
        int len= fileReader.read(chars);
        while (len !=-1){
            fileWriter.write(chars,0,len);
            len=fileReader.read(chars);
        }
        //4、读取完成，关闭流
        fileWriter.close();
        fileReader.close();
    }
}
