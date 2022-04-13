package AutoWrite;

public class Emp {

    private Dept dept;

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return
                "dept=" + dept ;
    }

    public void test(){
        System.out.println(dept);
    }
}
