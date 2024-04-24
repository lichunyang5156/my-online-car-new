package com.example.dailyTestServer.NIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class NIOFileChannelTest {
    private static final String path="/Users/cherry/data/nioFileOut.txt";

    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");

        System.out.println("===========堆外映射写=============");

        FileChannel rafChannel = raf.getChannel();
        MappedByteBuffer map = rafChannel.map(FileChannel.MapMode.READ_WRITE, 0, 4094);
        map.put("@@@LCY".getBytes());  //不是系统调用，  但是因为这个map是和内存映射的，所以数据会到达内核的pagecache

        System.out.println("========map--put=======");
        System.in.read();


        //=======================================ByteBuffer+文件IO
        raf.seek(0);
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(8192);

        int read = rafChannel.read(byteBuffer);
        System.out.println(byteBuffer);//想ByteBuffer写数据
        byteBuffer.flip();//切换到读取数据状态
        System.out.println(byteBuffer);//读取ByteBuffer数据状态

        for (int i = 0; i < byteBuffer.limit(); i++) {
//            Thread.sleep(200);
            System.out.print(((char)byteBuffer.get(i)));
        }
        System.out.println("========ByteBuffer+文件IO=======");

    }

    private static void normalWrite() throws IOException {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");
        System.out.println("==========普通写==============");
        raf.write("hello LCY\n".getBytes(StandardCharsets.UTF_8));
        raf.write("hello JMJ\n".getBytes(StandardCharsets.UTF_8));
        System.in.read();
    }

    private static void randomWrite()throws IOException {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");

        System.out.println("===========随机写=============");
        raf.seek(4);
        raf.write("ooxx".getBytes(StandardCharsets.UTF_8));
        System.in.read();
    }


}
