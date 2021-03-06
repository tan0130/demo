package ssm.dbconnection;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * create by tan on 2018-05-15
 * 日志写入数据库，配置文件数据库连接文件
 **/
public class ConnectionFactory {
    public static Connection getDatabaseConnection() throws SQLException {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&serverTimezone=GMT%2B8");
        ds.setUsername("root");
        ds.setPassword("liuke666");
        System.out.println("日志系统连接数据库");
        return ds.getConnection();
    }
}
