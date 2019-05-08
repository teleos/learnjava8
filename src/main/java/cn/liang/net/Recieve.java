package cn.liang.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Recieve implements Runnable {

    private DatagramSocket server ;
    private String from;

    public Recieve(int reciveSocket,String from){
        this.from = from;
        try  {
            server = new DatagramSocket(reciveSocket);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            byte container[] = new byte[1024 * 16];
            DatagramPacket p = new DatagramPacket(container, 0, container.length);
            try {
                server.receive(p);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String s = new String(p.getData(), 0, p.getLength());

            System.out.println(from +":"+ s);
            if (s.equals("bye")) break;
        }
    }
}
