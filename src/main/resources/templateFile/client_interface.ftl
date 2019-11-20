package ${clientPackage};
import ${entityPackage}.${table.className};

import ${clientFallBackPackage}.${table.className}ClientFallBack;
import java.util.List;
import java.util.Map;

import cn.dm.config.DmConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
/**
* Created by duyanhan
*/
@FeignClient(name = "${providerModule}", configuration = DmConfiguration.class, fallback = ${table.className}ClientFallBack.class)
public interface Rest${table.className}Client {

    /**
    * 根据ID查询${table.className}
    */
    @RequestMapping(value = "/get${table.className}ById",method = RequestMethod.POST)
    public ${table.className} get${table.className}ById(@RequestParam("id") Long id)throws Exception;

    /**
    * 根据多条件构成的Map集合查询${table.className}列表
    */
    @RequestMapping(value = "/get${table.className}ListByMap",method = RequestMethod.POST)
    public List<${table.className}>	get${table.className}ListByMap(@RequestParam Map<String,Object> param)throws Exception;

    /**
    * 根据多条件构成的Map集合查询${table.className}总数
    */
    @RequestMapping(value = "/get${table.className}CountByMap",method = RequestMethod.POST)
    public Integer get${table.className}CountByMap(@RequestParam Map<String,Object> param)throws Exception;

    /**
    * 新增${table.className}对象对应的数据库记录
    */
    @RequestMapping(value = "/qdtxAdd${table.className}",method = RequestMethod.POST)
    public Integer qdtxAdd${table.className}(@RequestBody ${table.className} ${lowerClassName})throws Exception;

    /**
    * 更新${table.className}对象对应的数据库记录
    */
    @RequestMapping(value = "/qdtxModify${table.className}",method = RequestMethod.POST)
    public Integer qdtxModify${table.className}(@RequestBody ${table.className} ${lowerClassName})throws Exception;
}
