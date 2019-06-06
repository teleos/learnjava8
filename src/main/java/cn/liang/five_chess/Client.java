package cn.liang.five_chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class Client extends JFrame implements ActionListener,Transmit{

    private JPanel contentPanel ,panel1,goPanel;
    private JLabel addressLabel,portLabel,inputMsgLabel;
    private JTextField addressField,portField, msgField;
    private JTextArea jta;
    private JScrollPane scrollPane;
    private JButton chatButton,sendButton;
    private JButton b1  ,eb;




    public Client(){

        initComponent();
        ((GobangPanel) goPanel).setCanPlay(false);
        ((GobangPanel) goPanel).isBlack = true;
        chatButton.addActionListener(this);
        sendButton.addActionListener(this);
    }

    Socket socket = null;
    PrintWriter out = null  ;
    BufferedReader reader = null;

    private void bindServer(SocketAddress address) {
        if (chatButton.getText().trim().equals("连接")) {

            try  {
                socket = new Socket();
                socket.connect(address);

                out = new PrintWriter(socket.getOutputStream());

                sendData("客户端连接成功");
                chatButton.setText("已经连接");
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


                new Thread(()->{
                    try {
                         while (true) {

                             TimeUnit.MILLISECONDS.sleep(100);

                             if (reader.ready()){
                                 String cmd = reader.readLine();
                                 jta.append( "服务端："+cmd+"\n");
                                 String[] ss = cmd.split("\\|");
                                 if (cmd.startsWith("move")){

                                     int x = Integer.parseInt(ss[1]);
                                     int y = Integer.parseInt(ss[2]);
                                     int[][] allChess = ((GobangPanel) goPanel).getAllChess();
                                     allChess[x][y] = 2;
                                     goPanel.repaint();
//                                    canPlay = true;
                                     ((GobangPanel) goPanel).setCanPlay(false);
                                 }
                             }
                         }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendData(String msg) {
        if (out!=null){
            out.println(msg);
            jta.append("客户端："+msg+"\n");
            out.flush();
        }
    }

    private void initComponent() {
        setTitle("AlphaGo五子棋——————客户端");
        setSize(new Dimension(540,610));

        Font font = new Font("宋体", 0, 14);
        contentPanel = (JPanel) this.getContentPane();
        contentPanel.setLayout(null);
        panel1 = new JPanel(null);
        goPanel = new GobangPanel(this);
        goPanel.setLayout(null);

        addressLabel = new JLabel("服务器地址：");
        addressLabel.setFont(font);
        addressLabel.setBounds(new Rectangle(20,22,87,28));

        addressField = new JTextField("127.0.0.1");
        addressField.setBounds(new Rectangle(114,26,108,24));

        portLabel = new JLabel("端口号");
        portLabel.setFont(font);
        portField = new JTextField("4700");
        portField.setBounds(new Rectangle(320,27,108,24));

        chatButton = new JButton("连接");
        chatButton .setFont(new Font("Dialog",0,14));
        chatButton.setBounds(new Rectangle(440,28,73,25));
        chatButton.setBorder(BorderFactory.createEtchedBorder());

        inputMsgLabel = new JLabel("服务器输入信息");
        inputMsgLabel.setBounds(new Rectangle(23,57,87,28));
        inputMsgLabel.setFont(font);

        msgField = new JTextField();
        msgField.setBounds(new Rectangle(114,60,314,24));

        sendButton = new JButton("发送");
        sendButton.setActionCommand("connectButton");
        sendButton.setBorder(BorderFactory.createEtchedBorder());
        sendButton.setBounds(new Rectangle(440,58,73,25));

        jta = new JTextArea();

        scrollPane = new JScrollPane();
        scrollPane.setBounds(new Rectangle(23,85,493,69));

        jta.setText("聊天内容:\n");
        panel1.add(addressLabel);
        panel1.add(addressField);
        panel1.add(portLabel);
        panel1.add(portField);
        panel1.add(chatButton);
        panel1.add(inputMsgLabel);
        panel1.add(msgField);
        panel1.add(sendButton);
        panel1.add(scrollPane);

        scrollPane.getViewport().add(jta);
        contentPanel.add(panel1);
        contentPanel.add(goPanel);
        panel1.setBounds(0,0,540,160);
        goPanel.setBounds(22,160,490,460);

        b1 = new JButton("悔棋");
        eb = new JButton("退出");
        b1.setBounds(400,300,70,30);
        eb.setBounds(400,350,70,30);
        goPanel.add(b1);
        goPanel.add(eb);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            new Client().setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chatButton){
            String host = addressField.getText().trim();
            int port = Integer.parseInt(portField.getText().trim());
            bindServer(new InetSocketAddress(host,port));

        } else if(e.getSource() == sendButton) {

            if (out != null) {
                String msg = msgField.getText().trim();
                sendData(msg);
                msgField.setText("");
            }
        }
    }




}
