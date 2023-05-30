package tools;

import javax.swing.*;
import java.awt.*;

public class NewWindow extends JFrame {

    public NewWindow(String str,int a,int b){
        this.setSize(a, b);//大小
        this.setTitle(str);//标题
        this.setFont(MyFont.F1);//字体
        this.setLocationRelativeTo(null);//位置
        //this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);//点击关闭按钮的反应
        this.getContentPane().setBackground(Color.white);//窗口背景颜色
        this.setVisible(true);//窗口可见
        this.setResizable(false);//窗口不可调整大小
    }


}
