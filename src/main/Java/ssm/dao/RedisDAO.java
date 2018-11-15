package ssm.dao;

/**
 * create by 1311230692@qq.com on 2018/11/7 14:49
 * redis 操作接口类
 **/
public interface RedisDAO {

    /**
     * String 获取键值
     * @param key 需要查询的键
     * */
    String get(String key);

    /**
     * String 设置键值
     * @param key 键
     * @param value 值
     * */
    String set(String key, String value);

    /**
     * Hash 获取键值
     * @param hkey hash key
     * @param key 键
     * */
    String hget(String hkey, String key);

    /**
     * Hash 设置键值
     * @param hkey hash 键
     * @param key 键
     * @param value 键值
     * */
    long hset(String hkey, String key, String value);
}
