package org.example.http.server.tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class RequestInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求的详细信息并打印到控制台
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Request Protocol: " + request.getProtocol());
        System.out.println("Request Remote Address: " + request.getRemoteAddr());
        System.out.println("Request Remote Host: " + request.getRemoteHost());

        // 打印请求头部信息
        System.out.println("Request Headers:");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }

        // 打印请求参数
        System.out.println("Request Parameters:");
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            System.out.println(paramName + ": " + request.getParameter(paramName));
        }

        // 打印Cookie
        System.out.println("Request Cookies:");
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (javax.servlet.http.Cookie cookie : cookies) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }

        // 打印请求体（仅适用于POST请求）
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            System.out.println("Request Body:");
            System.out.println(request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual));
        }

        // 返回响应
        response.setContentType("text/plain");
        response.getWriter().println("Request details printed in console");
    }
}
