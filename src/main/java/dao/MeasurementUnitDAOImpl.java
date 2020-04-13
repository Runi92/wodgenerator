package dao;

import entities.MeasurementUnitEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class MeasurementUnitDAOImpl implements DAO<MeasurementUnitEntity> {

    @Override
    public MeasurementUnitEntity findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(MeasurementUnitEntity.class, id);
    }

    @Override
    public void save(MeasurementUnitEntity measurementUnitEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(measurementUnitEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(MeasurementUnitEntity measurementUnitEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(measurementUnitEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(MeasurementUnitEntity measurementUnitEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(measurementUnitEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public List<MeasurementUnitEntity> findAll() {
        return (List<MeasurementUnitEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From entities.MeasurementUnitEntity").list();
    }
}
