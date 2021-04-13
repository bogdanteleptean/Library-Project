package com.sda.practical.databases;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class BookRepository {

    public static List<BookEntity> getAllBooks() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query query = session.createQuery("FROM BookEntity b order by b.bookTitle");
        List<BookEntity> bookEntityList = query.list();

        session.close();

        return bookEntityList;
    }

    public void addBook(BookEntity bookEntity){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(bookEntity);
        transaction.commit();
        session.close();
    }


    public BookEntity findById(Integer bookId) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        BookEntity bookEntity = session.get(BookEntity.class, bookId);
        session.close();
        return bookEntity;
    }

    public static List<BookEntity> findBookByTitle(String bookTitle){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("FROM BookEntity WHERE bookTitle = :bookTitle");
        query.setParameter("bookTitle", bookTitle);
        List<BookEntity> bookEntities = query.list();
        session.close();
        return bookEntities;
    }

    public static List<BookEntity> findAllBooksWithAuthorId(Integer authorId){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("FROM BookEntity WHERE authorId = :authorId");
        query.setParameter("authorId", authorId);
        List<BookEntity> bookList = query.list();
        session.close();
        return bookList;
    }

    public static List<BookEntity> findAllBooksByAuthorName(String authorFirstName, String authorLastName){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("FROM BookEntity b Where b.authorEntity.authorFirstName = :authorFirstName and b.authorEntity.authorLastName = :authorLastName");
        query.setParameter("authorFirstName", authorFirstName);
        query.setParameter("authorLastName", authorLastName);
        List<BookEntity> bookList = query.list();
        session.close();
        return bookList;
    }


    public void updateBook(BookEntity bookEntity) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(bookEntity);
        transaction.commit();
        session.close();
    }

    public void deleteBook(BookEntity bookEntity) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(bookEntity);
        transaction.commit();
        session.close();
    }
}
