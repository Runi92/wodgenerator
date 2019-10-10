package dao;

import entities.ExerciseEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ExerciseDAOImpl implements ExerciseDAO {

    @Override
    public ExerciseEntity findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ExerciseEntity.class, id);
    }

    @Override
    public void save(ExerciseEntity exerciseEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(exerciseEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(ExerciseEntity exerciseEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(exerciseEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(ExerciseEntity exerciseEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(exerciseEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public List<ExerciseEntity> findAll() {
        return (List<ExerciseEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From entities.ExerciseEntity").list();
    }
}
