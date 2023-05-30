package sqlconnection;

import java.sql.*;

public class SqlConnection {
    Connection ct = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    final String driver;
    final String burl = "jdbc:mysql://localhost:3306/zhiyu_library";
    final String userName = "root";
    final String passWord = "root";
    public SqlConnection() {
        driver = "com.mysql.cj.jdbc.Driver";
    }


    //实现查询结果功能的方法
public ResultSet sqlQuery(String sql){
    try{
        //加载驱动
        //public static forName(String className) throws ClassNotFoundException
        //返回与给定字符串名称的类或接口相关联的类对象
        Class.forName(driver);

        //尝试建立与给定数据库URL的连接
        /*public static Connection getConnection(String url,String user,String password)throws SQLException
       */
        ct=DriverManager.getConnection(burl,userName,passWord);

        ps=ct.prepareStatement(sql);

        rs=ps.executeQuery();//查询结果

    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    return rs;
}

public void sqlUpdate(String sql){  //实现添加和修改的功能
    try{
        Class.forName(driver);
        ct=DriverManager.getConnection(burl,userName,passWord);
        ps= ct.prepareStatement(sql);
        //添加和新操作
        int i= ps.executeUpdate();//返回一个值，为1则为添加成功
        if(i==1){
            System.out.println("添加修改成功！");
        }
        else{
            System.out.println("添加/修改失败!");
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}

public void deleteStu(String sql){  //实现删除的功能
    try{
        Class.forName(driver);
        ct=DriverManager.getConnection(burl,userName,passWord);
        ps=ct.prepareStatement(sql);
        int result=ps.executeUpdate();

        if(result==1){
            System.out.println("数据删除成功");
        }
        else{
            System.out.println("数据删除失败");
        }
    } catch (SQLException | ClassNotFoundException e) {
        System.out.println("er");
    }
}
public void closeSql(){ //关闭数据库连接
    try{
        if(ct!=null)ct.close();  //立即释放此Connection对象的数据库和JDBC资源，而不是等待它们自动释放。
        if(ps!=null) ps.close();
        if(rs!=null)rs.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

}

}
