package com.example.dailyTestServer.NIO;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class NIOClientA {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        try {
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
            log.info("client connect finished");
            ByteBuffer wrap = ByteBuffer.wrap("hello".getBytes(StandardCharsets.UTF_8));
            socketChannel.write(wrap);
            log.info("client send finish");
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
