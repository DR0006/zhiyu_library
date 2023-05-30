package view.Books;

import tools.MyFont;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.*;

public class Books extends JFrame {

    private String name;
    ImageIcon image;
    JLabel label, label2;
    Box hBox, vBox;

    public Books(String Name) throws SQLException, IOException, ClassNotFoundException {
        JPanel panel = new JPanel();
        label = new JLabel();
        if (Name == null) {
            Name = "计算机网络";
        }
        Connection conn = null;
        final String user = "root";
        final String password = "root";
        final String url = "jdbc:mysql://localhost:3306/zhiyu_library";


        //
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("驱动加载成功！");
            //建立连接
            conn = DriverManager.getConnection(url, user, password);
            //System.out.println("数据库连接成功");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
        String sql = "SELECT * FROM imagebook where Name='" + Name + "' ";
        assert conn != null;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Blob img = null;
        String comment = null;
        while (rs.next()) {
            name = '《' + rs.getString(2) + '》' + ':';
            comment = rs.getString(4);
            img = rs.getBlob(3);

        }
        assert img != null;
        byte[] imageData = img.getBytes(1, img.getBinaryStream().available());
        image = new ImageIcon(imageData);
        label.setIcon(image);

        //
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setFont(MyFont.F1);
        textArea.setEditable(false);
        textArea.setBackground(Color.CYAN);
        textArea.setText(comment);
        textArea.setLineWrap(true);//自动换行
        textArea.setWrapStyleWord(true);            // 激活断行不断字功能
        label2 = new JLabel(name, SwingConstants.LEFT);
        label2.setFont(new Font("", Font.BOLD, 30)); // 设置文字的字体及大小
        label2.setHorizontalAlignment(JLabel.CENTER);
        hBox = Box.createVerticalBox();
        hBox.add(label2);
        hBox.add(label);
        vBox = Box.createHorizontalBox();
        vBox.add(hBox);
        vBox.add(textArea);
        //panel.add(label2,BorderLayout.NORTH);
        panel.add(vBox);
        //panel.add(label, BorderLayout.CENTER);

        panel.add(textArea, BorderLayout.SOUTH);
        this.setResizable(false);
        this.setVisible(true);
        //this.setLocationRelativeTo(null);
        this.setSize(800, 700);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setContentPane(panel);
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Books("恶意");
    }
}
