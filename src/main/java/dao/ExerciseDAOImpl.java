package dao;

import entities.ExerciseEntity;

import java.util.List;


public class ExerciseDAOImpl extends DAOImpl<ExerciseEntity> {

    @Override
    public ExerciseEntity findById(int id) {
        return session.get(ExerciseEntity.class, id);
    }

    @Override
    public List<ExerciseEntity> findAll() {
        return (List<ExerciseEntity>) session.createQuery("From entities.ExerciseEntity").list();
    }
}
