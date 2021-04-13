package com.sda.practical.databases;

import com.sda.practical.utils.LoggerUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AuthorRepository {

    public void addAuthor(AuthorEntity authorEntity) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(authorEntity);
        transaction.commit();
        session.close();
    }

    public void updateAuthor(AuthorEntity authorEntity) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(authorEntity);
        transaction.commit();
        session.close();
    }

    public void deleteAuthor(AuthorEntity authorEntity) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(authorEntity);
        transaction.commit();
        session.close();
    }

//    public void updateAuthor(Integer authorId){
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(authorEntity);
//        transaction.commit();
//        session.close();
//    }

    public static List<AuthorEntity> getAllAuthors() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query query = session.createQuery("FROM AuthorEntity");
        List<AuthorEntity> authorEntityList = query.list();

        session.close();

        return authorEntityList;
    }

//    public static AuthorEntity findByName(String authorFirstName, String authorLastName) {
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Query query = session.createQuery("FROM AuthorEntity");
//        List<AuthorEntity> authorEntityList = query.list();
//        for (AuthorEntity a: authorEntityList) {
//            if(a.getAuthorFirstName().equals(authorFirstName) &&
//                a.getAuthorLastName().equals(authorLastName)){
//                return a;
//            }
//        }
//        session.close();
//        return null;
//    }

    public static List<AuthorEntity> findByName(String authorFirstName, String authorLastName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("FROM AuthorEntity WHERE authorFirstName = :authorFirstName AND authorLastName = :authorLastName");
        query.setParameter("authorFirstName", authorFirstName);
        query.setParameter("authorLastName", authorLastName);
        List<AuthorEntity> authorEntityList = query.list();
        session.close();
        if (authorEntityList.isEmpty() || authorEntityList == null){
            return null;
        }
        return authorEntityList;
    }

    private static AuthorEntity findById(Integer authorId) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        AuthorEntity authorEntity = session.get(AuthorEntity.class, authorId);
        session.close();
        return authorEntity;
    }
}
