package JDBCTemplate.Pojo;

public class Book {

    private Integer bookid;
    private String bookname;
    private String bookstatus;

    public Book() {
    }

    public Book(Integer bookid, String bookname, String bookstatus) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.bookstatus = bookstatus;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookstatus() {
        return bookstatus;
    }

    public void setBookstatus(String bookstatus) {
        this.bookstatus = bookstatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                ", bookstatus='" + bookstatus + '\'' +
                '}';
    }
}
