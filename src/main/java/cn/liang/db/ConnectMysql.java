package cn.liang.db;

import java.sql.*;

public class ConnectMysql {

    public static void main(String[] args) {
        Connection connection =null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取链接

            connection = JDBCUtils.getConnection();
            //3.传输语句
            PreparedStatement preparedStatement = connection.prepareStatement("select * from good");
            //4.得到结果
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("id\tname\tprice\tcounter\taddress");
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int counter = resultSet.getInt("counter");
                String address = resultSet.getString("address");
                System.out.println(id+"\t"+name+"\t"+price+"\t"+counter+"\t"+address);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection);
        }
    }
}
