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
public class Database {
    /**
     * 数据库驱动
     */
    private String driver;
    /**
     * 数据库链接
     */
    private String url;
    /**
     * 数据库用户名
     */
    private String user;
    /**
     * 数据库密码
     */
    private String password;
}
