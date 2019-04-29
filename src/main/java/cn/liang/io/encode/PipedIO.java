package cn.liang.io.encode;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedIO {


    /*管道流*/
    public static void main(String[] args) {

        try {
            final PipedOutputStream out = new PipedOutputStream();
            final PipedInputStream in = new PipedInputStream(out);

            new Thread(()->{
                try {
                    out.write("hello world,pipe!".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (out!=null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread( ()->{
                try {

                    int data ;
                    while ( (data = in.read())!=-1 && out!=null) {
                        System.out.println((char) data);

                    }
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    if (in!=null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
