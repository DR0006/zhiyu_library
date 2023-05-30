package view.inquiry;

/*
    此类为查询书籍的表格模型
 */
import sqlconnection.SqlConnection;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class BookTable extends AbstractTableModel {
    //相关定义
    final Vector<Vector<String>> rowData;
    Vector<String> row;
    final Vector<String> columnNames;

    //定义一个记录集，用于接收从数据库返回来的记录集
    final ResultSet rs;

    //定义一个用于连接数据库的对象
    final SqlConnection conn;

    public BookTable(String sql) {
        if (sql==null) {
            //sql语句
            sql = "select * from books";
        }
        //表头
            columnNames = new Vector<>();
            columnNames.add("编号");
            columnNames.add("书名");
            columnNames.add("藏书数量");

            //创建表的记录集
            rowData = new Vector<>();

            //调用数据库连接
            conn = new SqlConnection();
            rs = conn.sqlQuery(sql);
            //获取数据库表格数据
            try {
                while (rs.next()) {
                    row = new Vector<>();
                    row.add(rs.getString(1));
                    row.add(rs.getString(2));
                    row.add(rs.getString(3));
                    rowData.add(row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    @Override
    //设置表的列名
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return this.columnNames.get(column);
    }

    @Override
    //得到的列数
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return this.columnNames.size();
    }

    //得到的行数
    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return this.rowData.size();
    }

    @Override
    //得到某行某列的值
    public Object getValueAt(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return ((Vector<?>)this.rowData.get(arg0)).get(arg1);
    }

}
