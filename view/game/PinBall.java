package view.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PinBall {
    private final JFrame frame = new JFrame("弹球小游戏");
    //桌面宽度
    private final int TABLE_WIDTH = 300;

    //球拍的高度和宽度
    private final int RACKET_WIDTH = 60;
    //球的大小
    private final int BALL_SIZE = 16;
    //小球坐标
    private int ballX = 120;
    private int ballY = 20;

    //小球在xy方向上分别移动端速度
    private int speedY = 10;
    private int speedX = 5;

    //定义球拍的坐标
    private int racketX = 120;
    private final int racketY = 340;

    //标识游戏是否结束
    private boolean isOver = false;
    //声明一个定时器
    private Timer timer;

    //定义一个类，继承canvas，充当画布
    private class MyCanvas extends Canvas {
        //重写paint方法
        @Override
        public void paint(Graphics g) {
            //结束
            if (isOver) {
                g.setColor(Color.PINK);
                g.setFont(new Font("Times", Font.BOLD, 30));
                g.drawString("Game Over！", 50, 200);
            } else {//中
                //绘制小球
                g.setColor(Color.red);
                g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

                //绘制球拍
                g.setColor(Color.CYAN);
                int RACKET_HEIGHT = 20;
                g.fillRect(racketX, racketY, RACKET_WIDTH, RACKET_HEIGHT);

            }


        }
    }

    //创建绘画区域
    MyCanvas drawArea = new MyCanvas();

    public void init() {

        //完成球拍坐标的变化
        KeyListener listener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //获取所按下的键
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_LEFT) {
                    //向左移动
                    if (racketX > 0) {
                        racketX -= 10;
                    }

                }
                if (keyCode == KeyEvent.VK_RIGHT) {
                    //向右移动
                    if (racketX < (TABLE_WIDTH - RACKET_WIDTH)) {
                        racketX += 10;
                    }
                }

            }
        };
        //给Frame和drawArea注册监听器
        frame.addKeyListener(listener);
        drawArea.addKeyListener(listener);

        //小球坐标控制
        ActionListener task= e -> {
            //更新小球坐标，重绘界面

            //根据边界范围修改速度
            if(ballX<=0||ballX>=TABLE_WIDTH-BALL_SIZE){
                speedX=-speedX;
            }
            if(ballY<=0||ballY>racketY-BALL_SIZE&&ballX>racketX&&ballX<racketX+RACKET_WIDTH){
                speedY=-speedY;
            }
            if(ballY>racketY-BALL_SIZE&&(ballX<racketX||ballX>racketX+RACKET_WIDTH)){
                //当前小球超出球拍范围，结束

                //停止计时器
                timer.stop();
                //
                isOver=true;
                drawArea.repaint();
            }


            ballX+=speedX;
            ballY+=speedY;

            //
            drawArea.repaint();
        };
        timer=new Timer(100,task);
        timer.start();

        int TABLE_HEIGHT = 400;
        drawArea.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        frame.add(drawArea);
            //设置frame最佳大小,并可视
        frame.pack();
        frame.setVisible(true);
        }
    }

