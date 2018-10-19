package ssm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * create by 1311230692@qq.com on 2018/10/15 15:29
 * 用户实体类
 * 测试 mybatis 二级缓存，User类要实现 Serializable 接口
 * */
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // 用户编号
    private String nick_name; // 用户昵称
    private String user_name; // 用户姓名
    private String user_password; // 用户密码
    private String user_sex; // 用户性别
    private String user_tel; // 用户联系方式
    private String user_email; // 用户邮箱

    public User() {
    }

    public User(String nick_name, String user_name, String user_password, String user_sex, String user_tel, String user_email) {
        this.nick_name = nick_name;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_sex = user_sex;
        this.user_tel = user_tel;
        this.user_email = user_email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nick_name='" + nick_name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_tel='" + user_tel + '\'' +
                ", user_email='" + user_email + '\'' +
                '}';
    }
}
