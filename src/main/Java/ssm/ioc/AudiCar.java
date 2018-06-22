package ssm.ioc;

/**
 * create by tan on 2018/6/22
 * 奥迪汽车类
 **/
public class AudiCar implements Car{
    private String name; // 汽车系列名称

    @Override
    public void run() {
        System.out.println("奥迪" + name + "... is running");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
