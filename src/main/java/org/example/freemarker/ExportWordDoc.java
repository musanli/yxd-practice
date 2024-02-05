package org.example.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ExportWordDoc {
    public static void main(String[] args) {
        try {
            // 创建FreeMarker配置
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setClassForTemplateLoading(ExportWordDoc.class, "/org/example/freemarker");
            // 加载模板文件
            Template template = cfg.getTemplate("word_template.ftl");

            // 创建数据模型
            Map<String, Object> data = new HashMap<>();
            data.put("title", "Sample Word Document");

            // 生成Word文档
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.doc")));
            template.process(data, out);
            out.flush();
            out.close();

            System.out.println("Word document generated successfully.");
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
