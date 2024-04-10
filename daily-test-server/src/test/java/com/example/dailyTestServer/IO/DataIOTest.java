package com.example.dailyTestServer.IO;

import java.io.*;

public class DataIOTest {

    public static void main(String[] args) throws IOException {
        File file=new File("/Users/cherry/data/data.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        DataInputStream dis=new DataInputStream(fileInputStream);
        byte[] bytes=new byte[1024];
        int len = dis.read(bytes);
        while (len!=-1){
            for (int i = 0; i <len ; i++) {
                System.out.println(bytes[i]);
            }
            len = dis.read(bytes);
        }
        dis.close();
    }

    private void testDataOutputStream() throws IOException {
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
