package dao;

import entities.TrainingPartEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class TrainingPartDAOImpl implements TrainingPartDAO {
    @Override
    public TrainingPartEntity findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(TrainingPartEntity.class, id);
    }

    @Override
    public void save(TrainingPartEntity trainingPartEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(trainingPartEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(TrainingPartEntity trainingPartEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(trainingPartEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(TrainingPartEntity trainingPartEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(trainingPartEntity);
        transaction.commit();
        session.close();

    }

    @Override
    public List<TrainingPartEntity> findAll() {
        return (List<TrainingPartEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From entities.TrainingPartEntity").list();
    }
}
