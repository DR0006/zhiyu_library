package view.inquiry;

/*
    此类为查询界面
 */
import tools.MyFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookQuery extends JDialog implements ActionListener {
    //相关定义
    final JPanel jp;
    final JLabel jp1_jl;
    final JButton bu1;
    final JButton bu2;
    final JButton bu3;
    final JTextField jt;
    final JScrollPane jsp;
    BookTable tot;
    final JTable table;

    //构造方法
    public  BookQuery(Frame Main,String title,boolean model){
        //调用父类构造方法
        super(Main,title,model);
        //创建一个书籍表格BookTable对象
        tot=new BookTable(null);
        jp=new JPanel();//面板
        jp1_jl=new JLabel("请输入书名");//标签
        jt=new JTextField(15);//文本框
        //三个按钮
        bu1=new JButton("查询");
        bu2=new JButton("清除记录");
        bu3=new JButton("查看热门书籍");
        //设置字体
        jt.setFont(MyFont.F1);
        jp1_jl.setFont(MyFont.F2);
        bu1.setFont(MyFont.F2);
        bu2.setFont(MyFont.F2);
        bu3.setFont(MyFont.F2);
        //按钮反应
        bu1.addActionListener(this);
        bu2.addActionListener(this);
        bu3.addActionListener(this);
        //添加组件到面板jp中
        jp.add(jp1_jl);
        jp.add(jt);
        jp.add(bu1);
        jp.add(bu2);
        jp.add(bu3);
        //创建一个  JTable表格对象
        table=new JTable();
        table.setFont(MyFont.F1);//字体
        table.setSelectionBackground(Color.LIGHT_GRAY);// 选中后字体背景
        table.setSelectionForeground(Color.DARK_GRAY);// 选中后字体颜色
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
        //
        jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //垂直滚动条需要时显示, 水平滚动条不显示

        this.add(jp,"North");//把面板jp设置在窗口南边
        this.add(jsp,"Center");//把滚动条设置在中间
        //大小
        this.setSize(500,300);
        //默认位置为中央
        this.setLocationRelativeTo(null);
        //窗口可见
        this.setVisible(true);

    }
    //反应具体实现
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bu1){
            //点击按钮1（查询）
            System.out.println("查询被点击");
            String bookName=jt.getText().trim();
            //sql语句
            String sql="SELECT *FROM books WHERE BookName='"+bookName+"' ";
            //
            tot=new BookTable(sql);
            table.setModel(tot);//设置表格模型
            jt.setText(null);//查询完成后把文本框设置为空，便于下次查询直接输入而不用手动删除
        }
        //点击按钮2（清除）
        else if(e.getSource()==bu2){
            System.out.println("清除记录被点击");
            table.setVisible(false);//把表格设置为不可见 ，起到将表格“清除”的作用
        }
        else{
            //点击按钮3（查看热门书籍）
            System.out.println("查看热门书籍被点击");
            tot=new BookTable(null);
            table.setVisible(true);//设置可见
            table.setModel(tot);//设置模型
        }

    }
}
