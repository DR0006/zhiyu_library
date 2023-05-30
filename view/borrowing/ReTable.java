package view.borrowing;

/*
    此类为借阅书籍的表格模板
 */
import sqlconnection.SqlConnection;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ReTable extends AbstractTableModel {
    //相关定义
     final Vector<Vector<String>> rowData;
    Vector<String> row;
    final Vector<String> columnNames;

    //定义一个记录集，用于接收从数据库返回来的记录集
    final ResultSet rs;

    //定义一个用于连接数据库的对象
    final SqlConnection conn;
    public ReTable(String sql) {

        if (sql == null) {
            //sql语句 查询bookreturn表格内容
            sql = "SELECT *FROM bookreturn";
        }
        //表头
            columnNames = new Vector<>();
            columnNames.add("书的编号");
            columnNames.add("书名");
            columnNames.add("借阅学生姓名");
            columnNames.add("借阅学生班级");
            columnNames.add("借阅学生学号");
            columnNames.add("借阅天数");
        //
        rowData = new Vector<>();
        //建立连接
        conn = new SqlConnection();
        rs = conn.sqlQuery(sql);
        //把返回的结果集的数据添加到Vector中
        try {
            while (rs.next()) {
                row = new Vector<>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                rowData.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //相关重写
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
