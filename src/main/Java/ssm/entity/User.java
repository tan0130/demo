package ssm.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * create by tan on 2018-05-03
 * 用户实体类
 * */
@Entity
public class User {
    @Id
    private int userid; // 用户编号
    private String nickname; // 用户昵称
    private String username; // 用户姓名
    private String password; // 用户密码
    private String sex; // 用户性别
    private String tel; // 用户联系方式
    private String email; // 用户邮箱
    private String mark; // 备注

    public User() {
    }

    public User(int userid, String nickname, String username, String password, String sex, String tel, String email, String mark) {
        this.userid = userid;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.tel = tel;
        this.email = email;
        this.mark = mark;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
