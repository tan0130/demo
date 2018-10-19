package ssm.dao;

import ssm.entity.User;

import java.util.List;

/**
 * create by 1311230692@qq.com on 2018/10/15 14:42
 * 用户操作接口
 **/
public interface UserDAO {
    /**
     * 查询所有用户
     * */
    List<User> getAllUser();

    /**
     * 根据 id 查询用户信息
     * @param id 需要查询的 id
     * */
    User getUserById(int id);

    /**
     * 根据 id 和密码来查询用户信息
     * @param id 需要查询的 id
     * @param password 对应的密码
     * */
    User getUserByIdAndPwd(int id, String password);

    /**
     * 添加用户信息
     * @param user 用户实体
     * */
    void addUser(User user);

    /**
     * 根据 id 删除用户信息
     * @param id 要删除的 id 信息
     * */
    void deleteUser(int id);

    /**
     * 更新用户信息
     * @param user 用户实体
     * */
    void updateUser(User user);

    /**
     * 修改用户信息
     * @param user 用户实体
     * */
    void updatePassword(User user);

    /**
     * 根据 id 查询用户信息，返回用户实体
     * @param id 要查询的 id
     * */
    List<User> getUserByIdList(int id);
}
