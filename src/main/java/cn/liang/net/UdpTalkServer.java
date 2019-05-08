package cn.liang.net;

import java.io.IOException;
import java.net.*;

/**
 * UDP在线交流服务端
 */
public class UdpTalkServer {
    public static void main(String[] args) {

        System.out.println("教师端启动。。。");

        new Thread(new Recieve(9999,"学生")).start();
        new Thread(new Send(10000, "localhost", 10001)).start();
    }
}
