package dao;

import entities.ExerciseTypeEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ExerciseTypeDAOImpl implements ExerciseTypeDAO {
    @Override
    public ExerciseTypeEntity findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ExerciseTypeEntity.class, id);
    }

    @Override
    public void save(ExerciseTypeEntity exerciseTypeEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(exerciseTypeEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(ExerciseTypeEntity exerciseTypeEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(exerciseTypeEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(ExerciseTypeEntity exerciseTypeEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(exerciseTypeEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public List<ExerciseTypeEntity> findAll() {
        return (List<ExerciseTypeEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From entities.ExerciseTypeEntity").list();
    }
}
