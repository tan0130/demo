package ssm.aop;

/**
 * create by tan on 2018/6/21
 * Human类，用于实现Sleep接口
 **/
public class Human implements Sleep{
    @Override
    public void sleep() {
        System.out.println("睡觉.......");
    }
}
