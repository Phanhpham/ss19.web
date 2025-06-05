package com.data.repository;

import com.data.model.Active;
import com.data.model.Customer;
import com.data.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserRepoImpl implements UserRepo{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<User> getAllUser() {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery(" from User",User.class);
        return query.getResultList();
    }

    @Override
    public List<User> getUsersByPage(int pageNo, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        }
    }

    @Override
    public long countTotalUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM User", Long.class);
            return query.uniqueResult();
        }
    }

    @Override
    public void blockUserById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                user.setIsActive(Active.BLOCK);
                session.update(user);
            }
            tx.commit();
        }
    }

    @Override
    public void unblockUserById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                user.setIsActive(Active.ACTIVE);
                session.update(user);
            }
            tx.commit();
        }
    }


}
