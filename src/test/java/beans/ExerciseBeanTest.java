package beans;

import entities.ExerciseEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ExerciseBeanTest {

    private static final String SNATCH = "snatch";
    private static final String SNATCH_WEIGHTLIFTING = "snatch_wf";
    private static final String SNATCH_BYID_TEST = "snatch_byid_test";
    private static final String SNATCH_DELETE_TEST = "snatch_delete_test";

    private ExerciseEntity exerciseEntity;
    private ExerciseEntity exerciseEntityById;
    private ExerciseBean exerciseBean;

    @Before
    public void initTestBean() {
        exerciseBean = new ExerciseBean();
    }

    @Before
    public void initTestData() {
        exerciseEntity = new ExerciseEntity();
        exerciseEntity.setName(SNATCH);

    }

    @Before
    public void saveExerciseByIdTestData() {
        exerciseEntityById = new ExerciseEntity();
        exerciseEntityById.setName(SNATCH_BYID_TEST);
        exerciseBean.saveExercise(exerciseEntityById);
    }

    @Test
    public void findExerciseById() {
        exerciseEntityById = exerciseBean.findExerciseById(exerciseEntityById.getId());
        Assert.assertTrue(exerciseEntityById.getName().equals(SNATCH_BYID_TEST));
    }

    @Test
    public void saveExercise() {
        exerciseBean.saveExercise(exerciseEntity);
        Assert.assertTrue(exerciseBean.findAllExercise().contains(SNATCH));
    }

    @Test
    public void deleteExercise() {
        exerciseBean.deleteExercise(exerciseEntityById);
        Assert.assertFalse(exerciseBean.findAllExercise().contains(exerciseEntityById));
    }

    @Test
    public void updateExercise() {
        exerciseEntity.setName(SNATCH_WEIGHTLIFTING);
        exerciseBean.updateExercise(exerciseEntity);
        Assert.assertTrue(exerciseBean.findExerciseById(exerciseEntity.getId()).getName().equals(SNATCH_WEIGHTLIFTING));
    }

    @Test
    public void findAllExercise() {
        List<ExerciseEntity> exerciseEntities = exerciseBean.findAllExercise();
        System.out.println(exerciseEntities);
    }
}