package dao;

import entities.MeasurementUnitEntity;

import java.util.List;

public class MeasurementUnitDAOImpl extends DAOImpl<MeasurementUnitEntity> {

    @Override
    public MeasurementUnitEntity findById(int id) {
        return session.get(MeasurementUnitEntity.class, id);
    }

    @Override
    public List<MeasurementUnitEntity> findAll() {
        return (List<MeasurementUnitEntity>) session.createQuery("From entities.MeasurementUnitEntity").list();
    }
}
