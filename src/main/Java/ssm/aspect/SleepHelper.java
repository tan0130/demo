package ssm.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * create by tan on 2018/6/21
 * 睡觉辅助类，用于处理除睡觉之外的所有事情
 * 使用AspectJ提供的注解，这种方法更简单
 **/
@Aspect // 标志切面
public class SleepHelper {
    public SleepHelper() {

    }

    @Pointcut("execution(* *.sleep())") // 指定了切点
    public void sleepPoint() {}

    @Before("sleepPoint()") // 指定了运行时通知
    public void beforeSleep() {
        System.out.println("睡觉前.......脱衣服");
    }

    @AfterReturning("sleepPoint()")
    public void afterSleep() {
        System.out.println("起床后......穿衣服");
    }
}
