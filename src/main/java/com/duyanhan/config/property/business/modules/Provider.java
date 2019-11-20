package com.duyanhan.config.property.business.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Provider {

    /**
     * 当前业务的provider模块的名称
     */
    private String moduleName;
    /**
     * 当前业务的provider模块的ServiceImpl所在包完整名称
     */
    private String serviceImplementsPackageName;
    /**
     * 封装了数据访问接口的远程调用接口的具体实现文件（简单来讲，就是单体项目中的ServiceImpl类）的存放路径
     */
    private String serviceImplementsSavePath;
}
