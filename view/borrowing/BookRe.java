package view.borrowing;

/*
    此类为借阅书籍界面
    相关功能实现
 */

import sqlconnection.SqlConnection;
import tools.MyFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRe extends JDialog implements ActionListener {
    //相关定义
    final JLabel label1;
    final JLabel label2;
    final JLabel label3;
    final JLabel label4;
    final JLabel label5;
    final JLabel label6;
    final JButton bu1;
    final JButton bu2;
    final JButton bu3;
    final JTextField jt1;
    final JTextField jt2;
    final JTextField jt3;
    final JTextField jt4;
    final JTextField jt5;
    final JTextField jt6;
    String Id,BookName,student,stuclass,stuid,bookreturn;
    String pan;
    String sql2;
    SqlConnection conn;
    ResultSet rs;
    //构造方法
    public BookRe(Frame main,String title,boolean model){
        super(main ,title,model);//调用父类构造方法
        //创建标签
        label1=new JLabel("书   号:");
        label2=new JLabel("书   名:");
        label3=new JLabel("姓   名:");
        label4=new JLabel("班   级:");
        label5=new JLabel("学   号:");
        label6=new JLabel("天   数:");
        //创建文本框
        jt1=new JTextField(15);
        jt2=new JTextField(15);
        jt3=new JTextField(15);
        jt4=new JTextField(15);
        jt5=new JTextField(15);
        jt6=new JTextField(15);
        //设置文本框边界效果起到美化
        jt1.setBorder(BorderFactory.createLoweredBevelBorder());
        jt2.setBorder(BorderFactory.createLoweredBevelBorder());
        jt3.setBorder(BorderFactory.createLoweredBevelBorder());
        jt4.setBorder(BorderFactory.createLoweredBevelBorder());
        jt5.setBorder(BorderFactory.createLoweredBevelBorder());
        jt6.setBorder(BorderFactory.createLoweredBevelBorder());

        //创建三个按钮
        bu1=new JButton("借阅");
        bu2=new JButton("清除");
        bu3=new JButton("取消");
        //标签设置字体
        label1.setFont(MyFont.F1);
        label2.setFont(MyFont.F1);
        label3.setFont(MyFont.F1);
        label4.setFont(MyFont.F1);
        label5.setFont(MyFont.F1);
        label6.setFont(MyFont.F1);
        //按钮设置字体
        bu1.setFont(MyFont.F2);
        bu2.setFont(MyFont.F2);
        bu3.setFont(MyFont.F2);
        //点击按钮反应
        bu1.addActionListener(this);
        bu2.addActionListener(this);
        bu3.addActionListener(this);
        //创建水平盒子hBox
        Box hBox1=Box.createHorizontalBox();//水平
        Box hBox2=Box.createHorizontalBox();
        Box hBox3=Box.createHorizontalBox();//水平
        Box hBox4=Box.createHorizontalBox();
        Box hBox5=Box.createHorizontalBox();//水平
        Box hBox6=Box.createHorizontalBox();
        Box hBox7=Box.createHorizontalBox();
        //相关组件的添加
        hBox1.add(label1);
        hBox1.add(jt1);
        hBox2.add(label2);
        hBox2.add(jt2);
        hBox3.add(label3);
        hBox3.add(jt3);
        hBox4.add(label4);
        hBox4.add(jt4);
        hBox5.add(label5);
        hBox5.add(jt5);
        hBox6.add(label6);
        hBox6.add(jt6);
        hBox7.add(bu1);
        hBox7.add(bu2);
        hBox7.add(bu3);

        //创建垂直盒子
        //把7个水平盒子串起来
        Box vBox=Box.createVerticalBox();
        vBox.add(hBox1);
        vBox.add(hBox2);
        vBox.add(hBox3);
        vBox.add(hBox4);
        vBox.add(hBox5);
        vBox.add(hBox6);
        vBox.add(hBox7);

        //窗口设置面板
        this.setContentPane(vBox);
        /*
        table=new JTable();
        table.setFont(MyFont.F1);//
        table.setSelectionBackground(Color.LIGHT_GRAY);// 选中后字体背景
        table.setSelectionForeground(Color.DARK_GRAY);// 选中后字体颜色
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        //
        jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //垂直滚动条需要时显示, 水平滚动条不显示
                */
        //窗口大小
        this.setSize(400,250);
        //窗口背景颜色
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        //窗口默认居于中央
        this.setLocationRelativeTo(null);
        //窗口可见
        this.setVisible(true);
    }
    //按钮反应具体实现
    @Override
    public void actionPerformed(ActionEvent e) {
        //点击按钮1（借阅）
        if (e.getSource() == bu1) {
            //
            System.out.println("借阅按钮被点击");
            Id = jt1.getText().trim();
            BookName = jt2.getText().trim();
            student = jt3.getText().trim();
            stuclass = jt4.getText().trim();
            stuid = jt5.getText().trim();
            bookreturn = jt6.getText().trim();

            //判断文本是否为空
            //为空则提示相关消息
            if (Id.equals("") || BookName.equals("") || stuclass.equals("") || stuid.equals("") || student.equals("") || bookreturn.equals("")) {
                JOptionPane.showMessageDialog(this, "请补全所有信息");
            }
            //sql语句
            String sql1="SELECT * FROM books WHERE Id='"+Id+"'";
            //建立连接
            conn=new SqlConnection();
            rs=conn.sqlQuery(sql1);
            //获取结果集内容
            try{
                while(rs.next()){
                    pan=rs.getString(1);//pan用以判断所借的书表内是否存在
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //判断所借的书是否在表里存在
            if(pan==null){  //为空则表示不存在这本书
                JOptionPane.showMessageDialog(this,"很抱歉，您所选择的书本图书馆不存在");
            }
            else{
                //否则 进行借阅  ，即在借阅表格内添加数据
                //sql语句
                sql2="insert into bookreturn values('"+Id+"','"+BookName+"','"+student+"','"+stuclass+"','"+stuid+"','"+bookreturn+"')";
                //建立连接
                SqlConnection conn=new SqlConnection();
                conn.sqlUpdate(sql2);
                //释放资源
                conn.closeSql();
                //关闭此界面
                this.dispose();
            }
        }
        //点击按钮2（清除）
        //将文本框设置为空起到清除作用
        else if(e.getSource()==bu2){
            System.out.println("清除按钮被点击");
            jt1.setText(null);
            jt2.setText(null);
            jt3.setText(null);
            jt4.setText(null);
            jt5.setText(null);
        }
        //点击按钮3（取消）  则关闭此界面
        else{
            System.out.println("取消按钮被点击");
            this.dispose();
        }
    }
}
