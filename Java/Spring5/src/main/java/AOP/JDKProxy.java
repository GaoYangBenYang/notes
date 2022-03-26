package AOP;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class JDKProxy {

    public static void main(String[] args){

        Class[] interfaces = {UserDao.class};

//        //创建接口实现类的代理方法

//        //匿名内部类
//        Proxy.newProxyInstance(
//                JDKProxy.class.getClassLoader(), interfaces, new InvocationHandler() {
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        //增强方法
//                        return null;
//                    }
//                });

        UserDaoImpl userDao = new UserDaoImpl();

        //创建类实现InvocationHandler接口
        UserDao dao = (UserDao) Proxy.newProxyInstance(
                JDKProxy.class.getClassLoader(), interfaces, new UserDaoProxy(userDao));

        int res = dao.add(1,2);
        System.out.println(res);
    }
}

class UserDaoProxy implements InvocationHandler{

    //1.创建的是谁的代理对象，就把谁传进来
    //通过有参构造器传递值
    private Object obj;
    public UserDaoProxy(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //方法之前
        System.out.println("方法之前执行。。。。"+method.getName()+":传递参数.."+ Arrays.toString(args));

        //被增强的方法
        Object res = method.invoke(obj,args);

        //方法之后
        System.out.println("方法之后执行。。。。"+method.getName()+":传递参数.."+ Arrays.toString(args));
        System.out.println("方法之后执行。。。。"+obj);

        return res;
    }
}