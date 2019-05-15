package cn.liang.wx;

import java.util.Scanner;

public class WX_Transfer {

    public static void main(String[] args) {
        System.out.println("***************微信转账模块***************");
        Scanner input = new Scanner(System.in);
        System.out.println("请输入您的微信账号：");
        String uname = input.next();
        System.out.println("请输入您的密码");
        String pwd = input.next();
        WxDao wxDao = new WxDao();
        WxUser wxUser = wxDao.checkLogin(uname, pwd);
        if (wxUser!=null){
            System.out.println("恭喜你登录成功");

            System.out.println("用户名："+uname);
            WxMoney money = wxDao.getMoney(uname);

            //获取好友的名称与金额
            System.out.println("请输入转账好友名称");
            String fname = input.next();
            System.out.println("请输入转账金额");
            double m = input.nextDouble();
            wxDao.transfer(m,uname,fname);
            //重新查询账号金额
            WxMoney money1 = wxDao.getMoney(uname);
            System.out.println("转账成功，您当前用户余额为："+money1.getMoney());
        }else {
            System.out.println("你的账号或密码输入错误");
        }
    }
}
