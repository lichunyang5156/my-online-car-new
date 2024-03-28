package com.example.dailyTestServer.NIO;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class NIOBlockingClient {


    @SneakyThrows
    public static void main(String[] args) {
        SocketChannel socketChannel=SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("127.0.0.1",8080));
        log.info("client connect finished");
        ByteBuffer writeBuffer=ByteBuffer.wrap("hello".getBytes(StandardCharsets.UTF_8));
        socketChannel.write(writeBuffer);
        ByteBuffer writeBuffer2=ByteBuffer.wrap("hello2".getBytes(StandardCharsets.UTF_8));
        socketChannel.write(writeBuffer2);
        log.info("client send finished");
    }
}
