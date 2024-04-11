package com.example.dailyTestServer.NIO;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NIONonBlockingServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));
        List<SocketChannel> socketChannelList = new ArrayList<>();
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (Objects.nonNull(socketChannel)){
                log.info("receive connection from client.client:{}",socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(true);
                socketChannelList.add(socketChannel);
            }
            for (SocketChannel channel : socketChannelList) {
                ByteBuffer allocate = ByteBuffer.allocate(12);
                int readLen = channel.read(allocate);
//                log.info("receive message from client. client:{} message:{}",channel.getRemoteAddress(),
//                        new String(allocate.array(), 0, readLen,"UTF-8"));
                if (readLen>0){
                    log.info("receive message from client. client:{} message:{}",channel.getRemoteAddress(),
                            new String(allocate.array(), 0, readLen,"UTF-8"));
                }
                allocate.clear();
            }

            TimeUnit.MILLISECONDS.sleep(1000);
        }

    }
}
