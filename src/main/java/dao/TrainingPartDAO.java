package dao;

import entities.TrainingPartEntity;

import java.util.List;

public interface TrainingPartDAO {

    TrainingPartEntity findById(int id);

    void save(TrainingPartEntity trainingPartEntity);

    void update(TrainingPartEntity trainingPartEntity);

    void delete(TrainingPartEntity trainingPartEntity);

    List<TrainingPartEntity> findAll();
}
