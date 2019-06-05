package cn.liang.five_chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


/**
 * 绘制服务端界面
 */

public class Server extends JFrame implements ActionListener {
    private JPanel contentPanel, panel1, goPanel;

    private JLabel portLabel, inMsgLabel;
    private JTextField portField, msgField;
    private JScrollPane jScrollPane;
    private JTextArea msgJta;
    private JButton monitorButton, sendButton;

    private JButton b1, eb;


    //保存棋子坐标
    int x = 0,y = 0;
    //保存之前下过的全部棋子的坐标
    //0:这个点没有棋子，1：这个点是黑子,2:这个点是白子
    int [][] allChess = new int[19][19];
    boolean isBlack = false;//自己是白棋
    boolean canPlay = false;//标识当前游戏是否可以继续
    String[] ss = new String [10]; //消息数组


    public Server() {

        //初始化组件
        initComponent();

        monitorButton.addActionListener(this);
        sendButton.addActionListener(this);
    }


    private void initComponent() {
        contentPanel = (JPanel) getContentPane();
        setSize(new Dimension(570, 630));//设置窗口大小
        setTitle("AlphaGo五子棋游戏---服务器端");
        panel1 = new JPanel(null);//设置布局为定位布局

        portLabel = new JLabel("端口号：");
        portLabel.setFont(new Font("宋体", 0, 14));
        portLabel.setBounds(new Rectangle(22, 0, 72, 28));

        portField = new JTextField("4700");
        portField.setBounds(new Rectangle(73, 0, 45, 24));

        monitorButton = new JButton("侦听");
        monitorButton.setBounds(new Rectangle(120, 0, 73, 25));
        monitorButton.setFont(new Font("宋体", 0, 14));

        inMsgLabel = new JLabel("请输入信息");
        inMsgLabel.setBounds(new Rectangle(200, 0, 87, 28));
        msgField = new JTextField();
        msgField.setBounds(new Rectangle(278, 0, 154, 24));

        sendButton = new JButton("发送");
        sendButton.setBorder(BorderFactory.createEtchedBorder());
        sendButton.setFont(new Font("Dialog", 0, 14));
        sendButton.setBounds(new Rectangle(434, 0, 43, 25));

        jScrollPane = new JScrollPane();
        jScrollPane.setBounds(new Rectangle(23, 28, 493, 89));

        msgJta = new JTextArea();
        msgJta.setText("聊天内容");

        panel1.add(portLabel);
        panel1.add(portField);
        panel1.add(monitorButton);
        panel1.add(inMsgLabel);
        panel1.add(msgField);
        panel1.add(sendButton);
        panel1.add(jScrollPane);
        jScrollPane.getViewport().add(msgJta);

        contentPanel.setLayout(null);
        contentPanel.add(panel1);

        goPanel = new GobangPanel();
        goPanel.setBounds(22, 160, 490, 460);
        panel1.setBounds(0, 0, 540, 120);


        contentPanel.add(goPanel);
        goPanel.setLayout(null);

        b1 = new JButton("悔棋");
        eb = new JButton("退出");
        b1.setBounds(400, 300, 70, 30);
        eb.setBounds(400, 350, 70, 30);
        goPanel.add(b1);
        goPanel.add(eb);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    ServerSocket server = null;
    Socket socket = null;
    PrintWriter os = null;
    BufferedReader reader = null;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == monitorButton) {
            int port = Integer.parseInt(portField.getText().trim());
            listenClient(port);
        } else if (e.getSource() == sendButton) {
            String data = msgField.getText().trim();
            sendData(data);
            msgField.setText("");
        }

    }

    //等待客户端请求的方法
    private void listenClient(final int port) {
        if (monitorButton.getText().trim().equals("侦听")) {

//            new Thread(()->{

            try {
                server = new ServerSocket(port);
                monitorButton.setText("正在侦听...");
                socket = server.accept();

                sendData("已经连接成功");

                monitorButton.setText("正在聊天...");
                msgJta.append("\n客户端已经连接到服务器\n");

                new Thread(() -> {

                    while (true) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);

                            if (reader == null) {
                                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            }

                            if (reader.ready()) {
                                String cmd = reader.readLine();
                                msgJta.append("客户端：" + cmd + "\n");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            }).start();
        }
    }

    private void sendData(String msg) {
        try {
            if (os == null)
                os = new PrintWriter(socket.getOutputStream());
            os.println("服务器" + msg);
            os.flush();

            if (!msg.equals("已经连接成功")) {
                msgJta.append("服务器：" + msg + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Server().setVisible(true));
    }


    class GobangPanel extends JPanel {

        private String msg ;


        public GobangPanel(){

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.drawString("游戏信息"+msg ,130,40);
            for (int i = 0; i < 19; i++) {//绘制棋盘
                g.drawLine(10,50+20*i,370,50+20*i); //画棋盘
                g.drawLine(10+20*i,50,10+20*i,410);

                //标注点位
                g.fillOval(68,108,4,4);
                g.fillOval(308,108,4,4);
                g.fillOval(308,348,4,4);
                g.fillOval(68,348,4,4);

                g.fillOval(308,228,4,4);
                g.fillOval(188,108,4,4);
                g.fillOval(68,228,4,4);
                g.fillOval(188,348,4,4);
                g.fillOval(188,228,4,4);
            }
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
