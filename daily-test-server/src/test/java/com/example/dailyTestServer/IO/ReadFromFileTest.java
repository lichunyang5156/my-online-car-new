package com.example.dailyTestServer.IO;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFileTest {

    public static void main(String[] args) throws IOException {
        readSingleChar();
        readMultipleChars();
    }

    /**
     * 一次读取一个字符
     * @throws IOException
     */
    private static void readSingleChar() throws IOException {
        //1、定义File类
        File file=new File("/Users/cherry/data/out.txt");
        //2、通过流读取File文件
        FileReader fileReader=new FileReader(file);
        //3、输出读取的内容
        int n;
        while ((n=fileReader.read())!=-1){
            System.out.println((char)n);
        }
        //4、读取完成，关闭流
        fileReader.close();
    }

    /**
     * 一次读取多个字符
     * @throws IOException
     */
    private static void readMultipleChars() throws IOException {
        //1、定义File类
        File file=new File("/Users/cherry/data/out.txt");
        //2、通过流读取File文件
        FileReader fileReader=new FileReader(file);
        //3、输出读取的内容
        int n;
        char[] chars=new char[5];
        while ((n=fileReader.read(chars)) !=-1){
            //todo 这里需要注意，当读取的字符不够数组chars的长度的时候，新读取的字符只会替换数组对应位置的字符，而后面的字符不会清空
            //todo 这就导致，可能数组chars中的数据还存储了上一次读取的数据
            //todo 所以需要通多读取的索引值来遍历输出结果
            for (int i = 0; i < n; i++) {
                System.out.println(chars[i]);
            }
        }
        //4、读取完成，关闭流
        fileReader.close();
    }
}
