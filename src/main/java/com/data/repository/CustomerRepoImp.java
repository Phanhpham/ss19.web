package com.data.repository;

import com.data.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public class CustomerRepoImp implements CustomerRepo {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getAll() {
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery(" from Customer",Customer.class);
        return query.getResultList();
    }

//    @Override
//    public void addCustomer(Customer customer) {
//        Session session = sessionFactory.openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.save(customer);
//            tx.commit();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
}
