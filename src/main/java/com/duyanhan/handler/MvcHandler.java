package com.duyanhan.handler;


import com.duyanhan.bean.Table;
import com.duyanhan.util.FreeMarkerUtils;
import com.duyanhan.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 作用：根据模板生成文件
 */
@Slf4j
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MvcHandler {

    // 获取模板路径
    private String templateFilePath;

// ---------module模块：包含的是与数据库记录一一对应的实体类

    /**
     * 逆向生成实体类文件（entity包）
     * 前提：所有数据库的表名都是小写形式，且单词间用下划线隔开
     *
     * @param table
     */
    public void generateEntity(Table table, String entityPackageName, String entitySavePath) {
        Map input = new HashMap();
        input.put("table", table);
        input.put("entityPackage", entityPackageName);
        // 处理表名，转换成驼峰形式，获取表名驼峰但首字母小写的形式
        input.put("lowerClassName", StringUtils.lowerName(table.getClassName()));
        // 根据表名获取类名的文件名
        String fileName = table.getClassName() + ".java";
        // 获取model保存路径
        String savePath = entitySavePath;
        String templateName = "entity_class";
        // 生成实体类文件
        FreeMarkerUtils.genteratorFile(input, templateFilePath, templateName, savePath, fileName);
    }

// ---------dao模块：包含的是直接用来操作数据库的接口和具体SQL实现的XML文件

    /**
     * 根据表信息生成对应的Mapper接口的XML文件
     *
     * @param table
     */
    public void generateMapperXML(Table table, String entityPackageName, String mapperPackageName, String mapperXMLSavePath) {
        Map input = new HashMap();
        input.put("table", table);
        input.put("entityPackage", entityPackageName);
        input.put("mapperPackage", mapperPackageName);
        input.put("lowerClassName", StringUtils.lowerName(table.getClassName()));
        String fileName = table.getClassName() + "Mapper" + ".xml";
        String savePath = mapperXMLSavePath + "//";
        String templateName = "mapper_xml";
        FreeMarkerUtils.genteratorFile(input, templateFilePath, templateName, savePath, fileName);
    }

    /**
     * 根据表信息生成对应的Mapper接口文件
     *
     * @param table
     */
    public void generateMapperInterface(Table table, String entityPackageName, String mapperPackageName, String mapperInterfaceSavePath) {
        Map input = new HashMap();
        input.put("table", table);
        input.put("entityPackage", entityPackageName);
        input.put("mapperPackage", mapperPackageName);
        input.put("lowerClassName", StringUtils.lowerName(table.getClassName()));
        String fileName = table.getClassName() + "Mapper" + ".java";
        String savePath = mapperInterfaceSavePath + "//";
        String templateName = "mapper_interface";
        FreeMarkerUtils.genteratorFile(input, templateFilePath, templateName, savePath, fileName);
    }

// ---------client模块：包含的是 封装了直接调用操作数据库接口 的远程调用接口 和 关联的远程调用失败时的应变类

    /**
     * 逆向生成各种远程调用接口文件（client包，提供给各个业务模块使用）
     *
     * @param table
     */
    public void generateClient(Table table, String entityPackageName, String clientPackageName, String clientSavePath, String clientFallBackPackageName, String providerModuleName, String clientConfigPackageName) {
        Map input = new HashMap();
        input.put("table", table);
        input.put("entityPackage", entityPackageName);
        input.put("clientPackage", clientPackageName);
        input.put("clientFallBackPackage", clientFallBackPackageName);
        input.put("clientConfigPackage", clientConfigPackageName);
        input.put("providerModule", providerModuleName);
        input.put("lowerClassName", StringUtils.lowerName(table.getClassName()));
        String fileName = "Rest" + table.getClassName() + "Client" + ".java";
        String savePath = clientSavePath + "//";
        String templateName = "client_interface";
        FreeMarkerUtils.genteratorFile(input, templateFilePath, templateName, savePath, fileName);
    }

    /**
     * 逆向生成各种远程调用接口失败时的应变类文件
     *
     * @param table
     */
    public void generateClientFallBack(Table table, String entityPackageName, String clientPackageName, String clientFallBackPackageName, String clientFallBackSavePath) {
        Map input = new HashMap();
        input.put("table", table);
        input.put("entityPackage", entityPackageName);
        input.put("clientPackage", clientPackageName);
        input.put("clientFallBackPackage", clientFallBackPackageName);
        input.put("lowerClassName", StringUtils.lowerName(table.getClassName()));
        String fileName = table.getClassName() + "ClientFallBack" + ".java";
        String savePath = clientFallBackSavePath + "//";
        String templateName = "client_fallback_class";
        FreeMarkerUtils.genteratorFile(input, templateFilePath, templateName, savePath, fileName);
    }

// ---------具体业务的provider模块：包含的是 封装了直接调用操作数据库接口 的远程调用接口 对应的实现类

    public void generateServiceImpl(Table table, String entityPackageName, String mapperPackageName, String serviceImplementsPackageName, String serviceImplementsSavePath) {
        Map input = new HashMap();
        input.put("table", table);
        input.put("entityPackage", entityPackageName);
        input.put("mapperPackage", mapperPackageName);
        input.put("serviceImplementsPackage", serviceImplementsPackageName);
        input.put("package", "templateFile");
        input.put("lowerClassName", StringUtils.lowerName(table.getClassName()));
        String fileName = "Rest" + table.getClassName() + "Service" + ".java";
        String savePath = serviceImplementsSavePath + "//";
        String templateName = "service_implements_class";
        FreeMarkerUtils.genteratorFile(input, templateFilePath, templateName, savePath, fileName);
    }


}
