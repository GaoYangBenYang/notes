package Beans;

public class Orders {

    private String oname;

    public Orders(){
        System.out.println("第一步，无参构造器");
    }

    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("第二步，设置属性的值");
    }

    //创建执行 初始化方法
    public void initMethod(){
        System.out.println("第三步，调用初始化方法");
    }

    //创建执行 销毁的方法
    public void destroyMethod(){
        System.out.println("第五步，销毁的方法");
    }

}
