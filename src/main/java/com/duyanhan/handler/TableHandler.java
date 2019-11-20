package com.duyanhan.handler;

import com.duyanhan.bean.Cloumn;
import com.duyanhan.bean.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 数据库表处理
 */
public class TableHandler {

    // 需要排除的表的表名
    private List<String> tableExceptList = new ArrayList<String>();

    public void addExceptTable(String tableName) {
        tableExceptList.add(tableName);
    }

    // 查询数据库表
    private List<Table> queryDataTables(String driver, String url, String user, String password) {
        Connection conn = null;
        // 定义一个表对象列表用于存储当前访问的数据库中的所有的表数据对象
        List<Table> tables = new ArrayList<Table>();
        try {
            // 根据数据库驱动完全类名加载数据库驱动
            Class.forName(driver);
            // 获取数据库链接
            conn = DriverManager.getConnection(url, user, password);
            // 获取数据库元数据
            DatabaseMetaData dbmd = conn.getMetaData();
            // 从元数据中获取所有数据库表
            ResultSet resultSet = dbmd.getTables(null, null, null, new String[]{"TABLE"});
            // 遍历每一个表对象
            while (resultSet.next()) {
                // 创建一个Table对象，用来存储从元数据中取出的表名、表备注
                Table table = new Table();
                table.setCloumns(new ArrayList<Cloumn>());
                // 获取表名
                String tableName = resultSet.getString("TABLE_NAME");
                // 根据表名获取表备注（建表语句中的注释部分）
                String remarkes = getCommentByTableName(tableName, url, user, password);
                table.setTableName(tableName);
                table.setComment(remarkes);
                // 获取表字段
                ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
                while (rs.next()) {
                    // 获取字段名、字段备注、字段类型名
                    Cloumn cloumn = new Cloumn();
                    cloumn.setCloumnName(rs.getString("COLUMN_NAME"));
                    cloumn.setComment(rs.getString("REMARKS"));
                    cloumn.setCloumnType(rs.getString("TYPE_NAME"));
                    // 字段对象保存到表对象中
                    table.getCloumns().add(cloumn);
                }
                // 保存表对象至列表中
                tables.add(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tables;
    }

    // 获取实际要用来逆向生成model的表
    public List<Table> getTables(String driver, String url, String user, String password) {
        List<Table> dataTableList = queryDataTables(driver, url, user, password);
        // 如果数据库中有表
        if (null != dataTableList && dataTableList.size() != 0) {
            if (null != tableExceptList && tableExceptList.size() == 0) {
                // 查看被排除表列表
                for (String tableName : tableExceptList) {
                    Iterator<Table> it = dataTableList.iterator();
                    while (it.hasNext()) {
                        Table x = it.next();
                        if (x.getTableName().equals(tableName)) {
                            // 从获取的全部表列表中排除掉要被排除的表
                            it.remove();
                        }
                    }
                }
            }
        }
        return dataTableList;
    }

    /**
     * 根据表名查看建表语句中的注释部分
     * @param tableName
     * @return
     * @throws Exception
     */
    private static String getCommentByTableName(String tableName, String url, String user, String password) throws Exception {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE " + tableName);
        String comment = null;
        if (rs != null && rs.next()) {
            comment = parse(rs.getString(2));
        }
        rs.close();
        stmt.close();
        conn.close();
        return comment;
    }

    /**
     * 返回建表语句中的注释信息
     *
     * @param all
     * @return
     */
    private static String parse(String all) {
        String comment = null;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }
}
