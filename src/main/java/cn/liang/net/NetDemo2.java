package cn.liang.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.stream.Stream;

public class NetDemo2 {
    public static void main(String[] args) {

        //定位指定的应用程序
        InetSocketAddress address =new InetSocketAddress("www.baidu.com", 80);
        System.out.println(address.getAddress());
        System.out.println(address.getHostName());
        System.out.println(address.getHostString());
        System.out.println(address.getPort());

        System.out.println("#####################模拟爬虫######################");


        try {
            URL url = new URL("https://www.dianping.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
            InputStream in = connection.getInputStream();
            Stream<String> lines = new BufferedReader(new InputStreamReader(in, "utf-8")).lines();
            lines.forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
