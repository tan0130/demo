package ssm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ssm.ioc.AudiCar;
import ssm.ioc.Car;

/**
 * create by tan on 2018/6/22
 * 用于测试IoC 依赖注入
 **/
public class TestIoC {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/applicationContext-ioc.xml");
        Car car = (Car)applicationContext.getBean("audiCar");
        car.run();
    }
}
