package com.example.dailyTestServer.NIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        String path="";
        FileChannel fileChannel= FileChannel.open(Paths.get(path), StandardOpenOption.WRITE,StandardOpenOption.READ);

        ByteBuffer byteBuffer=ByteBuffer.allocate(128);

        while (fileChannel.read(byteBuffer)!=-1){
            while (byteBuffer.hasRemaining()){
                byte b = byteBuffer.get();
                System.out.println(b);
            }
        }

    }
}
