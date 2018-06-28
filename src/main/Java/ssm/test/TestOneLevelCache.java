package ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import ssm.dao.UserDAO;
import ssm.entity.User;
import ssm.util.MybatisUtil;

/**
 * create by tan on 2018/6/27
 * 测试mybatis的一级缓存，作用域是session
 **/
public class TestOneLevelCache {

    /**
     * 普通测试
     * */
    @Test
    public void testCache() {
        SqlSession session = MybatisUtil.getSqlSession();
        UserDAO userDAO = session.getMapper(UserDAO.class);
        // 第一次查询
        User user = userDAO.getUserById(80);
        session.close();

        // 第二次查询,不同的SqlSession
        session = MybatisUtil.getSqlSession();
        userDAO = session.getMapper(UserDAO.class);
        User user1 = userDAO.getUserById(80);

        System.out.println(user.equals(user1)); // 返回true，第二次查询是从缓存中取值
        session.close();
    }
    /**
     * 有delete 和 commit操作
     * */
    @Test
    public void testCache2() {
        SqlSession session = MybatisUtil.getSqlSession();
        UserDAO userDAO = session.getMapper(UserDAO.class);
        // 第一次查询
        User user = userDAO.getUserById(80);

        // 执行 delete 和 commit 操作
        userDAO.deleteUser(88);
        session.commit();

        // 第二次查询
        User user1 = userDAO.getUserById(80);
        System.out.println(user.equals(user1)); // 返回true，第二次查询是从缓存中取值
        session.close();
    }
}
