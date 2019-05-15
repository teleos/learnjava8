package cn.liang.wx;

import java.util.Objects;

public class WxMoney {
    private String uname;
    private double money;


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WxMoney wxMoney = (WxMoney) o;
        return Double.compare(wxMoney.money, money) == 0 &&
                Objects.equals(uname, wxMoney.uname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uname, money);
    }
}
