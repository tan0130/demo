package ssm.dao;

import ssm.entity.User;

import java.util.List;

/**
 * create by tan on 2018-05-03
 * 用户操作
 * */
public interface UserDAO {
    public List<User> getAllUser(); // 查询所有用户
    public User getUserById(int id); // 根据ID查询用户信息
    public User getUserByIdAndPwd(int id, String password); // 根据ID和密码来查询用户信息
}
