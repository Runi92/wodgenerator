package beans;

import dao.ExerciseDAO;
import dao.ExerciseDAOImpl;
import entities.ExerciseEntity;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.CellEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ViewScoped
@ManagedBean(name = "exerciseBean")
@Getter
@Setter
public class ExerciseBean {

    private String exerciseName;
    private List<ExerciseEntity> exercises;
    private List<ExerciseEntity> selectedExercises;

    public ExerciseBean() {
        exercises = findAllExercise();
    }

    private ExerciseDAO exerciseDAO = new ExerciseDAOImpl();

    public void addExercise() {
        saveExercise(ExerciseEntity.builder().name(exerciseName).build());
    }

    public void showAllExercises() {
        exercises = findAllExercise();
    }

    public void editExercise(CellEditEvent event) {
        String exerciseOldName = (String) event.getOldValue();
        String updatedExerciseName = (String) event.getNewValue();
        if (updatedExerciseName != null && !updatedExerciseName.equals(exerciseOldName)) {
            ExerciseEntity exerciseEntity = findExerciseById(Integer.parseInt(event.getRowKey()));
            exerciseEntity.setName(updatedExerciseName);
            updateExercise(exerciseEntity);
        }
    }

    public void deleteSelectedExercises() {
        for (ExerciseEntity exerciseEntity : selectedExercises) {
            deleteExercise(exerciseEntity);
        }
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
