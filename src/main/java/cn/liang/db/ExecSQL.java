package cn.liang.db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ExecSQL {


    public static void main(String[] args) {
        try (Scanner in = args.length == 0 ? new Scanner(System.in) :
                new Scanner(Paths.get(args[0]), "UTF-8")) {
            try(Connection conn = getConnection();
                Statement stat = conn.createStatement()){
                while (true){
                    if (args.length == 0) System.out.println("Error command or EXIT to exit");

                    if(!in.hasNextLine())
                        return;

                    String line = in.nextLine().trim();
                    if (line.equalsIgnoreCase("EXIT")) return;
                    if (line.endsWith(";")){
                        line = line.substring(0,line.length()-1);
                    }
                    boolean isResult = stat.execute(line);
                    if (isResult){
                        try(ResultSet rs = stat.getResultSet()){
                            showResultSet(rs);
                        }
                    }else {
                        int updateCount = stat.getUpdateCount();
                        System.out.println(updateCount+"rows updated");
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void showResultSet(ResultSet rs) throws SQLException {
//        检索此 ResultSet对象的列的数量，类型和属性。
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <=columnCount ; i++) {
            if (i>1) System.out.print(",");
            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();
        while (rs.next()){
            for (int i = 1; i <= columnCount; i++) {
                if (i>1) System.out.print(",");
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
    }

    public static Connection getConnection(){
        //创建properties对象
        Properties prop = new Properties();
        //加载.properties文件
        try (InputStream in = Files.newInputStream(Paths.get("src/main/resources/db.properties"))){
            prop.load(in);

            String driver = prop.getProperty("driverName");
            if (driver!=null)System.setProperty("jdbc.drivers",driver);

            String url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            return DriverManager.getConnection(url,username,password);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
}
