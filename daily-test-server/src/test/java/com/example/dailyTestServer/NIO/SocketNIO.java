package com.example.dailyTestServer.NIO;


import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class SocketNIO {

    public static void main(String[] args) throws IOException {
        LinkedList<SocketChannel> list=new LinkedList<>();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9090));
        serverSocketChannel.configureBlocking(false);//todo 设置serverSocketChannel为非阻塞模式，表示在收到连接前不会阻塞在 accept 处，而是继续执行后续逻辑
        while (true){
            SocketChannel client = serverSocketChannel.accept();
            if (client==null){
                System.out.println("没有连接……");
            }else {
                client.configureBlocking(false);//todo 将socket设置为阻塞模式，表示当前客户端传输数据完成之前不会阻塞在read阶段，而是尝试读取其他客户端的数据
                int port = client.socket().getPort();
                System.out.println("client...port:"+port);
                list.add(client);
            }

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
            for (SocketChannel socketChannel : list) {
                int readLen = socketChannel.read(byteBuffer);
                if (readLen>0){
                    byteBuffer.flip();
                    byte[] aaa = new byte[byteBuffer.limit()];
                    byteBuffer.get(aaa);

                    String string = new String(aaa);
                    System.out.println("receive "+socketChannel.socket().getPort() + "message : " + string);
                    byteBuffer.clear();
                }
            }
        }

    }

}
