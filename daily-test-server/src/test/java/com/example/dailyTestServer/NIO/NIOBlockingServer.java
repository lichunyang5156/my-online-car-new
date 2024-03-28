package com.example.dailyTestServer.NIO;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
@Slf4j
public class NIOBlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(true);//设置SocketChannel为阻塞模式（默认就是阻塞模式）
        serverSocketChannel.bind(new InetSocketAddress(8080));
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        while (true){
            SocketChannel socketChannel=serverSocketChannel.accept();
            log.info("receive connection from client.client:{}",socketChannel.getRemoteAddress());
            socketChannel.configureBlocking(true);//设置SocketChannel为阻塞模式（默认就是）
            //如果读不到数据，会阻塞在这里，无法及时处理其他Channel的请求
            int length = socketChannel.read(byteBuffer);
            log.info("receive message from client. client:{} message:{}",socketChannel.getRemoteAddress(),new String(byteBuffer.array(),0,length,"UTF-8"));
            byteBuffer.clear();
        }
    }
}
