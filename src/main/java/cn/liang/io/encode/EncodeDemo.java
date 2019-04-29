package cn.liang.io.encode;


import java.io.*;

public class EncodeDemo {
/*字符编码*/


    public static void main(String[] args) {


        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        new FileInputStream("src\\main\\java\\cn\\liang\\io\\encode\\encode.txt"),"GBK"));

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("d:/aaa.txt"),"utf-8"))
        ) {

            String line ;
            while ((line =reader.readLine())!=null){
                String gbk = new String(line.getBytes(), "UTF-8");
                writer.write(gbk);
                writer.newLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
