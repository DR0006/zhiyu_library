package view.menu;

/*
    此类为学生菜单界面
 */
import tools.MyFont;
import tools.NewWindow;
import view.Books.Books;
import view.borrowing.BookRe;
import view.borrowing.ReTable;
import view.chat.MyBChat;
import view.inquiry.BookQuery;
import view.inquiry.BookTable;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class StuMenu extends JFrame{
    //相关定义
    JTable table;
    BookTable ta;
    Box vbox;
    ReTable ra;
    JSplitPane splitPane;
    public JButton bu1,bu2,bu3,bu4,bu5,bu6,bu7;
    JLabel label;
    final JScrollPane jsp;
    //构造
    public StuMenu() {
        this.menuWindow();
        this.menubar();
        this.Split();
        jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //垂直滚动条需要时显示, 水平滚动条不显示
        this.add(jsp);
    }

    public void Split(){//分隔栏
        splitPane = new JSplitPane();
        // 拖动分隔条时连续重绘组件
        splitPane.setContinuousLayout(true);

        // 设置分隔条的初始位置
        splitPane.setDividerLocation(130);
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        //按扭
        bu1=new JButton("查看我的信息");
        bu2=new JButton("查看本馆藏书");
        bu3=new JButton("查看所借的书");
        //按钮
        bu4=new JButton("修改借阅记录");
        bu5=new JButton("删除借阅记录");
        bu6=new JButton("添加新的书籍");
        bu7=new JButton("删除本馆书籍");
        bu7=new JButton("修改本馆书籍");



        //按钮字体
        bu1.setFont(MyFont.F1);
        bu2.setFont(MyFont.F1);
        bu3.setFont(MyFont.F1);
        bu4.setFont(MyFont.F1);
        bu5.setFont(MyFont.F1);
        bu6.setFont(MyFont.F1);
        bu7.setFont(MyFont.F1);
        //图片标签
        label=new JLabel(new ImageIcon("image/08.png"));

        //设置头像
        //鼠标监听事件
        label.addMouseListener(new MouseAdapter() {
            @Override
            //点击
            public void mouseClicked(MouseEvent arg0) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("image/01.png"));

                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String name = chooser.getSelectedFile().getPath();
                    ImageIcon icon = new ImageIcon(name);


                    //按默认比例缩小图片以适应JLabel
                    Image image = icon.getImage();
                    label.setSize(120, 100);
                    Image newImage = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT);
                    icon.setImage(newImage);
                    label.setIcon(icon);
                }
            }
        });
        //按钮背景颜色
        bu1.setBackground(Color.cyan);
        bu2.setBackground(Color.cyan);
        bu3.setBackground(Color.cyan);
        bu4.setBackground(Color.cyan);
        bu5.setBackground(Color.cyan);
        bu6.setBackground(Color.cyan);
        bu7.setBackground(Color.cyan);
        //按钮不可点击（显示灰色）学生无此权限
        bu4.setEnabled(false);
        bu5.setEnabled(false);
        bu6.setEnabled(false);
        bu7.setEnabled(false);

        //垂直盒子
        vbox=Box.createVerticalBox();
        vbox.add(label);
        vbox.add(bu1);
        vbox.add(bu2);
        vbox.add(bu3);
        vbox.add(bu4);
        vbox.add(bu5);
        vbox.add(bu6);
        vbox.add(bu7);
        //表格 模型
        table=new JTable();
        ra=new ReTable(null);
        table.setModel(ra);
        table.setVisible(false);

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // 鼠标按下
                //鼠标双击表格显示书籍信息
                if(e.getClickCount()==2){
                    //功能实现
                    //选择一行
                    int rowNo=table.getSelectedRow();
                    String Name= (String) table.getValueAt(rowNo, 1);
                    try {
                        new Books(Name);

                    } catch (SQLException | IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }


                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        //查看我所借的书被点击
        bu3.addActionListener(e -> {
            System.out.println("查看我所借的书被点击");
            I01();
        });
        //查看本馆图书被点击
        bu2.addActionListener(e -> {
            System.out.println("查看本馆图书被点击");
            ta=new BookTable(null);
            table.getTableHeader().setForeground(Color.RED);  // 设置表头名称字体颜色

            table.setModel(ta);
            table.setVisible(true);

        });
        //查看我的信息被点击
        bu1.addActionListener(e -> {
            System.out.println("查看我的信息被点击");
            JOptionPane.showMessageDialog(this,"您所登录的是学生界面，如需管理员账号，请联系老师或管理员");
        });


        //点击表头可以查询
        JTableHeader header=table.getTableHeader();

        header.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    Ioo1();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });


        //分隔符位置
        splitPane.setLeftComponent(vbox);
        splitPane.setRightComponent(table);
        //面板   窗口可见
        this.setContentPane(splitPane);
        this.setVisible(true);
    }
    public void menubar() {
        JMenuBar menubar=new JMenuBar();
        //创建一级菜单
        JMenu systemMenu=new JMenu("系统");
        JMenu serveMenu=new JMenu("服务");
        JMenu helpMenu=new JMenu("帮助");

        //创建（（帮助））的子菜单
        JMenuItem help1=new JMenuItem("问题");

        //添加（（帮助））的子菜单
        helpMenu.add(help1);
        help1.addActionListener(e -> {
            NewWindow help11 =new NewWindow("帮助",200,100);
            JLabel label1=new JLabel("如有相关疑问");
            label1.setFont(MyFont.F2);
            JLabel label2=new JLabel("欢迎询问QQ：2508160610");
            label2.setFont(MyFont.F2);
            JPanel panel=new JPanel();
            panel.add(label1);
            panel.add(label2);
            help11.setContentPane(panel);

        });
        //设置一级菜单字体
        systemMenu.setFont(MyFont.F1);
        serveMenu.setFont(MyFont.F1);
        helpMenu.setFont(MyFont.F1);
        //添加到面板
        menubar.add(systemMenu);
        menubar.add(serveMenu);
        menubar.add(helpMenu);
        //管理的子菜单
        JMenuItem I001=new JMenuItem("查询");
        JMenuItem I002=new JMenuItem("还书");
        JMenuItem I003=new JMenuItem("借书");
        JMenuItem I004=new JMenuItem("意见反馈");
        JMenuItem I005=new JMenuItem("联系管理员");



        //把子菜单添加到（（服务））菜单中
        serveMenu.add(I001);
        serveMenu.add(I002);
        serveMenu.add(I003);
        serveMenu.add(I004);

        serveMenu.add(I005);
        //查询被点击
        I001.addActionListener(e -> {
            System.out.println("查询被点击");
            Ioo1();

        });
    //(e -> System.out.println("查询被点击"));

        //还书被点击
        I002.addActionListener(e ->{
             System.out.println("还书被点击");
         Ioo2();
        });

        //借书被点击
        I003.addActionListener(e -> {
            System.out.println("借书被点击");
            Ioo3();
        });

        //意见反馈被点击q
        I004.addActionListener(e -> {
            System.out.println("意见反馈被点击q");
            Ioo4();
        });

        I005.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("联系管理员被点击");
                MyBChat Chat=new MyBChat();
                Chat.start();
            }
        });

        //系统的子菜单
        JMenuItem about=new JMenuItem("说明");
        JMenuItem close=new JMenuItem("退出");
        //把子菜单添加到（（系统））菜单
        systemMenu.add(about);
        systemMenu.add(close);
        //子菜单（（关于））功能实现
        about.addActionListener(e -> {
            System.out.println("子菜单关于被点击");
            NewWindow at=new NewWindow("关于本系统",400,150);
            JLabel label3=new JLabel("本系统致力于服务广大师生和图书管理员。");
            JLabel label1=new JLabel("可以借助本系统在线借书和预约还书");
            JLabel label2=new JLabel("通过本系统可以提前知道图书馆是否有您喜欢的书籍。");
            JLabel label4=new JLabel("还有其他功能期待您的发现。");
            JPanel panel=new JPanel();
            panel.add(label3);
            panel.add(label1);
            panel.add(label2);
            panel.add(label4);
            at.setContentPane(panel);
        });
        //子菜单（（退出））功能实现
        close.addActionListener(e -> this.dispose());

        this.setJMenuBar(menubar);
    }


    //菜单窗口
    public void menuWindow() {
        this.setSize(2048, 2048);
        this.setTitle("管理系统菜单");
        this.setFont(MyFont.F2);//字体
        this.setLocationRelativeTo(null);//位置
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//点击关闭按钮的反应
        this.getContentPane().setBackground(Color.white);//窗口背景颜色
        this.setVisible(true);//窗口可见
        this.setResizable(false);
    }
    //查询功能
    private void Ioo1(){

        //打开查询界面
        new BookQuery(this,"查询界面",true);
        String sql="select * from books";
        ta=new BookTable(sql);
    }
    //还书功能
    private void Ioo2() {
        JOptionPane.showMessageDialog(this,"请前往本图书馆进行还书，图书管理员将进行相关操作");
    }

    //借阅功能
    private void Ioo3() {
        new BookRe(this,"借阅窗口",true);
        I01();

    }

    //书籍意见反馈
    private void Ioo4() {
        JOptionPane.showMessageDialog(this,"如有其他想看的书籍，欢迎通过发邮件到邮箱2508160610@qq.com向我们反馈");
    }
    public void I01(){
        ra=new ReTable(null);
        table.setModel(ra);
        table.setVisible(true);
    }
    //开始main
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
        new StuMenu();

    }

}






