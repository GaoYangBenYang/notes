package IOC.FactoryBean;

import IOC.Collect.Book;
import org.springframework.beans.factory.FactoryBean;

public class MyBean implements FactoryBean<Book> {
    @Override
    public Book getObject() throws Exception {
        Book book = new Book();
        book.setBname("哈哈");
        return book;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
