package ssm.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPool;
import ssm.dao.RedisDAO;

import javax.annotation.Resource;

/**
 * create by 1311230692@qq.com on 2018/11/7 14:56
 * redis 操作实现类
 **/
@Repository("redisDAO")
public class RedisDAOImpl implements RedisDAO {

    @Resource
    private JedisPool jedisPool;

    @Override
    public String get(String key) {
        return jedisPool.getResource().get(key);
    }

    @Override
    public String set(String key, String value) {
        System.out.println("RedisDAOImpl 添加缓存数据");
        return jedisPool.getResource().set(key, value);
    }

    @Override
    public String hget(String hkey, String key) {
        return jedisPool.getResource().hget(hkey, key);
    }

    @Override
    public long hset(String hkey, String key, String value) {
        return jedisPool.getResource().hset(hkey, key, value);
    }
}
