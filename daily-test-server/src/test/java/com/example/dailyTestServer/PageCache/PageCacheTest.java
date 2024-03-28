package com.example.dailyTestServer.PageCache;

import java.io.*;

public class PageCacheTest {

    private static String path="/";
    private static byte data=new Byte("1234567890\n");

    public static void testBasicFileIO() throws IOException {
        File file= new File(path);
        FileOutputStream out=new FileOutputStream(file);
        while (true){
            out.write(data);
        }
    }

    public static void testBufferIO() throws IOException {
        File file= new File(path);
        BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(file));
        while (true){
            out.write(data);
        }
    }
}
