package com.duyanhan.util;

/**
 * Created by dm
 */
public class StringUtils {
    /**
     * ĳλ����ĸ��д
     *
     * @param name
     * @param indx
     * @return
     */
    public static String captureName(String name, int indx) {
        name = name.substring(0, indx) + name.substring(indx, indx + 1).toUpperCase() + name.substring(indx + 1);
        return name;
    }

    /**
     * 将单词首字母转换为大写
     *
     * @param name
     * @return
     */
    public static String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    /***
     * 将单词首字母转换为小写
     *
     * @param name
     * @return
     */
    public static String lowerName(String name) {
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }

    /***
     * 去掉字段的下划线，并且每个单词的首字母大写（除了第一个单词）
     *
     * @return
     */
    public static String putOffUnderline(String columnName) {
        StringBuffer fieldNameBuffer = null;
        String tempNameArray[] = columnName.split("_");
        for (int i = 0; i < tempNameArray.length; i++) {
            // 首字母不处理
            if (i == 0) {
                fieldNameBuffer = new StringBuffer(tempNameArray[i]);
            } else {
                fieldNameBuffer.append(captureName(tempNameArray[i]));
            }
        }
        return fieldNameBuffer.toString();
    }

    public static void main(String[] args) {
        String a = StringUtils.putOffUnderline("Aa_bb_cc");
        System.out.println(a);
    }
}
