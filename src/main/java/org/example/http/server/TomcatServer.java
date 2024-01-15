package org.example.http.server;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;
import org.example.http.server.tomcat.RequestInfoServlet;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

public class TomcatServer {


    public static void main(String[] args) throws LifecycleException {
        //创建tomcat对象
        ServerProperties.Tomcat tomcat = new ServerProperties.Tomcat();
//        //设置端口
//        tomcat.setPort(8080);
//        //添加工程项目
//        Context context = tomcat.addContext("/", null);
//        //注册servlet
//        Wrapper wrapper = Tomcat.addServlet(context, "helloServlet", new RequestInfoServlet());
//        context.addServletMappingDecoded("/hello", "helloServlet");
//        //添加映射servlet
//        //启动
//        tomcat.start();
//        tomcat.getServer().await();
//        System.out.println("Tomcat started");
    }

}

