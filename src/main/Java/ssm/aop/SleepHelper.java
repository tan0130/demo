package ssm.aop;

import org.aspectj.lang.JoinPoint;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * create by tan on 2018/6/21
 * 睡觉辅助类，用于处理除睡觉之外的所有事情
 * 基于代理的AoP
 **/
public class SleepHelper implements MethodBeforeAdvice, AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("起床后......穿衣服");
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("睡觉前.......脱衣服");
    }
}
