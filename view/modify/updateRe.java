package view.modify;

/*
    此为修改功能实现
 */
import sqlconnection.SqlConnection;
import tools.MyFont;
import view.borrowing.ReTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class updateRe extends JDialog implements ActionListener {
    //相关定义
     JButton bu1;
     JButton bu2;
     JButton bu3;
     JLabel label1,label2,label3,label4,label5,label6;
     JTextField jt1,jt2,jt3,jt4,jt5,jt6;
     Box box,vbox;
     Box box1,box2,box3,box4,box5,box6;
    String BookName,student,stuclass,day,stuId,Id;
    //构造方法
    public  updateRe(Frame Main, String title, boolean model, ReTable ra, int rowNo){
        super(Main,title,model);
        updateWindow();

        Id=(String)ra.getValueAt(rowNo, 0);
        System.out.println("Id="+Id);
        //获得选择表格的内容
        BookName=(String)ra.getValueAt(rowNo, 1);
        student=(String)ra.getValueAt(rowNo, 2);
        stuclass=(String)ra.getValueAt(rowNo, 3);
        stuId=(String)ra.getValueAt(rowNo, 4);
        day=(String)ra.getValueAt(rowNo, 5);
        //设置文本框内容
        jt1.setText(Id);
        jt2.setText(BookName);
        jt3.setText(student);
        jt4.setText(stuclass);
        jt5.setText(stuId);
        jt6.setText(day);
        //内容面板
        this.setContentPane(vbox);
        this.setSize(500,300);//窗口大小
        this.setLocationRelativeTo(null);//位置
        this.getContentPane().setBackground(Color.LIGHT_GRAY);//背景颜色
        this.setVisible(true);//窗口可见


    }
    private  void updateWindow(){
        //定义标签
        label1=new JLabel("书   号:");
        label2=new JLabel("书   名:");
        label3=new JLabel("姓   名:");
        label4=new JLabel("班   级:");
        label5=new JLabel("学   号:");
        label6=new JLabel("天   数:");
        //文本框
        jt1=new JTextField(15);
        jt2=new JTextField(15);
        jt3=new JTextField(15);
        jt4=new JTextField(15);
        jt5=new JTextField(15);
        jt6=new JTextField(15);
        //文本框边界效果
        jt1.setBorder(BorderFactory.createLoweredBevelBorder());
        jt2.setBorder(BorderFactory.createLoweredBevelBorder());
        jt3.setBorder(BorderFactory.createLoweredBevelBorder());
        jt4.setBorder(BorderFactory.createLoweredBevelBorder());
        jt5.setBorder(BorderFactory.createLoweredBevelBorder());
        jt6.setBorder(BorderFactory.createLoweredBevelBorder());

        //按钮
        bu1=new JButton("修改");
        bu2=new JButton("清除记录");
        bu3=new JButton("取消");
        //按钮字体
        bu1.setFont(MyFont.F2);
        bu2.setFont(MyFont.F2);
        bu3.setFont(MyFont.F2);
        //按钮被点击的反应
        bu1.addActionListener(this);
        bu2.addActionListener(this);
        bu3.addActionListener(this);
        //水平盒子
        box1=Box.createHorizontalBox();
        box2=Box.createHorizontalBox();
        box3=Box.createHorizontalBox();
        box4=Box.createHorizontalBox();
        box5=Box.createHorizontalBox();
        box6=Box.createHorizontalBox();
        //添加组件
        box1.add(label1);
        box1.add(jt1);
        //
        box2.add(label2);
        box2.add(jt2);
        //
        box3.add(label3);
        box3.add(jt3);
        //
        box4.add(label4);
        box4.add(jt4);
        //
        box5.add(label5);
        box5.add(jt5);
        //
        box6.add(label6);
        box6.add(jt6);
        //垂直盒子
        box=Box.createHorizontalBox();
        box.add(bu1);
        box.add(bu2);
        box.add(bu3);
        //添加组件
        vbox=Box.createVerticalBox();
        vbox.add(box1);
        vbox.add(box2);
        vbox.add(box3);
        vbox.add(box4);
        vbox.add(box5);
        vbox.add(box6);
        vbox.add(box);
    }

    //点击按钮的反应
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bu1){
            //修改按钮
            System.out.println("修改按钮被点击");
            Id=jt1.getText().trim();
            BookName=jt2.getText().trim();
            student=jt3.getText().trim();
            stuclass=jt4.getText().trim();
            stuId=jt5.getText().trim();
            day=jt6.getText().trim();
            //判断是否为空，为空则提示消息
            if (Id.equals("") || BookName.equals("") || stuclass.equals("") || stuId.equals("") || student.equals("") || day.equals("")) {
                    JOptionPane.showMessageDialog(this, "请补全所有信息");
                }
            //sql语句
            String sql="update bookreturn set Id='"+Id+"',BookName='"+BookName+"',Restudent='"+student+"',Reclass='"+stuclass+"',Restuid='"+stuId+"',day='"+day+"'where Id='"+Id+"'";
           //建立连接
            SqlConnection connect=new SqlConnection();
            connect.sqlUpdate(sql);
            //关闭数据库连接
            connect.closeSql();
            //关闭对话框
            this.dispose();
        }
        //清除按钮被点击
        else if(e.getSource()==bu2){
            System.out.println("清除按钮被点击");
            jt1.setText(null);
            jt2.setText(null);
            jt3.setText(null);
            jt4.setText(null);
            jt5.setText(null);
            jt6.setText(null);
        }
        //取消按钮被点击
        else{
            System.out.println("取消按钮被点击");
            //关闭当前窗口
            this.dispose();
        }
    }
}
