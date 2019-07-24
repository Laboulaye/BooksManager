package com.study.dao;

import com.study.dao.BookDao;
import com.study.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private static final Logger logger = LoggerFactory.getLogger(BookDao.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.openSession();
        session.persist(book);
        logger.info("Book seccesfully saved. Book details " + book);
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.openSession();
        session.update(book);
        logger.info("Book succesfully updated. Book details " + book);

    }

    @Override
    public void removeBook(int id) {
        Session session = this.sessionFactory.openSession();
        Book book = session.load(Book.class, id);
        if(book != null) {
            session.delete(book);
        }
        logger.info("Book succesfully delete. Book details " + book);
    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.openSession();
        Book book = session.load(Book.class, id);
        logger.info("Book succesfully loaded. Book details " + book);
        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        Session session = this.sessionFactory.openSession();
        List<Book> bookList = session.createQuery("FROM Book").list();

        for(Book book : bookList){
            logger.info("Book list: " + book);
        }
        return bookList;
    }
}
