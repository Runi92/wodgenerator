package dao;

import entities.ExerciseEntity;
import entities.ExerciseTypeEntity;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExerciseDAOImplTest {

    private ExerciseDAOImpl exerciseDAO;
    private ExerciseEntity exerciseEntity;

    private void prepareDelete() {
        exerciseEntity = ExerciseEntity.builder()
                .id(5)
                .name("12345")
                .exerciseTypeEntity(
                        ExerciseTypeEntity.builder()
                                .id(2)
                                .name("Кардио")
                                .build()).build();
        exerciseDAO = new ExerciseDAOImpl();
    }

    @Test
    public void delete() {
        prepareDelete();
        exerciseDAO.delete(exerciseEntity);
    }

}