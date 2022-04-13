package AOP.AspectJAnnotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//增强的类
@Component
@Aspect//生成代理对象
public class UserProxy {
    //前置通知
    @Before(value = "execution(* AOP.AspectJAnnotation.User.add())")
    public void before(){
        System.out.println("before.........前置通知");
    }

    //后置通知（返回通知）
    @AfterReturning(value = "execution(* AOP.AspectJAnnotation.User.add())")
    public void afterReturning(){
        System.out.println("AfterReturning.........后置通知");
    }

    //环绕通知
    @Around(value = "execution(* AOP.AspectJAnnotation.User.add())")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("Around.........环绕通知之前");
        //增强的方法
        proceedingJoinPoint.proceed();
        System.out.println("Around.........环绕通知之后");
    }

    //最终通知
    @After(value = "execution(* AOP.AspectJAnnotation.User.add())")
    public void after(){
        System.out.println("After.........最终通知");
    }

    //异常通知
    @AfterThrowing(value = "execution(* AOP.AspectJAnnotation.User.add())")
    public void afterThrowing(){
        System.out.println("AfterThrowing.........异常通知");
    }

}
