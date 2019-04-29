package cn.liang.io.encode;

import java.io.*;
import java.util.stream.Stream;

public class FileStream {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("src\\main\\java\\cn\\liang\\io\\encode\\EncodeDemo.java"), "utf-8"))) {

            Stream<String> lines = reader.lines();
            lines.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
