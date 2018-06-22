package ssm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ssm.aop.Sleep;

/**
 * create by tan on 2018/6/21
 * 用于测试Aop
 **/
public class TestAoP {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop/applicationContext-POJO.xml");
        Sleep human = (Sleep)applicationContext.getBean("human");
        human.sleep();
    }
}
