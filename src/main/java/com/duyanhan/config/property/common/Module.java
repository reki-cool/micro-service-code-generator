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
public class Module {
    /**
     * 实体类所在包的完整名称
     */
    private String entityPackageName;
    /**
     * 实体类文件存放路径
     */
    private String entitySavePath;
}
