package com.data.repository;

import com.data.model.Movie;
import com.data.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepoImp implements MovieRepo {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Movie> getAllMovie() {
        Session session = sessionFactory.openSession();
        Query<Movie> query = session.createQuery(" from Movie",Movie.class);
        return query.getResultList();
    }
    @Override
    public List<Movie> getMoviesByPage(int pageNo, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> query = session.createQuery("FROM Movie", Movie.class);
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        }
    }

    @Override
    public long countTotalMovie() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Movie", Long.class);
            return query.uniqueResult();
        }
    }

    @Override
    public void save(Movie movie) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(movie); // nếu đã có id thì update, chưa có thì insert
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
