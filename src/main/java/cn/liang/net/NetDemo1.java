package cn.liang.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetDemo1 {

    /*
     *  获取IP地址
     *
     */

    public static void main(String[] args) {
        try {
            //获取本机ip对象
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println(addr.getHostAddress());
            System.out.println(addr.getHostName());

            //获取指定域名的ip对象
            InetAddress addr2 = InetAddress.getByName("www.google.com");
            System.out.println(addr2.getHostAddress());
            System.out.println(addr2.getHostName());

            //根据IP地址获取IP对象
            InetAddress addr3 = InetAddress.getByName("69.171.245.84");
            System.out.println(addr3.getHostName());
            System.out.println(addr3.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
