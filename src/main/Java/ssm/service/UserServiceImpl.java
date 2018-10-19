package ssm.service;

import org.springframework.stereotype.Service;
import ssm.dao.UserDAO;
import ssm.entity.User;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by 1311230692@qq.com on 2018/10/15 15:35
 * 用户操作业务逻辑层实现
 **/
@Service("UserService")
public class UserServiceImpl implements UserService{

    @Resource
    private UserDAO userDAO;

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByIdAndPwd(int id, String password) {
        return userDAO.getUserByIdAndPwd(id, password);
    }

    @Override
    public void addUser(User user) {
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
