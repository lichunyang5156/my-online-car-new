package com.example.dailyTestServer.BIO;

import org.junit.platform.commons.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServerMultiThreadTest {

    private final static ExecutorService threadPool= Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8090);
        System.out.println("step1: new ServerSocket(8090)");
        while (true) {
            Socket accept = serverSocket.accept();
            System.out.println("step2:port" + accept.getPort());
            threadPool.execute(()->{
                InputStream inputStream = null;
                try {
                    inputStream = accept.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line=bufferedReader.readLine())!=null) {
                        System.out.println("step3:" + line);
                    }
                    accept.close();
                    System.out.println("step4:客户端断开连接");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
