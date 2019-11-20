package com.duyanhan.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;

/**
 * Created by duyanhan
 */
public class FreeMarkerUtils {

    /**
     * 根据参数获取模板
     * @param templatePath 模板路径，例如数据库各表对应的实体类要存放的位置
     * @param templateFileName  模板文件名称，例如model.ftl等，但不包括后缀".ftl"，即model
     * @return
     */
    private static Template getTemplate(String templatePath, String templateFileName){
        Configuration configuration = new Configuration();
        Template template =null;
        try {
            configuration.setDirectoryForTemplateLoading(new File(templatePath));
            configuration.setObjectWrapper(new DefaultObjectWrapper());
            configuration.setDefaultEncoding("UTF-8");   //设置编码
            // 带后缀的模板文件名称
            template=configuration.getTemplate(templateFileName + ".ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return template;
    }

    /**
     * 根据参数生成文件
     * @param input 包含表名、包名、表名首字母小写的驼峰形式
     * @param templatePath  要存放生成的实体类的路径
     * @param templateFileName  用来生成代码的模板文件的名称（不包含后缀名，例如model.ftl就是model)
     * @param savePath
     * @param fileName
     */
    public static void genteratorFile(Map<String, String> input, String templatePath, String templateFileName, String savePath, String fileName){
        // 获取模板文件对象
        Template template=getTemplate(templatePath,templateFileName);
        // 创建目标文件对象（用来表示即将使用模板生成的文件）所在的目录对象
        File file = new File(savePath);
        if (!file.exists()) {
            // 如果目录不存在则创建
            file.mkdirs();
        }
        Writer writer = null;
        try {
            // 获取目标文件输出流
            writer=new OutputStreamWriter(new FileOutputStream(savePath+"\\"+fileName), "UTF-8");
            // input 是一个map集合，存储的都是模板文件中需要引用到的值，下面这个语句会使模板文件中引用的属性会被这些值替换，然后生成对应的目标内容写出到目标文件中
            template.process(input, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void appendgenteratorFile(Map<String, String> input, String template_path, String templateFileName, String savePath, String fileName){
        Template template=getTemplate(template_path,templateFileName);
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Writer writer = null;
        try {
            writer=new OutputStreamWriter(new FileOutputStream(savePath+"\\"+fileName,true), "UTF-8");
            template.process(input, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
