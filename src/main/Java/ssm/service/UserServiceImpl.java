package ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ssm.dao.RedisDAO;
import ssm.dao.UserDAO;
import ssm.dao.impl.RedisDAOImpl;
import ssm.entity.User;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * create by 1311230692@qq.com on 2018/10/15 15:35
 * 用户操作业务逻辑层实现
 **/
@Service("UserService")
public class UserServiceImpl implements UserService{

    @Resource
    private UserDAO userDAO;

    @Resource
    private RedisDAOImpl redisDAO;

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Override
    public User getUserById(int id) {
        System.out.println("UserServiceImpl 查询员工信息");

        String userJson = redisDAO.get("user_" + String.valueOf(id));

        if (StringUtils.isEmpty(userJson)) {
            redisDAO.set("user_" + id, String.valueOf(id));
            System.out.println("redis 数据写入成功，写入 user_id 数据为：" + id);
        }
        System.out.println("redis 键值查询 user_id:" + redisDAO.get("user_" + id));


        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByIdAndPwd(int id, String password) {
        return userDAO.getUserByIdAndPwd(id, password);
    }

    @Override
    public void addUser(User user) {
        System.out.println("service 层添加用户");
        userDAO.addUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void updatePassword(User user) {
        System.out.println("修改密码");
        userDAO.updatePassword(user);
    }

    @Override
    public List<User> getUserByIdList(int id) {
        return userDAO.getUserByIdList(id);
    }
}
