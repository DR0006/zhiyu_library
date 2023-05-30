package view.menu;

/*
    此类为管理员界面
 */


import sqlconnection.SqlConnection;
import tools.MyFont;
import tools.NewWindow;
import view.Books.Books;
import view.add.AddBooks;
import view.borrowing.BookRe;
import view.borrowing.ReTable;
import view.chat.MyAchat;
import view.game.PinBall;
import view.inquiry.BookQuery;
import view.inquiry.BookTable;
import view.modify.updateRe;
import view.add.AddBook;


import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ManMenu extends JFrame{
    //相关定义
    final JTable table;
    BookTable ta;
    Box vbox, hBox;
    final JScrollPane jsp;
    ReTable ra;
    JSplitPane splitPane;
    public JButton bu1,bu2,bu3,bu4,bu5,bu6,bu7;
    public JButton bt,bt2;
    JLabel headIcon;

    //构造方法
    public ManMenu() {
        this.menuWindow();
        this.menubar();
        //当登陆进入界面时，最初显示的界面
        table=new JTable();
        this.Split();
        jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //垂直滚动条需要时显示, 水平滚动条不显示
        this.add(jsp);
        //GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
    }

    public void Split() {
        //分隔栏
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        // 拖动分隔条时连续重绘组件
        splitPane.setContinuousLayout(true);

        // 设置分隔条的初始位置
        splitPane.setDividerLocation(150);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setOneTouchExpandable(true);
        //定义三个按钮
        bu1 = new JButton("查看我的信息");
        bu2 = new JButton("查看本馆藏书");
        bu3 = new JButton("查看所借的书");
        //按钮
        bu4 = new JButton("修改借阅记录");
        bu5 = new JButton("删除借阅记录");
        //按钮
        bu6 = new JButton("添加新的书籍");
        bu7 = new JButton("删除本馆书籍");
        //
        bt = new JButton("弹球球小游戏");
        bt2=new JButton("添加书籍简介");
        //按钮设置字体
        bu1.setFont(MyFont.F1);
        bu2.setFont(MyFont.F1);
        bu3.setFont(MyFont.F1);
        bu4.setFont(MyFont.F1);
        bu5.setFont(MyFont.F1);
        bu6.setFont(MyFont.F1);
        bu7.setFont(MyFont.F1);
        bt2.setFont(MyFont.F1);
        //
        bt.setBackground(Color.cyan);
        bt.setFont(MyFont.F1);

        //按钮背景颜色
        bu1.setBackground(Color.cyan);
        bu2.setBackground(Color.cyan);
        bu3.setBackground(Color.cyan);
        bu4.setBackground(Color.cyan);
        bu5.setBackground(Color.cyan);
        bu6.setBackground(Color.cyan);
        bu7.setBackground(Color.cyan);
        bt2.setBackground(Color.cyan);
        //定义一个存放图片的标签
        JPanel panel = new JPanel();
        //label=new JLabel(new ImageIcon("image/07.png"));
        headIcon = new JLabel(new ImageIcon("image/07.png"));
        headIcon.setSize(100, 100);
        headIcon.setToolTipText("点击此图片可以更换头像");
        JLabel label2 = new JLabel(new ImageIcon("image/11.png"));
        label2.setToolTipText("试试拉下分隔栏");
        //水平盒子
        vbox = Box.createHorizontalBox();
        hBox = Box.createVerticalBox();
        //添加部件到盒子
        vbox.add(headIcon);
        vbox.add(bu1);
        vbox.add(bu2);
        vbox.add(bu3);
        vbox.add(bu4);
        vbox.add(bu5);
        vbox.add(bu6);
        vbox.add(bu7);
        vbox.add(bt2);
        vbox.add(bt);
        JLabel label3 = new JLabel("表格数据如下:");
        panel.add(label2, BorderLayout.NORTH);
        panel.add(vbox, BorderLayout.SOUTH);
        panel.add(label3, BorderLayout.CENTER);
        //点击按钮1的反应
        bu1.addActionListener(e -> {
            System.out.println("查看我的信息被点击");
            Action1();
        });
        //点击按钮2的反应
        bu2.addActionListener(e -> {
            System.out.println("查看本馆图书被点击");
            Action2();
        });
        //点击按钮3的反应
        bu3.addActionListener(e -> {
            System.out.println("查看我所借的书被点击");
            Action3();
        });
        //点击按钮4的反应
        bu4.addActionListener(e -> {
            System.out.println("修改记录被点击");
            Action4();
            bu3.doClick();
        });
        //点击按钮5的反应
        bu5.addActionListener(e -> {
            System.out.println("删除记录被点击");
            Action5();
            bu3.doClick();
        });
        //点击按钮6的反应
        bu6.addActionListener(e -> {
            System.out.println("添加书籍被点击");
            Action6();
            bu2.doClick();
        });
        //点击按钮7的反应
        bu7.addActionListener(e -> {
            System.out.println("删除书籍被点击");
            Action7();
            bu2.doClick();
        });

        //
        bt.addActionListener(e -> {
            System.out.println("弹球小游戏被点击");
            Action8();
        });

        bt2.addActionListener(e -> {
            System.out.println("添加书籍简介被点击");
            new AddBooks();
        });
        //分隔符左边放盒子，右边放表格

        splitPane.setDividerSize(10);
        splitPane.setTopComponent(panel);
        splitPane.setBottomComponent(table);




        //表格鼠标右击事件
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 鼠标点击（按下并抬起）
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
                // 鼠标释放

                // 如果是鼠标右键，则显示弹出菜单
                if (e.isMetaDown()) {
                    showPopupMenu(e.getComponent(), e.getX(), e.getY());
                }
            }

            private void showPopupMenu(Component invoker, int x, int y) {
                // 创建 弹出菜单 对象
                JPopupMenu popupMenu = new JPopupMenu();

                // 创建 一级菜单
                JMenu deleteMenuItem = new JMenu("删除");
                JMenu editMenu = new JMenu("编辑");   // 需要 添加 二级子菜单 的 菜单，使用 JMenu
                JMenu addMenu = new JMenu("添加");

                // 创建 二级菜单
                JMenuItem addBook=new JMenuItem("书籍记录");
                JMenuItem addBorrow=new JMenuItem("借阅记录");
                JMenuItem book=new JMenuItem("书籍记录");
                JMenuItem borrow=new JMenuItem("借阅记录");

                JMenuItem findMenuItem = new JMenuItem("查找");
                JMenuItem modifyMenuItem = new JMenuItem("修改借阅记录");
                // 添加 二级菜单 到 "编辑"一级菜单
                addMenu.add(addBook);
                addMenu.add(addBorrow);

                deleteMenuItem.add(book);
                deleteMenuItem.add(borrow);
                editMenu.add(findMenuItem);
                editMenu.add(modifyMenuItem);

                // 添加 一级菜单 到 弹出菜单
                popupMenu.add(deleteMenuItem);
                popupMenu.addSeparator();       // 添加一条分隔符
                popupMenu.add(editMenu);
                popupMenu.add(addMenu);

                // 添加菜单项的点击监听器
                book.addActionListener(e -> {
                    System.out.println("删除书籍 被点击");
                    Action7();
                    bu2.doClick();
                });
                borrow.addActionListener(e -> {
                    System.out.println("删除借阅 记录被点击");
                    Action5();
                    bu3.doClick();

                });
                findMenuItem.addActionListener(e -> {
                    System.out.println("查找 被点击");
                    Ioo1();
                });

                modifyMenuItem.addActionListener(e -> {
                    System.out.println("修改借阅记录 被点击");
                    Action4();
                });
                addBook.addActionListener(e -> {
                    System.out.println("添加书籍记录被点击");
                    Action6();
                });
                addBorrow.addActionListener(e -> {
                    System.out.println("添加借阅记录被点击");
                    Ioo3();
                });
                // 在指定位置显示弹出菜单
                popupMenu.show(invoker, x, y);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // 鼠标进入组件区域
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 鼠标离开组件区域
            }
        });






        //设置背景颜色为粉色
        splitPane.setBackground(Color.PINK);
        splitPane.setDividerLocation(100);
        this.setContentPane(splitPane);//设置面板

        this.setVisible(true);//窗口可见


        //设置头像
        //鼠标监听事件
        headIcon.addMouseListener(new MouseAdapter() {
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
                    headIcon.setSize(100, 100);
                    Image newImage = image.getScaledInstance(headIcon.getWidth(), headIcon.getHeight(), Image.SCALE_DEFAULT);
                    icon.setImage(newImage);
                    headIcon.setIcon(icon);
                }
            }
        });
    }



    //菜单界面
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
        JMenuItem I005=new JMenuItem("学生消息");


        //把子菜单添加到（（服务））菜单中
        serveMenu.add(I001);
        serveMenu.add(I002);
        serveMenu.add(I003);
        serveMenu.add(I004);
        serveMenu.add(I005);

        //点击了查询按钮
        I001.addActionListener(e -> {
            System.out.println("查询被点击");
            Ioo1();//调用Ioo1方法，下同理

        });
        //(e -> System.out.println("查询被点击"));

        //点击了还书按钮
        I002.addActionListener(e ->{
            System.out.println("还书被点击");
            Ioo2();
        });
        //点击了借书按钮
        I003.addActionListener(e -> {
            System.out.println("借书被点击");
            Ioo3();
        });
        //点击了意见反馈按钮
        I004.addActionListener(e -> {
            System.out.println("意见反馈被点击q");
            Ioo4();
        });


        I005.addActionListener(e -> {
            System.out.println("学生消息");

            MyAchat Chat=new MyAchat();
            Chat.start();
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
            JLabel label5=new JLabel("试试点击图片和双击表格数据吧");
            JPanel panel=new JPanel();
            panel.add(label3);
            panel.add(label1);
            panel.add(label2);
            panel.add(label4);
            panel.add(label5);
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
        //sql语句
        String sql="select * from books";
        //表格
        ta=new BookTable(sql);
        table.setModel(ta);//设置表格模型
    }
    //还书功能
    private void Ioo2() {
        //提示消息
        JOptionPane.showMessageDialog(this,"请前往本图书馆进行还书，图书管理员将进行相关操作");
    }

    //借阅功能
    private void Ioo3() {
        //打开借阅界面
        new BookRe(this,"借阅窗口",true);
        Action3();
    }

    //书籍意见反馈
    private void Ioo4() {
        //提升消息
        JOptionPane.showMessageDialog(this,"如有其他想看的书籍，欢迎联系管理员或者通过发邮件到邮箱2508160610@qq.com向我们反馈");
    }

    //main方法
    public static void main(String[] args)  {

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

        new ManMenu();

    }
    //查看我的信息按钮被点击
    //提示消息
       private void Action1 () {
           System.out.println("查看我的信息按钮被点击");
        JOptionPane.showMessageDialog(this,"您所登录的是管理员界面，可以进行修改或删除等操作");
        }

        //查看本馆图书被点击
       private void Action2() {
           System.out.println("查看本馆图书被点击");
        ta=new BookTable(null);
        table.getTableHeader().setForeground(Color.RED);  // 设置表头名称字体颜色
           // 使用 表格模型 创建 行排序器
           RowSorter<BookTable> rowSorter = new TableRowSorter<>(ta);
           // 给 表格 设置 行排序器
           table.setRowSorter(rowSorter);
           table.setVisible(true);
           table.setModel(ta);
         }
         //查看我借的书被点击
        private void Action3(){
            System.out.println("查看我借的书被点击");
            ra=new ReTable(null);
            table.getTableHeader().setForeground(Color.RED);  // 设置表头名称字体颜色
            // 使用 表格模型 创建 行排序器
            RowSorter<ReTable> rowSorter = new TableRowSorter<>(ra);
            // 给 表格 设置 行排序器
            table.setRowSorter(rowSorter);
            table.setModel(ra);
            table.setVisible(true);
        }

        //修改记录被点击
        private void Action4(){
            System.out.println("修改记录被点击");

            int rowNo=table.getSelectedRow();
            //判断是否已经选择一行，如果没有选择，则弹出一个对话框，当等于-1时，表示没有选择
            if(rowNo==-1)
            {
                JOptionPane.showMessageDialog(this,"你没有选择一行！");
            }
            else
            {
                //打开修改窗口
                new updateRe(this,"修改窗口",true,ra,rowNo);
                ra=new ReTable(null);
                table.setModel(ra);
            }

        }

       //删除借阅记录
        private void Action5(){
            //选择一行
            int rowNo=table.getSelectedRow();
            //判断是否已经选择一行，如果没有选择，则弹出一个对话框，当等于-1时，表示没有选择
            if(rowNo==-1)
            {
                JOptionPane.showMessageDialog(this,"你没有选择一行！");
            }
            else
            {
                //得到选择的那一行的Id
                String reid=(String)ra.getValueAt(rowNo, 0);
                System.out.println("Id="+reid);
                String sql="DELETE FROM bookreturn WHERE Id='"+reid+"'";
                Object[] options = {"确定","取消"};
                int response=JOptionPane.showOptionDialog(this, "你确认要删除该记录吗？","删除信息确认对话框",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null, options, options[0]);
                if(response==0)
                {
                    System.out.println("你按下了确认按钮！");
                    //连接数据库
                    SqlConnection connect=new SqlConnection();
                    connect.deleteStu(sql);
                    ra=new ReTable(null);
                    table.setModel(ra);
                }
                else if(response==1)
                {
                    System.out.println("你按下了取消按钮！");
                }

            }
        }
        //添加书籍的功能
        private void Action6(){
            new AddBook(this,"添加书籍",true);
            ta=new BookTable(null);
            table.setModel(ta);
        }
        //删除书籍被点击
        private void Action7(){
            //选择一行
            int rowNo=table.getSelectedRow();
            //判断是否已经选择一行，如果没有选择，则弹出一个对话框，当等于-1时，表示没有选择
            if(rowNo==-1)
            {
                JOptionPane.showMessageDialog(this,"你没有选择一行！");
            }
            else
            {
                //得到选择的那一行的Id号
                String bookId=(String)ta.getValueAt(rowNo, 0);
                System.out.println("Id="+bookId);
                String sql="DELETE FROM books WHERE Id='"+bookId+"'";
                Object[] options = {"确定","取消"};
                int response=JOptionPane.showOptionDialog(this, "您确认要删除该书籍吗？","删除信息确认对话框",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null, options, options[0]);
                if(response==0)
                {
                    System.out.println("你按下了确认按钮！");
                    //连接数据库
                    SqlConnection connect=new SqlConnection();
                    connect.deleteStu(sql);
                    ta=new BookTable(null);
                    table.setModel(ta);
                }
                else if(response==1)
                {
                    System.out.println("你按下了取消按钮！");
                }

            }
        }
    private void Action8() {
        new PinBall().init();
    }

}