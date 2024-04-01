package com.example.dailyTestServer.IO;

import java.io.*;

public class DataIOTest {

    public static void main(String[] args) throws IOException {
        File file=new File("/Users/cherry/data/data.txt");
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        DataOutputStream dos=new DataOutputStream(fileOutputStream);
        dos.writeUTF("你好");
        dos.writeBoolean(false);
        dos.writeInt(32);
        dos.writeUTF("啊哈哈");

        dos.close();
    }
}
