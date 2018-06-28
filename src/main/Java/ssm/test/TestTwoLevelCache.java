package ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import ssm.dao.UserDAO;
import ssm.entity.User;
import ssm.util.MybatisUtil;

/**
 * create by tan on 2018/6/27
 * 测试二级缓存
 **/
public class TestTwoLevelCache {
    /**
     *
     * */
    @Test
    public void testCache() {
        // 获取 SqlSession 对象
        SqlSession session = MybatisUtil.getSqlSession();
        UserDAO userDAO = session.getMapper(UserDAO.class);
        User user = userDAO.getUserById(80);
        //System.out.println("user=" + user);
        session.close();

        // 再次获取 SqlSession 对象
        session = MybatisUtil.getSqlSession();
        userDAO = session.getMapper(UserDAO.class);
        User user2 = userDAO.getUserById(80);
        session.close();

        // 执行删除、提交操作
        session = MybatisUtil.getSqlSession();
        userDAO = session.getMapper(UserDAO.class);
        userDAO.deleteUser(85);
        session.commit();
        session.close();

        // 第三次获取 SqlSession 对象
        session = MybatisUtil.getSqlSession();
        userDAO = session.getMapper(UserDAO.class);
        User user3 = userDAO.getUserById(80);
        //System.out.println("user3=" + user2);
        session.close();

        session = MybatisUtil.getSqlSession();
        userDAO = session.getMapper(UserDAO.class);
        User user4 = userDAO.getUserById(80);
        //System.out.println("user3=" + user2);
        session.close();
    }
}
