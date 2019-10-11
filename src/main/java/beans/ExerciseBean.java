package beans;

import dao.ExerciseDAO;
import dao.ExerciseDAOImpl;
import entities.ExerciseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@SessionScoped
@ManagedBean(name = "exercise")
@Getter
@Setter
public class ExerciseBean {

    private String exerciseName;

    private ExerciseDAO exerciseDAO = new ExerciseDAOImpl();

    public void addExercise() {
        saveExercise(ExerciseEntity.builder().name(exerciseName).build());
    }

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
