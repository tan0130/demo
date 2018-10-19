package ssm.util;

/**
 * create by 1311230692@qq.com on 2018/10/17 16:14
 * 产生 6 位随机数字的校验码
 **/
public class RandomUtil {
    public static int getRandNum() {
        return (int)((Math.random() * 9 + 1) * 100000);
    }
}
