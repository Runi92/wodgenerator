package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.annotation.PreDestroy;
import java.util.List;

public abstract class DAOImpl<T> {

    protected final Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    public abstract T findById(int id);

    public abstract List findAll();

    public void save(T o) {
        Transaction transaction = session.beginTransaction();
        session.save(o);
        transaction.commit();
    }

    public void update(T o) {
        Transaction transaction = session.beginTransaction();
        session.update(o);
        transaction.commit();
    }

    public void delete(T o) {
        Transaction transaction = session.beginTransaction();
        session.delete(o);
        transaction.commit();

    }

    @PreDestroy
    private void closeSession() {
        session.close();
    }

}
