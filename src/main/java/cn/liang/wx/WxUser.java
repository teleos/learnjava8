package cn.liang.wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wx_user", schema = "test", catalog = "")
public class WxUser {
    private int uid;
    private String uname;
    private String pwd;

    @Id
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "uname")
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Basic
    @Column(name = "pwd")
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
