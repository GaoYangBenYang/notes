package IOC.Collect;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Stu {
    //数组类型属性
    private String[] course;
    //list集合类型属性
    private List<String> list;
    //map集合类型属性
    private Map<String,String> map;
    //srt集合类型属性
    private Set<String> set;

    public void setCourse(String[] course){
        this.course = course;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void test(){
        System.out.println(Arrays.toString(course)+"\n"+list+"\n"+map+"\n"+set);
    }
}
