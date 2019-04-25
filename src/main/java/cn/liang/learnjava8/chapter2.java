package cn.liang.learnjava8;

import java.io.*;

public class chapter2 {


    public static void main(String[] args) {
        try {
            String s =processFile(br -> br.readLine() + br.readLine());
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static String  processFile(BufferReaderProcessor processor)
            throws IOException{
        String path = chapter2.class.getClassLoader(). getResource("").getPath();
        try (BufferedReader br = new BufferedReader(new FileReader(path+"../resources/a.xml"))){
            return processor.process(br);
        }
    }


    /**
     * 传统方法处理IO，有很大的局限性
     * @return
     * @throws IOException
     */
    public static String processFile() throws IOException {
        String path = chapter2.class.getClassLoader(). getResource("").getPath();
        try (BufferedReader br = new BufferedReader(new FileReader(path+"../resources/a.xml"))){

            return br.readLine();
        }

    }
}


@FunctionalInterface
interface BufferReaderProcessor{
    String process(BufferedReader br) throws IOException;
}