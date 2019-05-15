package cn.liang.wx;

import cn.liang.db.JDBCUtils;

import java.sql.*;

public class WxDao {

    /**
     * 验证用户登录
     * @param user
     * @param password
     * @return
     */
    public WxUser checkLogin(String user,String password){

        WxUser u = null;
        try (Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM wx_user WHERE uname=? AND pwd=?"))
        {
            ps.setString(1,user);
            ps.setString(2,password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new WxUser();
                    u.setUid(rs.getInt("uid"));
                    u.setUname(rs.getString("uname"));
                    u.setPwd(rs.getString("pwd"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;

    }


    public WxMoney getMoney(String user){

        final String sql = "select * from wx_money where uname=?";
        WxMoney money = null;

        try (Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1,user);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    money = new WxMoney();
                    money.setUname(rs.getString("uname"));
                    money.setMoney(rs.getDouble("money"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return money;
    }

    public void transfer(double money,String uname,String fname){

       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try  {
           conn = JDBCUtils.getConnection();
           conn.setAutoCommit(false);//取消自动提交，开启事务
           ps = conn.prepareStatement("UPDATE wx_money SET money = money-? WHERE uname = ?");
           ps.setDouble(1,money);
           ps.setString(2,uname);
           int i = ps.executeUpdate();

           ps = conn.prepareStatement("UPDATE wx_money SET money = money+? WHERE uname = ?");
           ps.setDouble(1,money);
           ps.setString(2,fname);
           int j = ps.executeUpdate();

           if (i == j  && j==1)
               conn.commit();

       }catch (SQLException e){
           e.printStackTrace();
           try {
               if (conn!=null) conn.rollback();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }
       }finally {
           if (rs!=null) {
               try {
                   rs.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
           if (ps!=null) {
               try {
                   ps.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
           JDBCUtils.close(conn);
       }

    }
}
