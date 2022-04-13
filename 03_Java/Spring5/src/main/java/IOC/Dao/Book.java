package IOC.Dao;

/**
 * 使用set方法进行依赖注入
 */
public class Book {
    //属性创建
    private String bname;
    private String bauthor;
    //set方法
    public void setBname(String bname){
        this.bname = bname;
    }

    public void setBauthor(String bauthor){
        this.bauthor = bauthor;
    }

    public void testDemo(){
        System.out.printf(bname+"="+bauthor);
    }
}
