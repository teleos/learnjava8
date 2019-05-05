package cn.liang.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Send implements Runnable{

    private DatagramSocket client ;
    //从控制台中获取数据
    private BufferedReader reader ;

    private String toHost;
    private int toPort;

    public Send(int port,String toHost,int toPort){

        try {
            client = new DatagramSocket(port);
            reader = new BufferedReader(new InputStreamReader(System.in));
            this.toHost = toHost;
            this.toPort = toPort;
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true){
            try {
                String data = reader.readLine();
                byte[] datas = data.getBytes();
                //封装成DatagramPacket包裹，需要指定目的地
                DatagramPacket packet = new DatagramPacket(datas, 0, datas.length,
                        new InetSocketAddress(toHost, toPort));
                    client.send(packet);
                if (data.equals("bye")) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
