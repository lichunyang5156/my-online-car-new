package com.example.dailyTestServer.BIO;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

@Slf4j
public class BIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket();
        socket.connect(new InetSocketAddress("192.168.1.1",8090));
        log.info("client connect finished");
        InputStream in = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        OutputStream out = socket.getOutputStream();
        while(true){
            String line = reader.readLine();
            if(line != null ){
                byte[] bb = line.getBytes();
                for (byte b : bb) {
                    out.write(b);
                }
            }
        }

    }
}
