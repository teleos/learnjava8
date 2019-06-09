package cn.liang.five_chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GobangPanel extends JPanel {


    //保存棋子坐标
    int x = 0,y = 0;
    //保存之前下过的全部棋子的坐标
    //0:这个点没有棋子，1：这个点是黑子,2:这个点是白子
    int [][] allChess = new int[19][19];
    boolean isBlack ;//自己是白棋
    boolean canPlay = false;//标识当前游戏是否可以继续
    String[] ss = new String [10]; //消息数组

    Transmit transmit;
    private String msg;




    public GobangPanel( Transmit ts) {

        transmit = ts;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //判断服务器能否下棋
                if (canPlay == true) {
                    //获取x,y轴坐标
                    x = e.getX();
                    y = e.getY();
                    //设置下棋范围
                    if (x > 10 && x <= 370 && y >= 50 && y <= 410) {
                        x = x / 20;
                        y = (y - 60) / 20;
                        //没有下棋的位置才能放入棋子
                        if (allChess[x][y] == 0) {
                            //判断当前要下什么颜色的棋子
                            if (isBlack == true) {
                                allChess[x][y] = 1;
                                msg = "轮到白方";
                                transmit.sendData("move|" + String.valueOf(x) + "|" + String.valueOf(y));
                                canPlay = false;
                            } else {
                                //1>
                                allChess[x][y] = 2;
                                msg = "轮到黑方";
                                transmit.sendData("move|" + String.valueOf(x) + "|" + String.valueOf(y));
                                canPlay = false;
                            }
                            repaint();
                        } else {
                            msg = "当前位置已有棋子，请重新落子";
                            repaint();
                        }

                    }
                } else {
                    msg = "该对方走棋";
                    JOptionPane.showMessageDialog(null, msg);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString("游戏信息" + msg, 130, 40);
        // 绘制棋盘
        for (int i = 0; i < 19; i++) {
            g.drawLine(10, 50 + 20 * i, 370, 50 + 20 * i);
            g.drawLine(10 + 20 * i, 50, 10 + 20 * i, 410);
        }

        // 标注点位
        g.fillOval(68, 108, 4, 4);
        g.fillOval(308, 108, 4, 4);
        g.fillOval(308, 348, 4, 4);
        g.fillOval(68, 348, 4, 4);
        g.fillOval(308, 228, 4, 4);
        g.fillOval(188, 108, 4, 4);
        g.fillOval(68, 228, 4, 4);
        g.fillOval(188, 348, 4, 4);
        g.fillOval(188, 228, 4, 4);

        // 绘制全部棋子
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (allChess[i][j] == 1) {
                    // 黑子
                    int tempX = i * 20 + 10;
                    int tempY = j * 20 + 70;
                    g.fillOval(tempX - 7, tempY - 7, 14, 14);
                }
                if (allChess[i][j] == 2) {
                    // 白子
                    int tempX = i * 20 + 10;
                    int tempY = j * 20 + 70;
                    g.setColor(Color.WHITE);
                    g.fillOval(tempX - 7, tempY - 7, 14, 14);
                    g.setColor(Color.BLACK);
                    g.drawOval(tempX - 7, tempY - 7, 14, 14);
                }
            }
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }


    public int[][] getAllChess() {
        return allChess;
    }

    public void setAllChess(int[][] allChess) {
        this.allChess = allChess;
    }


}

