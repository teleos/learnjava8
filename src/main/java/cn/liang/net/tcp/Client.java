package cn.liang.net.tcp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        System.out.println("##########客户端###########");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             Socket socket = new Socket("localhost", 8888);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
            System.out.println("请输入用户名：");
            String username = reader.readLine();

            System.out.println("请输入密码：");
            String password = reader.readLine();

            dos.writeUTF(username);
            dos.writeUTF(password);


            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String s = dis.readUTF();
            System.out.println(s);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
