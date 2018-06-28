package ssm.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * create by tan on 2018/6/27
 * 用单例模式创建SqlSessionFactory
 **/
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory = null;
    static {
        String resource = "MyBatis.xmlbak";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
