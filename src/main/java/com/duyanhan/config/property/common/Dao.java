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
public class Dao {
    /**
     * Mapper接口所在包的完整名称
     */
    private String mapperPackageName;
    /**
     * Mapper接口文件存放路径
     */
    private String mapperInterfaceSavePath;
    /**
     * Mapper接口对应的SQL实现XML文件存放路径
     */
    private String mapperXMLSavePath;
}
