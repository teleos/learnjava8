package cn.liang.net.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        System.out.println("#######服务端#######");
        try (ServerSocket serverSocket = new ServerSocket(8888)) {

            Socket accept = serverSocket.accept();
            System.out.println("一个客户端建立了链接");

            DataInputStream dis = new DataInputStream(accept.getInputStream());

            String username = dis.readUTF();
            String password = dis.readUTF();
            String result;
            if (username.equals("zhangsan") && password.equals("1234")){
                result = "登录成功";
            }else {
                result = " 用户名或者密码错误";
            }

            DataOutputStream dos = new DataOutputStream(accept.getOutputStream());
            dos.writeUTF(result);
            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
