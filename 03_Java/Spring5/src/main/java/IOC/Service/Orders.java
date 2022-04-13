package IOC.Service;

/**
 * 使用有参构造器进行依赖注入
 */
public class Orders {

    private String oname;
    private String address;

    public Orders(String oname,String address){
        this.oname = oname;
        this.address = address;
    }
}
