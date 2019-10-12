package dao;

import entities.ExerciseTypeEntity;

import java.util.List;

public interface ExerciseTypeDAO {

    ExerciseTypeEntity findById(int id);

    void save(ExerciseTypeEntity exerciseTypeEntity);

    void update(ExerciseTypeEntity exerciseTypeEntity);

    void delete(ExerciseTypeEntity exerciseTypeEntity);

    List<ExerciseTypeEntity> findAll();
}
