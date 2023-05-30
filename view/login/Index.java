package view.login;

import javax.swing.*;
import java.awt.*;
public class Index extends JWindow implements Runnable{
    //定义与进度条相关的组件
    final JProgressBar jpb;//定义进度条
    final JLabel jl1;
    final int width;
    final int height;
    final JPanel panel;
    public static void main(String []args){

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
        Index index=new Index();
        //创建index线程
        Thread t=new Thread(index);
        //启动线程
        t.start();
    }

    //构造函数
    public Index()
    {
        panel=new JPanel();
        panel.setBackground(Color.pink);
        //创建标签,并在标签上放置一张图片
        jl1=new JLabel(new ImageIcon("image/login.png"));
        //创建进度条
        jpb=new JProgressBar();
        //设置进度条属性
        jpb.setStringPainted(true);//显示当前进度值信息
        jpb.setIndeterminate(false);//确定进度条执行完成后不来回滚动
        jpb.setBorderPainted(false);//设置进度条边框不显示
        jpb.setBackground(Color.darkGray);//设置进度条的背景色

        //添加组件
        panel.add(jl1,BorderLayout.NORTH);
        panel.add(jpb,BorderLayout.SOUTH);

        //设置窗体属性
        this.setSize(500,330);
        //设置窗体显示的位置
        width=Toolkit.getDefaultToolkit().getScreenSize().width;
        height=Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setContentPane(panel);
        this.setLocation(width/2-200,height/2-150);
        //设置窗口显示
        this.setVisible(true);
    }
    //进度条线程的run方法，用于设置线程的属性
    public void run() {

        //定义一个数组，存放进度条显示时需要的数据
        int []progressValue={0,1,8,14,20,38,43,56,65,75,78,86,94,100};
        for (int j : progressValue) {
            try {
                //休眠0.5秒，再执行
                Thread.sleep(500);//sleep 毫秒数
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jpb.setValue(j);//取得数组中的进度值
        }
        //当进度完成后，执行相应的操作，如切换到其他的窗口，同时关闭进度条窗口等
        new LoginWindow();
        //关闭进度条窗口
        this.dispose();
    }
}
