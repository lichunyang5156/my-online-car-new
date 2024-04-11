package com.example.dailyTestServer.NIO;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class NIOSelectorServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080),50);
        Selector selector = Selector.open();
        SelectionKey serverSocketKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){

            int count = selector.select();
            log.info("select event count:"+count);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()){
                    handleAccept(selectionKey);
                }else if (selectionKey.isWritable()){
                    handleRead(selectionKey);
                }
                iterator.remove();
            }
        }

    }

    private static void handleAccept(SelectionKey selectionKey) throws IOException{
        ServerSocketChannel serverSocketChannel= (ServerSocketChannel) selectionKey.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        if (Objects.nonNull(socketChannel)){
            log.info("receive connection from client.client:{}",socketChannel.getRemoteAddress());
            socketChannel.configureBlocking(false);
            Selector selector = selectionKey.selector();
            socketChannel.register(selector,SelectionKey.OP_READ);
        }
    }

    private static void handleRead(SelectionKey selectionKey) throws  IOException{
        SocketChannel socketChannel= (SocketChannel) selectionKey.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(8);
        int len = socketChannel.read(readBuffer);
        if (len>0){

        }
    }
}
