package com.duyanhan.config;

import com.duyanhan.config.property.Common;
import com.duyanhan.config.property.business.Business;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "micro-services-module-generator-properties")
@Slf4j
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeneratorProperties {

    /**
     * 用来生成文件的模板所在路径
     */
    private String templateFilePath;

    /**
     * 公共模块
     */
    private Common common;

    /**
     * 各个业务模块
     */
    private List<Business> businesses = new ArrayList<>();
}
