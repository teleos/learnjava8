package cn.liang.net;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/*
 * 发送端：
 * 使用datagramSocket指定端口，创建发送端
 * 准备数据转成字节数组
 * 封装成DatagramPacket
 * 发送数据
 * 释放资源
 */
public class UdpClient {

    public static void main(String[] args) {
        System.out.println("发送方启动中。。。。");
        try (DatagramSocket socket = new DatagramSocket(8888)) {

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("d:/downloads/a.jpg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream(16);
            int len = 0;
            byte[] buf = new byte[1024*16];
            while ((len = bis.read(buf))!=-1){
                baos.write(buf,0,len);
            }
            baos.flush();

            byte[] datas = baos.toByteArray();

            DatagramPacket packet = new DatagramPacket(datas, datas.length, new InetSocketAddress("localhost", 9999));
            socket.send(packet);

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
