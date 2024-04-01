package com.example.dailyTestServer.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/**
 * System提供的IO
 */
public class SystemIOTest {
    public static void main(String[] args) throws IOException {

        File file=new File("/Users/cherry/data/write.txt");

        FileOutputStream fileOutputStream=new FileOutputStream(file);

        Scanner sc=new Scanner(System.in);
        String s=null;
        while (!Objects.equals(s,"exit")){
             s = sc.next();
            System.out.println(s);
            fileOutputStream.write(s.getBytes(StandardCharsets.UTF_8));
        }

        fileOutputStream.close();

    }
}
