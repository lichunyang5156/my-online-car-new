package com.example.dailyTestServer;

import java.nio.ByteBuffer;

public class BufferTest {

    public static void main(String[] args) {
        ByteBuffer byteBuffer=ByteBuffer.allocate(12);
        System.out.println("初始化ByteBuffer，capacity："+byteBuffer.capacity() +"，position："+byteBuffer.position() +",limit："+byteBuffer.limit());

        for (int i = 0; i < 8; i++) {
            byteBuffer.put((byte) i);
        }
        System.out.println("【ByteBuffer写完数据】 capacity: " + byteBuffer.capacity() + " position: " + byteBuffer.position() + " limit: " + byteBuffer.limit());

        byteBuffer.flip();
        System.out.println("【ByteBuffer调flip】 capacity: " + byteBuffer.capacity() + " position: " + byteBuffer.position() + " limit: " + byteBuffer.limit());

        // 4、读数据，如果依次读取了6个字节，那现在position就指向下标为6的位置，limit不变
        for (int i = 0; i < 6; i++) {
            if (i == 3) {
                byteBuffer.mark();
            }
            byte b = byteBuffer.get();
        }
        System.out.println("【ByteBuffer读完数据】 capacity: " + byteBuffer.capacity() + " position: " + byteBuffer.position() + " limit: " + byteBuffer.limit());

        // 5、重置position
        byteBuffer.reset();
        System.out.println("【ByteBuffer调reset】 capacity: " + byteBuffer.capacity() + " position: " + byteBuffer.position() + " limit: " + byteBuffer.limit());

    }
}
