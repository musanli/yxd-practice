package org.example.http.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerWithServerSocket {
    public static void main(String[] args) throws Exception {
        int port = 8080; // 定义服务端口
        runServer(port);
    }

    public static void runServer(int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务器已启动，监听端口 " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null && line.isEmpty()) {
                        System.out.println("收到请求：" + line);
                    }
                    socket.getOutputStream().write("{\"name\":\"test\"}".getBytes());
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}