package cn.liang.net;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


/**
 * UDP在线交流客户端
 */
public class UdpTalkClient {


    public static void main(String[] args) {


        //
        System.out.println("学生端启动中。。。");
        new Thread(new Recieve(10001));
        new Thread(new Send(8888,"localhost",9999)).start();
    }
}
