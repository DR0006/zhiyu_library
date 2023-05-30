package view.login;

/*
    此类checkUser方法  判断登录者身份
 */
import sqlconnection.SqlConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    //相关定义
    int user;
    SqlConnection sqlConnection;
    ResultSet rs;
    String sql,pass,position;//

/*
    public int checkUser(String userId, String password) {
        if (userId.equals("root")) {
            if (password.equals("123456")) {
                System.out.println("管理员或班委");
                flag = 1;
            }
            if (password.equals("root")) {
                System.out.println("普通学生");
                flag = 2;
            }
        } else {
            flag = 0;
            System.out.println("error");
        }

        return flag;
    }
    */
    //检查登录者身份的方法
    public int checkUser(String userId ,String password){
        user=0;
        //sql语句
        sql="select * from login where userName='"+userId+"'";
        //建立连接
        sqlConnection=new SqlConnection();
        rs=sqlConnection.sqlQuery(sql);
        //获取数据
        try{
            while(rs.next()){
                pass=rs.getString(3);
                position=rs.getString(4);
            }
            if(password.equals(pass)){
                //职位为管理员，则user赋值为1
                  if(position.equals("管理员")){
                      user=1;
                      System.out.println("职位:"+position);
            }
                  //职位为学生，则赋值为2
                  else if(position.equals("学生")){
                      user=2;
                      System.out.println("职位:"+position);
                  }
            }
            //其他则赋值为3
            else{
                user=3;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //最后关闭数据库
        finally {
            sqlConnection.closeSql();
        }
        //返回user，便于借此判断登录者身份
        return user;
    }

}

