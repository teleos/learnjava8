package cn.liang.net;


import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 接收端：
 * 使用datagramSocket指定端口，创建接收端
 * 阻塞式接收包裹receive（DatagramPacket p）
 * 分析数据
 * 释放资源
 */
public class UdpServer {
    public static void main(String[] args) {

        System.out.println("接收方启动中。。。。");
        try (DatagramSocket socket = new DatagramSocket(9999)) {
            byte[] container = new byte[1024*16];
            DatagramPacket packet = new DatagramPacket(container, 0, container.length);
            socket.receive(packet);
            byte[] data = packet.getData();
            int length = packet.getLength();


            ByteArrayInputStream inputStream = new ByteArrayInputStream(data, 0, length);

            FileOutputStream fos = new FileOutputStream("d:/b.jpg");
            byte[] bytes = new byte[1024];
            int len = 0;
            while ( (len = inputStream.read(bytes))!=-1){
                fos.write(bytes,0,len);
            }

            fos.flush();
//            System.out.println(data);
            System.out.println("以下载完毕！！");


        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
