package com.duyanhan.bean;

import com.duyanhan.constant.TypeConstant;
import com.duyanhan.util.StringUtils;
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
public class Cloumn {

    /**
     * 获取字段名称
     */
    private String cloumnName;
    /**
     * 获取字段备注
     */
    private String comment;
    /**
     * 获取字段类型
     */
    private String cloumnType;

    /**
     * 获取字段名称
     * @return
     */
    public String getFieldName() {
        return StringUtils.putOffUnderline(this.cloumnName);
    }

    /**
     * 获取该字段关联的Java类型
     * @return
     */
    public String getJavaType() {
        return TypeConstant.getJavaType(this.cloumnType);
    }

    /**
     * 获取该字段名的首字母大写驼峰命令
     * @return
     */
    public String getUpperCasecloumnName(){
        return StringUtils.captureName(getFieldName());
    }
}
