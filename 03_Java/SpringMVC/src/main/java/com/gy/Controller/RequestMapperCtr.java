package com.gy.Controller;

import com.gy.Pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class RequestMapperCtr {

    @RequestMapping("/test")
    public String success(){
        return "success";
    }

    @RequestMapping(value = "/method",method = RequestMethod.POST)
    public void methodTest(){
        System.out.println("method参数使用");
    }

    @GetMapping(value = "/method")
    public void getMapping(){
        System.out.println("@GetMapping注解");
    }

    @GetMapping(value = "/params",params = {"username"})
    public void paramsTest(){
        System.out.println("@GetMapping注解params参数");
    }

    //根据控制器形参获取请求参数
    @RequestMapping("/testParams")
    public void testParams(@RequestParam() Integer id , String username ){
        System.out.println("di:"+id+",username:"+username);
    }

    //cookie映射注解 @CookieValue(value  name   required默认true必须传值    defaultValue设置默认值) 处理请求参数不一致
    @RequestMapping("/cookieParams")
    public void cookieParams(Integer id,@RequestParam("user_name") String username,@CookieValue("JSESSIONID") String jsessionid){
        System.out.println("di:"+id+",username:"+username+",JSESSIONID"+jsessionid);
    }
    //实体类形参注解
    @RequestMapping("/pojo")
    public void testPojo(User user){
        System.out.println("di:"+user.getId()+",username:"+user.getUsername());
    }

    //ModelAndView
    @RequestMapping("/testMAV")
    public ModelAndView modelAndView(){

        /**
         * ModelAndView有Model和View功能
         * Model主要用于向请求域共享数据
         * view主要用于设置试图，实现页面跳转
         */
        ModelAndView modelAndView = new ModelAndView();
        //向请求域共享数据
        modelAndView.addObject("testScope","hello");
        //设置试图，实现页面跳转
        modelAndView.setViewName("index");
        System.out.println(modelAndView.toString());
        return modelAndView;
    }
    //通过Model进行共享数据
    @RequestMapping("/testM")
    public String testM(Model model){
        //向请求域共享数据
        model.addAttribute("testScope","hello2");
        return "success";
    }

    //通过Map进行共享数据
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        //向请求域共享数据
        map.put("testScope","hello3");
        System.out.println(map);
        return "success";
    }


    //通过ModelMap进行共享数据
    @RequestMapping("/testMM")
    public String testMM(ModelMap modelMap){
        //向请求域共享数据
        modelMap.addAttribute("testScope","hello4");
        System.out.println(modelMap);
        return "success";
    }

    //使用原生ServletAPI的httpSession
    @RequestMapping("/testSession")
    public String testSession(HttpSession httpSession){
        //向session请求域共享数据
        httpSession.setAttribute("testScope","hello session");
        return "success";
    }

    @RequestMapping("/testForward")
    public String testForward(){
        System.out.println("转发");
        return "forward:/method";
    }

    @RequestMapping("/testRedirect")
    public String testRedirect(){
        System.out.println("重定向,请求/testHello");
        return "redirect:/testHello";
    }
}
