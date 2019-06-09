package cn.liang.wx;

import java.util.Objects;


public class WxUser {
    private int uid;
    private String uname;
    private String pwd;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WxUser wxUser = (WxUser) o;
        return uid == wxUser.uid &&
                Objects.equals(uname, wxUser.uname) &&
                Objects.equals(pwd, wxUser.pwd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid, uname, pwd);
    }
}
