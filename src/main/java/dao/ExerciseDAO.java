package dao;

import entities.ExerciseEntity;

import java.util.List;

public interface ExerciseDAO {

    ExerciseEntity findById(int id);

    void save(ExerciseEntity exerciseEntity);

    void update(ExerciseEntity exerciseEntity);

    void delete(ExerciseEntity exerciseEntity);

    List<ExerciseEntity> findAll();
}
