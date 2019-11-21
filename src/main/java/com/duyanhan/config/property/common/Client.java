package com.duyanhan.config.property.common;

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
public class Client {
    /**
     * 远程调用接口所在包的完整名称
     */
    private String clientPackageName;
    /**
     * 远程调用接口文件存放路径
     */
    private String clientSavePath;
    /**
     * 远程调用接口对应的调用失败应变类所在包的完整名称
     */
    private String clientFallBackPackageName;
    /**
     * 远程调用接口对应的调用失败应变类文件存放路径
     */
    private String clientFallBackSavePath;
    /**
     * 远程调用接口对日志级别配置类所在包的完整名称
     */
    private String clientConfigPackageName;
    /**
     * 远程调用接口对日志级别配置类文件存放路径
     */
    private String clientConfigSavePath;


}