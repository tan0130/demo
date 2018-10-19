//package ssm.test;
//
//
//import org.junit.runners.model.InitializationError;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.util.Log4jConfigurer;
//
//import java.io.FileNotFoundException;
//
///**
// * create by tan on 2018/6/28
// * 测试类加载log4j日志文件
// **/
//public class JUnit4ClassRunner extends SpringJUnit4ClassRunner {
//    static {
//        try {
//            Log4jConfigurer.initLogging("log4j.propertiesbak");
//        } catch (FileNotFoundException ex) {
//            System.err.println("cannot Initialize log4j");
//        }
//    }
//
//    public JUnit4ClassRunner(Class<?> clazz) throws InitializationError {
//        super(clazz);
//    }
//
//}
