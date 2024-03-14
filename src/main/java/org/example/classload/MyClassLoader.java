package org.example.classload;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * springboot 配置类加载器 方式一
 * *      MyClassLoader myClassLoader = new MyClassLoader("D:\\");
 * *      Thread.currentThread().setContextClassLoader(myClassLoader);
 * *      SpringApplication.run(DemoApplication.class, args);
 * springboot 配置类加载器 方式二
 * *      SpringApplication application = new SpringApplication(DemoApplication.class);
 * *      MyClassLoader myClassLoader = new MyClassLoader("D:\\");
 * *      DefaultResourceLoader resourceLoader = new DefaultResourceLoader(myClassLoader);
 * *      application.setResourceLoader(resourceLoader);
 * *      application.run(args);
 */
public class MyClassLoader extends ClassLoader {

    //自定义加载器时传进一个路径名，就跟其他类加载器的查找范围一样，
    //当从顶层开始查找时，任务从上面扔到我们这，我们也将去这个位置找那个类，找不到就往下抛
    String filePath;

    //构造器
    public MyClassLoader(String filePath) {
        this.filePath = filePath;
    }

    //这是继承的方法，要求传入一个文件名 例如:Test,返回一个Class对象,其中实际调用的是difineClass
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //这个是加上个.class后缀
        String fileName = name + ".class";

        FileInputStream fi = null;
        ByteArrayOutputStream bos = null;
        byte[] bytes = null;

        try {
            fi = new FileInputStream(new File(filePath, fileName));
            bos = new ByteArrayOutputStream();
            int i = 0;
            while ((i = fi.read()) != -1) {
                bos.write(i);
                bos.flush();
            }
            bytes = bos.toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //最核心的方法，defineClass通过字节数组帮你创建了class对象
        return defineClass(name, bytes, 0, bytes.length);
    }

    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader("D:\\");
        try {
            Class<?> test = myClassLoader.loadClass("Test");
            if (test != null) {
                Object x = test.newInstance();
                Method getA = test.getDeclaredMethod("getA", null);
                //调用方法
                System.out.println(getA.invoke(x, null));
            } else {
                System.out.println("err");
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}


