package com.duyanhan.config.property.business;


import com.duyanhan.config.property.business.modules.Database;
import com.duyanhan.config.property.business.modules.Provider;
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
public class Business {

    /**
     * 该业务的名称
     */
    private String businessName;
    /**
     * 该业务关联的数据库
     */
    private Database database;
    /**
     * 该业务对应的生产者模块
     */
    private Provider provider;

}
