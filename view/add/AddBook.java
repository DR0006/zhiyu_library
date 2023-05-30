package view.add;

/*
  此类实现添加图书的功能
 */

import sqlconnection.SqlConnection;
import tools.MyFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddBook extends JDialog implements ActionListener {
    //相关定义
    String Id,BookName,Number;
    SqlConnection conn;
    ResultSet rs;
    final JPanel panel1;
    final JPanel panel2;
    final JPanel panel3;
    final JLabel label1;
    final JLabel label2;
    final JLabel label3;
    final JTextField jt1;
    final JTextField jt2;
    final JTextField jt3;
    final JButton bt1;
    final JButton bt2;
    final JButton bt3;
    String pan;
    //构造函数
    public AddBook(Frame Main, String title, boolean model) {
        super(Main ,title,model);  //调用父类构造方法
        //创建三个标签
        label1=new JLabel("书   号:");
        label2=new JLabel("书   名:");
        label3=new JLabel("数   量:");
        //创建三个文本框
        jt1=new JTextField(15);
        jt2=new JTextField(15);
        jt3=new JTextField(15);
        //文本框设置边界效果
        jt1.setBorder(BorderFactory.createLoweredBevelBorder());
        jt2.setBorder(BorderFactory.createLoweredBevelBorder());
        jt3.setBorder(BorderFactory.createLoweredBevelBorder());

        //创建三个按钮
        bt1=new JButton("添加");
        bt2=new JButton("清除");
        bt3=new JButton("取消");
        //三个标签设置其字体
        label1.setFont(MyFont.F1);
        label2.setFont(MyFont.F1);
        label3.setFont(MyFont.F1);
        //按钮设置字体
        bt1.setFont(MyFont.F2);
        bt2.setFont(MyFont.F2);
        bt3.setFont(MyFont.F2);
        //点击按钮后的反应
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
        //创建面板，添加标签和文本框
        panel1=new JPanel();
        panel1.add(label1);
        panel1.add(jt1);
        //创建面板，添加标签和文本框
        panel2=new JPanel();
        panel2.add(label2);
        panel2.add(jt2);
        //创建面板，添加标签和文本框
        panel3=new JPanel();
        panel3.add(label3);
        panel3.add(jt3);
        //创建一个垂直盒子
        Box hBox1=Box.createHorizontalBox();
        hBox1.add(bt1);
        hBox1.add(bt2);
        hBox1.add(bt3);
        //创建一个水平盒子，在其添加三个面板和hBox1
        Box vBox=Box.createVerticalBox();
        vBox.add(panel1);
        vBox.add(panel2);
        vBox.add(panel3);
        vBox.add(hBox1);

        this.setContentPane(vBox);//设置面板内容
        this.setSize(400,250);//窗口大小
        this.getContentPane().setBackground(Color.LIGHT_GRAY);//背景颜色
        this.setLocationRelativeTo(null);//窗口默认生成在显示器中央
        this.setVisible(true);//设置窗口可见
    }
    //点击按钮反应的具体实现
    @Override
    public void actionPerformed(ActionEvent e) {
        //点击了按钮1（添加）
        if (e.getSource() == bt1) {
            System.out.println("添加按钮被点击");
            Id = jt1.getText().trim();
            BookName = jt2.getText().trim();
            Number = jt3.getText().trim();

            //判断文本是否为空
            if (Id.equals("") || BookName.equals("") || Number.equals("")) {
                JOptionPane.showMessageDialog(this, "请补全所有信息");
            }
            //sql查询语句
            String sql1="SELECT * FROM books WHERE Id='"+Id+"'";
            conn=new SqlConnection();//建立连接
            rs=conn.sqlQuery(sql1);//返回结果集

            try{
                //利用while循环获取
                while(rs.next()){
                    pan=rs.getString(1);  //得到Id这一列即第一列的内容赋值给pan
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //判断所添加的书的编号是否被占
            if(pan==null){
                //pan为空说明没找到这个Id，即此编号没被使用
                //sql插入语句
                String sql2="INSERT INTO books VALUES('"+Id+"','"+BookName+"','"+Number+"')";
                //数据库操作
                conn=new SqlConnection();
                conn.sqlUpdate(sql2);
                //释放数据库资源
                conn.closeSql();
                this.dispose();
            }
            else{
                //pan不为空即此编号被占用，提示相关内容
                JOptionPane.showMessageDialog(this,"抱歉，您所添加的书本的编号已被使用");
            }
        }
        //点击按钮2（清除）
        else if(e.getSource()==bt2){
            System.out.println("清除按钮被点击");
            //直接把三个文本框内容设置为空，起到清除效果
            jt1.setText(null);
            jt2.setText(null);
            jt3.setText(null);
        }
        //点击了按钮3（取消）
        else{
            //关闭这个界面
            System.out.println("取消按钮被点击");
            this.dispose();
        }
    }
}
