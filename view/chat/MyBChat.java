package view.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class MyBChat extends Thread {
    private JPanel panel;
    private JTextArea textArea;
    private JButton button;
    private JScrollPane scrollPane;
    private JTextField textField;
    private BufferedWriter bw;
    @Override
    public void run() {

        JFrame frame = new JFrame("学生聊天界面");
        panel = new JPanel();
        textArea = new JTextArea();

        button = new JButton("发送");

        scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        textField = new JTextField(15);

        panel.add(textField);
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("44");
                String text = textField.getText();

                text = "学生:" + text;

                textArea.append(text+ System.lineSeparator());

                try {
                    bw.write(text);
                    bw.newLine();
                    bw.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


                textField.setText("");
            }
        });
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setTitle("学生聊天界面");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        try {
            Socket socket = new Socket("127.0.0.1", 8888);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String line;
            while ((line = br.readLine()) != null) {
                textArea.append(line + System.lineSeparator());
            }

            socket.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame,"管理员不在线，请在工作时间联系");

        }


    }
}
