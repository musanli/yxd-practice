package org.example.http.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class HttpServerDemo {
    public static void main(String[] args) throws IOException {
        int port = 8080; // 定义服务端口
        runServer(port);
    }

    public static void runServer(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(Executors.newFixedThreadPool(10)); // 使用线程池处理连接
        server.start();
        System.out.println("服务器已启动，监听端口 " + port);
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 解析HTTP请求
            String requestMethod = exchange.getRequestMethod();
            System.out.println("Request Method: " + requestMethod);

            String requestURI = exchange.getRequestURI().toString();
            System.out.println("Request URI: " + requestURI);

            Headers requestHeaders = exchange.getRequestHeaders();
            requestHeaders.forEach((key, values) -> {
                System.out.println("Request Headers: \tKey=" + key+"\tValues:\t"+ values);

            });

            // 读取请求体
            String requestBody = "";
            if ("POST".equalsIgnoreCase(requestMethod) || "PUT".equalsIgnoreCase(requestMethod)) {
                // 读取请求体
                // 可以根据Content-Type判断是什么类型的请求体（比如JSON、表单等），然后使用相应的方式读取
                // 这里假设是文本类型的请求体
                try (InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                     BufferedReader br = new BufferedReader(isr)) {
                    StringBuilder requestBodyBuilder = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        requestBodyBuilder.append(line);
                    }
                    requestBody = requestBodyBuilder.toString();
                }
            }
            System.out.println("Request Body: " + requestBody);


            // 读取请求参数（对于POST请求）
            String requestParams = "";
            if ("POST".equalsIgnoreCase(requestMethod)) {
                Map<String, String> params = parseQuery(exchange.getRequestURI().getQuery());
                requestParams = params.toString();
            }
            // 打印请求信息到控制台
            System.out.println("Request Parameters: " + requestParams);

            // 构造HTTP响应
            String response = "{\"name\":\"test\"}";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        }

        // 解析查询参数
        private Map<String, String> parseQuery(String query) {
            Map<String, String> params = new HashMap<>();
            if (query != null) {
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    int idx = pair.indexOf("=");
                    try {
                        String key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
                        String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
                        params.put(key, value);
                    } catch (UnsupportedEncodingException e) {
                        // Handle the exception
                    }
                }
            }
            return params;
        }
    }

}
