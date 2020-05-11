package dao;

import entities.ExerciseTypeEntity;

import java.util.List;

public class ExerciseTypeDAOImpl extends DAOImpl<ExerciseTypeEntity> {

    @Override
    public ExerciseTypeEntity findById(int id) {
        return session.get(ExerciseTypeEntity.class, id);
    }

    @Override
    public List<ExerciseTypeEntity> findAll() {
        return (List<ExerciseTypeEntity>) session.createQuery("From entities.ExerciseTypeEntity").list(); }
}
