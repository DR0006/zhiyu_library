package view.register;

import sqlconnection.SqlConnection;
import tools.MyFont;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Register extends JFrame {
    Box hbox, vbox;
    String userCode;
    SqlConnection conn;
    JButton bt1, bt2;
    String pan;
    String position;
    public final String code = "520";
    JTextField jTextField1, jTextField2, jTextField3;
    JPasswordField passwordField1, passwordField2;


    public Register() {
        super("注册窗口");
        registerWindow();

    }

    private void registerWindow() {
        ImageIcon image1 = new ImageIcon("image/01.png");
        JLabel label1 = new JLabel(image1);
        JLabel label2 = new JLabel("请您输入账号");
        JLabel label3 = new JLabel("请您输入密码");
        JLabel label4 = new JLabel("再次输入密码");
        JLabel label5 = new JLabel(new ImageIcon("image/05.png"));
        JLabel label6 = new JLabel(new ImageIcon("image/05.png"));
        JLabel label9 = new JLabel(new ImageIcon("image/10.png"));
        JLabel label10 = new JLabel(new ImageIcon("image/09.png"));


        JLabel label7 = new JLabel("注册授权码:");
        JLabel label8 = new JLabel("输入你的职位");

        JLabel label11 = new JLabel("注:职位填学生或管理员");

        //
        bt1 = new JButton("注册");
        bt2 = new JButton("取消");

        //
        bt1.addActionListener(e -> {
            System.out.println("注册按钮被点击");
            Ioo1();
        });
        bt2.addActionListener(e -> {
            System.out.println("退出按钮被点击");
            Ioo2();
        });

        //
        hbox = Box.createHorizontalBox();
        hbox.add(bt1);
        hbox.add(bt2);

        jTextField1 = new JTextField(15);
        jTextField2 = new JTextField(15);
        jTextField3 = new JTextField(15);

        passwordField1 = new JPasswordField(15);
        passwordField2 = new JPasswordField(15);
        label2.setFont(MyFont.F2);
        label3.setFont(MyFont.F2);
        label4.setFont(MyFont.F2);
        label7.setFont(MyFont.F2);
        label8.setFont(MyFont.F2);
        label11.setFont(MyFont.F2);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField3.setBorder(BorderFactory.createLoweredBevelBorder());

        passwordField2.setBorder(BorderFactory.createLoweredBevelBorder());
        passwordField1.setBorder(BorderFactory.createLoweredBevelBorder());
        //
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(jTextField1);
        panel2.add(label5);
        panel2.add(label3);
        panel2.add(passwordField1);
        panel3.add(label6);
        panel3.add(label4);
        panel3.add(passwordField2);
        panel4.add(label9);
        panel4.add(label7);
        panel4.add(jTextField2);
        panel5.add(label10);
        panel5.add(label8);
        panel5.add(jTextField3);
        panel5.add(label11);

        vbox = Box.createVerticalBox();

        vbox.add(panel1);
        vbox.add(panel2);
        vbox.add(panel3);
        vbox.add(panel4);
        vbox.add(panel5);
        vbox.add(hbox);

        this.setSize(350, 350);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(vbox);
        this.setVisible(true);


    }

    public static void main(String[] args) {
        new Register();
    }

    private void Ioo1() {
        String userName = jTextField1.getText().trim();
        String password1 = String.valueOf(passwordField1.getPassword());
        String password2 = String.valueOf(passwordField2.getPassword());
        position = jTextField3.getText().trim();
        userCode = jTextField2.getText().trim();
        if (!password2.equals(password1)) {
            System.out.println("两次密码不同");
            JOptionPane.showMessageDialog(this, "两次输入的密码不相同！");
        }
        //判断文本是否为空
        if (userName.equals("") || password1.equals("") || password2.equals("") || position.equals("") || userCode.equals("")) {
            JOptionPane.showMessageDialog(this, "请补全所有信息");
        }
        //授权码符合
        if (userCode.equals(code)) {
            //sql查询语句
            String sql1 = "SELECT * FROM login WHERE userName='" + userName + "'";
            conn = new SqlConnection();//建立连接
            ResultSet rs = conn.sqlQuery(sql1);//返回结果集
            try {
                //利用while循环获取
                while (rs.next()) {
                    pan = rs.getString(1);  //得到Id这一列即第一列的内容赋值给pan
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //判断所添加的用户账号是否被占
            if (pan == null) {
                //pan为空说明没找到这个Id，即此编号没被使用
                //sql插入语句
                String sql2 = "INSERT INTO login (userName,password,position) VALUES('" + userName + "','" + password1 + "','" + position + "')";
                //数据库操作
                SqlConnection conn = new SqlConnection();
                conn.sqlUpdate(sql2);

                System.out.println("注册成功");
                //释放数据库资源
                conn.closeSql();
                this.dispose();
            } else {
                //pan不为空即此编号被占用，提示相关内容
                JOptionPane.showMessageDialog(this, "抱歉，您注册的用户名已被注册!");
            }

        } else {
            JOptionPane.showMessageDialog(this, "授权码错误，请联系管理员");
        }
    }

    private void Ioo2() {
        this.dispose();
    }
}

