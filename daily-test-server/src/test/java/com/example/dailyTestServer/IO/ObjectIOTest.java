package com.example.dailyTestServer.IO;

import java.io.*;

public class ObjectIOTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        readObjectFromFile();
        People people=new People();
        people.setAge(18);
        people.setPassword("1234");
        System.out.println(people.toString());
    }

    private void writeObjectToFile() throws IOException {
        File file=new File("/Users/cherry/data/object.txt");
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        ObjectOutputStream oos=new ObjectOutputStream(fileOutputStream);
        oos.writeObject("你好");
        oos.close();
    }

    private static void readObjectFromFile() throws IOException, ClassNotFoundException {
        File file=new File("/Users/cherry/data/object.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        ObjectInputStream ois=new ObjectInputStream(fileInputStream);
        Object o = ois.readObject();
        System.out.println(o);
    }
}
