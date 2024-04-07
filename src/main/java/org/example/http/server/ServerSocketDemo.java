package org.example.http.server;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerSocketDemo {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server is running on port 8080...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                StringBuilder sb = new StringBuilder();
                sb.append("Client connected: \t\t" + clientSocket.getInetAddress()).append("\n");

                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    OutputStream out = clientSocket.getOutputStream();

                    StringBuilder request = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null && !line.isEmpty()) {
                        request.append(line).append("\r\n");
                    }

                    sb.append("Received request:\t\t" + request).append("\n");

                    // Parse request to extract method, headers, body, params, cookies, etc.
                    String[] requestLines = request.toString().split("\r\n");
                    String method = requestLines[0].split(" ")[0];
                    Map<String, String> headers = new HashMap<>();
                    Map<String, String> params = new HashMap<>();
                    String body = "";

                    for (int i = 1; i < requestLines.length; i++) {
                        String[] header = requestLines[i].split(": ");
                        if (header.length == 2) {
                            headers.put(header[0], header[1]);
                        }
                    }

                    sb.append("Method:\t\t" + method);
                    sb.append("Headers:\t\t" + headers);
                    sb.append("Params:\t\t" + params);
                    sb.append("Body:\t\t" + body);

                    // Prepare response JSON
                    String jsonResponse = "{\"method\": \"" + method + "\", \"headers\": " + headers.toString() + ", \"params\": " + params.toString() + ", \"body\": \"" + body + "\"}";

                    // Send response
                    String response = "HTTP/1.1 200 OK\r\n" +
                            "Content-Type: application/json\r\n" +
                            "Content-Length: " + jsonResponse.length() + "\r\n\r\n" +
                            jsonResponse;
                    out.write(response.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
