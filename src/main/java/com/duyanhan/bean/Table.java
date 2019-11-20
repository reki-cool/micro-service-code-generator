package com.duyanhan.bean;

import com.duyanhan.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Table implements Serializable {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表备注
     */
    private String comment;
    /**
     * 表列信息
     */
    private List<Cloumn> cloumns;
    /**
     * 根据表名获取类名
     * 过程：去掉表名下划线，并且每个单词首字母大写
     * @return
     */
    public String getClassName(){
        return  StringUtils.captureName(StringUtils.putOffUnderline(this.tableName));
    }
}
