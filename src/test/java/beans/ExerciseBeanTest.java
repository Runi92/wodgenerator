package beans;

import entities.ExerciseEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ExerciseBeanTest {

    private static final String SNATCH = "snatch";
    private static final String SNATCH_WEIGHTLIFTING = "snatch_wf";
    private static final String SNATCH_BYID_TEST = "snatch_byid_test";
    private static final String SNATCH_DELETE_TEST = "snatch_delete_test";
    private static final String SNATCH_DELETE_BY_NAME = "snatch_delete_by_name_test";

    private ExerciseEntity exerciseEntity;
    private ExerciseEntity exerciseEntityById;
    private ExerciseEntity exerciseEntityToDelete;
    private ExerciseBean exerciseBean;

    @Before
    public void initTestData() {
        exerciseBean = new ExerciseBean();
    }

    @Test
    public void findExerciseById() {
        exerciseEntityById = new ExerciseEntity();
        exerciseEntityById.setName(SNATCH_BYID_TEST);
        exerciseBean.saveExercise(exerciseEntityById);
        exerciseEntityById = exerciseBean.findExerciseById(exerciseEntityById.getId());
        Assert.assertTrue(exerciseEntityById.getName().equals(SNATCH_BYID_TEST));
        exerciseBean.deleteExercise(exerciseEntityById);
    }

    @Test
    public void saveExercise() {
        exerciseEntity = new ExerciseEntity();
        exerciseEntity.setName(SNATCH);
        exerciseBean.saveExercise(exerciseEntity);
        Assert.assertTrue(exerciseBean.findAllExercise().stream().map(ExerciseEntity::getName).collect(Collectors.toList()).contains(SNATCH));
        exerciseBean.deleteExercise(exerciseEntity);
    }

    @Test
    public void deleteExercise() {
        exerciseEntityToDelete = new ExerciseEntity();
        exerciseEntityToDelete.setName(SNATCH_DELETE_TEST);
        exerciseBean.saveExercise(exerciseEntityToDelete);
        exerciseBean.deleteExercise(exerciseEntityToDelete);
        Assert.assertFalse(exerciseBean.findAllExercise().contains(exerciseEntityToDelete));
    }

    @Test
    public void updateExercise() {
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setName(SNATCH);
        exerciseBean.saveExercise(exerciseEntity);
        exerciseEntity.setName(SNATCH_WEIGHTLIFTING);
        exerciseBean.updateExercise(exerciseEntity);
        Assert.assertTrue(exerciseBean.findExerciseById(exerciseEntity.getId()).getName().equals(SNATCH_WEIGHTLIFTING));
        exerciseBean.deleteExercise(exerciseEntity);
    }

    @Test
    public void findAllExercise() {
        List<ExerciseEntity> exerciseEntities = exerciseBean.findAllExercise();
        System.out.println(exerciseEntities);
    }

    @Test
    public void addExercise() {
        List<Integer> testIntegerList = new ArrayList<>();
        testIntegerList.add(1);
        for (int i = 0; i < testIntegerList.size(); i++) {
            System.out.println(testIntegerList.get(i).getClass());
        }
    }
}