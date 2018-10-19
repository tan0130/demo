package ssm.dao;

import ssm.entity.User;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * create by 1311230692@qq.com on 2018/10/17 13:49
 * 密码相关操作接口
 **/
public interface PasswordDAO {
    /**
     * 根据 email 查询用户信息，返回用户实体
     * @param email 要查询的 email
     * */
    User getUserByEmail(String email);

    /**
     * 根据 tel 查询用户信息，返回用户实体
     * @param tel 要查询的 tel
     * */
    User getUserByTel(String tel);

    /**
     * 发送 html 格式的邮件
     * @param to 要发送的对象
     * */
    ConcurrentHashMap<String, Object> sendHtmlEmail(String to);

    /**
     * 修改用户信息
     * @param user 用户实体
     * */
    void updatePassword(User user);
}
