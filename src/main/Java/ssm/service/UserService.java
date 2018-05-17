package ssm.service;

import ssm.entity.User;

import java.util.List;

/**
 * create by tan on 2018-05-03
 * 业务逻辑层，封装用户操作
 * */
public interface UserService {
    public List<User> getAllUser(); // 查询所有用户
    public User getUserById(int id); // 根据ID查询用户信息
    public User getUserByIdAndPwd(int id, String password); // 根据ID和密码查询用户信息
    public void addUser(User user); // 添加用户信息
    public void deleteUser(int id); // 根据ID删除用户信息
    public void updateUser(User user); // 更新用户信息
    public void updatePassword(User user); // 修改用户密码
    public List<User> getUserById1(int id); // 根据ID查询用户信息
}
