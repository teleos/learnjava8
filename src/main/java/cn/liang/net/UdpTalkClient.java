package cn.liang.net;


/**
 * UDP在线交流客户端
 */
public class UdpTalkClient {


    public static void main(String[] args) {


        //
        System.out.println("学生端启动中。。。");
        new Thread(new Recieve(10001,"老师")).start(); //接收数据
        new Thread(new Send(8888,"localhost",9999)).start();//发送数据
    }
}
