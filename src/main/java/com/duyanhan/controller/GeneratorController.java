package com.duyanhan.controller;

import com.duyanhan.bean.Table;
import com.duyanhan.config.GeneratorProperties;
import com.duyanhan.config.property.Common;
import com.duyanhan.config.property.business.Business;
import com.duyanhan.config.property.business.modules.Database;
import com.duyanhan.config.property.business.modules.Provider;
import com.duyanhan.config.property.common.Client;
import com.duyanhan.config.property.common.Dao;
import com.duyanhan.config.property.common.Module;
import com.duyanhan.handler.MvcHandler;
import com.duyanhan.handler.TableHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GeneratorController {

    @Autowired
    private GeneratorProperties generatorProperties;

    @GetMapping("/")
    public GeneratorProperties test() {
        return generatorProperties;
    }

    @GetMapping("/generate")
    public String generateCode() {
        // 根据公有模块，查看公有模块中包含的各个子模块的信息
        Common common = generatorProperties.getCommon();

        // 公有模块中的module子模块
        Module module = common.getModule();
        // 实体类所在包完整名称
        String entityPackageName = module.getEntityPackageName();
        // 实体类文件保存目录
        String entitySavePath = module.getEntitySavePath();

        // 公有模块中的dao子模块
        Dao dao = common.getDao();
        // Mapper接口所在包完整名称
        String mapperPackageName = dao.getMapperPackageName();
        // Mapper接口文件保存目录
        String mapperInterfaceSavePath = dao.getMapperInterfaceSavePath();
        // Mapper接口对应SQL实现的XML保存目录
        String mapperXMLSavePath = dao.getMapperXMLSavePath();

        // 公有模块中的client子模块
        Client client = common.getClient();
        // 封装了各个操作DAO接口的远程调用接口所在包完整成名
        String clientPackageName = client.getClientPackageName();
        // 封装了各个操作DAO接口的远程调用接口文件保存目录
        String clientSavePath = client.getClientSavePath();
        // 封装了各个操作DAO接口的远程调用接口失败时的应变类所在包完整名称
        String clientFallBackPackageName = client.getClientFallBackPackageName();
        // Feign配置类所在包名
        String clientConfigPackageName = client.getClientConfigPackageName();
        // 封装了各个操作DAO接口的远程调用接口失败时的应变类文件保存目录
        String clientFallBackSavePath = client.getClientFallBackSavePath();


        // 业务列表，根据每个业务遍历关联的数据库
        List<Business> businesses = generatorProperties.getBusinesses();
        for (Business business : businesses) {
            // 查询当前业务关联的数据库信息
            Database database = business.getDatabase();
            String driver = database.getDriver();
            String url = database.getUrl();
            String user = database.getUser();
            String password = database.getPassword();

            // 查询当前业务包含的模块
            Provider provider = business.getProvider();
            // 当前业务的provider模块的模块名称
            String providerModuleName = provider.getModuleName();
            // 当前业务的provider模块的Service实现类所在包的完整名称
            String serviceImplementsPackageName = provider.getServiceImplementsPackageName();
            // 当前业务的provider模块的Service实现类文件保存目录
            String serviceImplementsSavePath = provider.getServiceImplementsSavePath();


            MvcHandler mvcHandler = new MvcHandler(generatorProperties.getTemplateFilePath());
            TableHandler tableHandler = new TableHandler();
            // 获取当前业务模块相关联数据库中的所有表
            List<Table> tables = tableHandler.getTables(driver, url, user, password);
            for (Table table : tables) {
                // ------------公有模块common的entity子模块
                // 逆向生成实体类代码文件
                mvcHandler.generateEntity(table, entityPackageName, entitySavePath);

                // ------------公有模块common的dao子模块
                // 逆向生成Mapper接口文件
                mvcHandler.generateMapperInterface(table, entityPackageName, mapperPackageName, mapperInterfaceSavePath);
                // 逆向生成Mapper接口SQL实现的XML文件
                mvcHandler.generateMapperXML(table, entityPackageName, mapperPackageName, mapperXMLSavePath);

                // ------------公有模块common的client子模块
                // 逆向生成Client接口文件：封装了各种操作数据访问对象（DAO）接口的远程调用接口（相当于单体应用中的Service）
                mvcHandler.generateClient(table, entityPackageName, clientPackageName, clientSavePath, clientFallBackPackageName, providerModuleName, clientConfigPackageName);
                // 逆向生成ClientFallBack类文件：当client接口方法调用失败时生效的应变类
                mvcHandler.generateClientFallBack(table, entityPackageName, clientPackageName, clientFallBackPackageName, clientFallBackSavePath);

                // ------------具体业务的provider模块
                // 逆向生成Provider类文件：是Client接口的具体实现（相当于单体应用中的ServiceImpl）
                mvcHandler.generateServiceImpl(table, entityPackageName, mapperPackageName, serviceImplementsPackageName, serviceImplementsSavePath);
            }
        }
        return "重复性代码生成完毕";
    }
}
