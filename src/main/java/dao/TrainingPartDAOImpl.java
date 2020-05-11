package dao;

import entities.TrainingPartEntity;

import java.util.List;

public class TrainingPartDAOImpl extends DAOImpl<TrainingPartEntity> {

    @Override
    public TrainingPartEntity findById(int id) {

        return session.get(TrainingPartEntity.class, id);
    }
    @Override
    public List<TrainingPartEntity> findAll() {
        return (List<TrainingPartEntity>) session.createQuery("From entities.TrainingPartEntity").list();
    }
}
