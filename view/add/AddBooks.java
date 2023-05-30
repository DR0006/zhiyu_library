package view.add;

import sqlconnection.SqlConnection;
import tools.MyFont;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class AddBooks extends JFrame  implements ActionListener {


    int pan;
    SqlConnection conn;
    ResultSet rs;
    JLabel label1,label2,label3,label4;
    String name;
    JTextField tx1,tx2;
    JButton button1,button2,button3;
    JButton bt;
    JFileChooser chooser;
    JTextArea textArea;
    public AddBooks() {
        addMenu();

    }

    private void addMenu(){

        label1=new JLabel("书的编号:");
        label2=new JLabel("书籍名称:");
        label3=new JLabel("书籍简介:");

        label4=new JLabel("书籍图片:");

        label1.setBackground(Color.cyan);
        label2.setBackground(Color.CYAN);
        label3.setBackground(Color.CYAN);
        label4.setBackground(Color.CYAN);

        tx1=new JTextField(30);
        tx2=new JTextField(30);

        textArea=new JTextArea();
        textArea.setSize(200,300);

        button1=new JButton("添加");
        button2=new JButton("清除");
        button3=new JButton("取消");

        bt=new JButton("点击上传图片");
        bt.setBackground(Color.pink);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        bt.addActionListener(this);


        button1.setFont(MyFont.F1);
        button2.setFont(MyFont.F1);
        button3.setFont(MyFont.F1);
        bt.setFont(MyFont.F1);

        label1.setFont(MyFont.F2);
        label2.setFont(MyFont.F2);
        label3.setFont(MyFont.F2);
        label4.setFont(MyFont.F2);


        //创建一个BackImage对象
        BackImage bi= new BackImage();
        //设定图片的位置
        bi.setBounds(0,0,600,600);


        label1.setBounds(300,100,100,40);
        label2.setBounds(300,150,100,40);
        label4.setBounds(300,200,100,40);
        label3.setBounds(300,250,100,40);
        bt.setBounds(360,200,200,30);
        button1.setBounds(570,250,70,30);
        button2.setBounds(570,280,70,30);
        button3.setBounds(570,310,70,30);
        tx1.setBounds(360,100,200,30);
        tx2.setBounds(360,150,200,30);
        textArea.setBounds(360,250,200,300);


        //button2.setBounds(0,300);
        textArea.setFont(MyFont.F1);
        textArea.setEditable(true);
        textArea.setBackground(Color.white);
        textArea.setLineWrap(true);//自动换行
        textArea.setWrapStyleWord(true);

        this.add(label1);
        this.add(tx1);
        this.add(label2);
        this.add(tx2);
        this.add(label3);
        this.add(bt);
        this.add(label4);
        this.add(textArea);
        this.add(button1);
        this.add(button2);
        this.add(button3);

        this.setResizable(false);

        this.add(bi);
        this.setSize(700,700);
        this.setTitle("添加书籍简介");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//点击“x”关闭按钮后的反应
        this.setVisible(true);//设置窗口可见




    }

    //创建一个内部类，并继承JPanel，用于画背景图片
    static class BackImage extends JPanel
    {
        Image im;
        public BackImage(){
            try
            {
                im= ImageIO.read(new File("image/12.jpg"));//以本地图片位置创建一个Image对象
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        //要把图片画到JPanel，需要重写一个Paint方法
        public void paint(Graphics g) //此处的paint中的p是小写，
        //如果是大写，则就是重写paint方法了，而是一个新的方法了，那么后面的图片是不会输出的
        {
            g.drawImage(im,0,0,700,700,this);
        }
    }

    public static byte[] getImgStr (String path) throws IOException {
            FileInputStream fis = new FileInputStream(path);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            int len = 0;
            byte[] b = new byte[1024];
            while ((len = fis.read(b)) != -1) {
                out.write(b, 0, len);
            }

            //接收out
            byte[] array = out.toByteArray();
            fis.close();
            out.close();

            return array;
        }

        public void  add(){
            int id = Integer.parseInt(tx1.getText());
            String bookName = tx2.getText();
            String comment = textArea.getText();
         /*
        加载驱动
         */
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                //获取连接
                String url = "jdbc:mysql://localhost:3306/zhiyu_library?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
                String user = "root";
                String password = "root";
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                /*
                插入图片
                 */
                    byte[] arr = getImgStr(name);
                    Blob blob = connection.createBlob();
                    blob.setBytes(1, arr);

                    String sql = "insert into imagebook (Id,Name,pic,comment) values('"+ id +"','"+ bookName +"',?,'"+ comment +"')";

                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setBlob(1, blob);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }




    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        new AddBooks();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource()==bt) {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("image/01.png"));

            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                name = chooser.getSelectedFile().getPath();
                System.out.println(name);
                bt.setText(name);


            }
        }
        if (e.getSource()==button1) {
            //判断是否可以插入
            //sql查询语句
            int id = Integer.parseInt(tx1.getText());
            String sql1 = "SELECT * FROM imagebook WHERE Id='" + id + "'";
            conn = new SqlConnection();//建立连接
            rs = conn.sqlQuery(sql1);//返回结果集

            try {
                //利用while循环获取
                while (rs.next()) {
                    pan = rs.getInt(1);  //得到Id这一列即第一列的内容赋值给pan
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //判断是否为空
            if (tx1.getText() == null || tx2.getText() == null || textArea.getText()
                    == null || name == null) {
                JOptionPane.showMessageDialog(this, "请补全所有信息！");
            } else {
                //为0说明没找到返回0
                if (pan == 0) {
                    //pan为空说明没找到这个Id，即此编号没被使用)
                    add();
                    JOptionPane.showMessageDialog(this,"添加成功");
                    button2.doClick();
                } else {
                    JOptionPane.showMessageDialog(this, "该书籍已经添加了书籍简介");
                }
            }
        }
        if (e.getSource()==button2){
            tx1.setText(null);
            tx2.setText(null);
            textArea.setText(null);
            name=null;
            bt.setText("点击上传图片");

        }
        if(e.getSource()==button3){
            this.dispose();
        }
        }
    }


