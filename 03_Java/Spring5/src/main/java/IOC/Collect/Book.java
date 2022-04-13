package IOC.Collect;

import java.util.List;

public class Book {
    private List<String> list;
    private String bname;

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void test(){
        System.out.printf(list.toString());
    }
}
