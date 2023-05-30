package view.login;

/*
    此类为登录界面
 */
import tools.MyFont;
import view.menu.ManMenu;
import view.menu.StuMenu;
import view.register.Register;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginWindow extends JFrame {
    //定义密码和用户名
    private String username;
    private String password;

    //面板
    private final JPanel panel1 = new JPanel();
    private final JPanel panel2 = new JPanel();
    //登录模型
    LoginModel loginModel;
    int user;

    //密码框
    //private  JPasswordField pass;
    //-----------
    //按钮
    private JButton bt1;
    private JButton bt2;
    private JButton bt3;
    //文本框
    static JTextField text1;
    //private JTextField text2;
    //滚动条
    static JPasswordField pass;

    //构造方法
    public LoginWindow() {
        this.addComponent();
        this.init();
        this.addListener();
    }

    //登录界面窗口
    public void init() {
        this.setSize(600, 500);//大小
        this.setTitle("系统登录界面");//标题
        this.setFont(MyFont.F1);//字体
        this.setLocationRelativeTo(null);//位置
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//点击关闭按钮的反应
        this.getContentPane().setBackground(Color.white);//窗口背景颜色
        this.setVisible(true);//窗口可见
        this.setResizable(false);//窗口不可调整大小
    }


    //增加零部件
    private void  addComponent () {
        //用户名  使用默认的浮动布局
        //标签
        //定义两个Icon对象
        Icon im1=new ImageIcon("image/01.png");
        Icon im2=new ImageIcon("image/02.png");
        JLabel label1 = new JLabel("用 户:",im1,SwingConstants.CENTER);
        label1.setVisible(true);//标签可见
        JPanel panel4 = new JPanel();//定义一个面板
        label1.setFont(MyFont.F2);//字体美化

        ImageIcon imageIcon=new ImageIcon("image/img.png");
        JLabel label=new JLabel(imageIcon);
        //添加部件
        panel4.add(label);
        panel1.add(label1);
        text1=new JTextField(10);
        panel1.add(text1);

        //密码   使用默认的浮动布局
        JLabel label2 = new JLabel("密 码:",im2, SwingConstants.CENTER);
        label2.setFont(MyFont.F2);
        panel2.add(label2);
        pass=new JPasswordField(10);
        panel2.add(pass);
        //登录和注册  浮动布局并在容器内居中显示
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //文本框
        //private JTextField text1;
        //private JTextField text2;
        //按钮
        bt1 = new JButton("登录");
        bt2 = new JButton("退出");
        bt3=new JButton("注册");

        bt1.setBackground(Color.cyan);
        bt2.setBackground(Color.cyan);
        bt2.setBackground(Color.WHITE);
        panel3.add(bt1);
        panel3.add(bt3);
        panel3.add(bt2);


        panel4.add(label);
        //创建一个垂直盒子容器  把三个panel串起来
        Box vbox = Box.createVerticalBox();
        panel1.setBackground(Color.PINK);
        panel2.setBackground(Color.pink);
        panel3.setBackground(Color.pink);
        panel4.setBackground(Color.white);
        vbox.add(panel4);
        vbox.add(panel1);
        vbox.add(panel2);
        vbox.add(panel3);
        this.setBackground(Color.white);


        this.setContentPane(vbox);
        this.pack();


    }

    //功能方法
    //登录按钮功能
    private void addListener() {
        bt1.addActionListener(e -> {
            password=new String(pass.getPassword());
            username=text1.getText();
            //LoginModel 用登录模型判断登录者身份
            loginModel=new LoginModel();
            user=loginModel.checkUser(username,password);
            if(user==1){//管理员
                //打开管理员界面
                new ManMenu();
                this.dispose();//dispose方法是关闭窗体，并释放一部分资源。
                System.out.println("管理员菜单窗口启动");
            }
            else if(user==2){//学生
                //打开学生界面
                new StuMenu();
                this.dispose();//关闭当前界面
                System.out.println("学生菜单窗口启动");
            }
            //user为其他值
            else {
                //提示消息“密码或用户名错误”
                JOptionPane.showMessageDialog(null,"登陆密码或用户名错误");
            }
        });

        //退出按钮
        bt2.addActionListener(e -> dispose());

        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register();
            }
        });
    }

    //main方法
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
        new LoginWindow();
    }
}
