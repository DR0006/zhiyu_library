package view.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyAchat extends Thread{

    private BufferedWriter bw;
    private JPanel panel;
    private JTextArea textArea;
    private JButton button;
    private JScrollPane scrollPane;
    private JTextField textField;
    @Override
    public void run() {
        JFrame frame=new JFrame("管理员聊天界面");
        panel=new JPanel();
        textArea=new JTextArea();

        button=new JButton("发送");

        scrollPane=new JScrollPane(textArea);
        textField=new JTextField(15);

        //文本框不可编辑
        textArea.setEditable(false);
        panel.add(textField);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("发送按钮被点击");

                String text = textField.getText();

                text = "管理员:" + text;

                textArea.append(text+System.lineSeparator());

                try {
                    bw.write(text);
                    bw.newLine();
                    bw.flush();
                    textField.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame,"暂无学生上线");
                }
            }
        });

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel,BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        try {
            ServerSocket serverSocket=new ServerSocket(8888);

            Socket socket=serverSocket.accept();

            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line;
            while((line=br.readLine())!=null){
                textArea.append(line+ System.lineSeparator());

            }



            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
