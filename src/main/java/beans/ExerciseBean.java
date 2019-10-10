package beans;

import dao.ExerciseDAO;
import dao.ExerciseDAOImpl;
import entities.ExerciseEntity;

import javax.faces.bean.SessionScoped;
import java.util.List;

@SessionScoped
public class ExerciseBean {

    private ExerciseDAO exerciseDAO = new ExerciseDAOImpl();

    public ExerciseEntity findExerciseById(int id) {
        return exerciseDAO.findById(id);
    }

    public void saveExercise(ExerciseEntity exerciseEntity) {
        exerciseDAO.save(exerciseEntity);
    }

    public void deleteExercise(ExerciseEntity exerciseEntity) {
        exerciseDAO.delete(exerciseEntity);
    }

    public void updateExercise(ExerciseEntity exerciseEntity) {
        exerciseDAO.update(exerciseEntity);
    }

    public List<ExerciseEntity> findAllExercise() {
        return exerciseDAO.findAll();
    }
}
