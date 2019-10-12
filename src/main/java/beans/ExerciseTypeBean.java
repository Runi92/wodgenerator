package beans;

import dao.ExerciseTypeDAO;
import dao.ExerciseTypeDAOImpl;
import entities.ExerciseTypeEntity;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.CellEditEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ViewScoped
@ManagedBean(name = "exerciseTypeBean")
@Getter
@Setter
public class ExerciseTypeBean {

    private String exerciseTypeName;
    private List<ExerciseTypeEntity> exerciseTypes;
    private List<ExerciseTypeEntity> selectedExerciseTypes;

    public ExerciseTypeBean() {
        exerciseTypes = findAllExerciseTypes();
    }

    private ExerciseTypeDAO exerciseTypeDAO = new ExerciseTypeDAOImpl();

    public void addExerciseType() {
        saveExerciseType(ExerciseTypeEntity.builder().name(exerciseTypeName).build());
    }

    public void showAllExerciseTypes() {
        exerciseTypes = findAllExerciseTypes();
    }

    public void editExerciseType(CellEditEvent event) {
        String exerciseTypeOldName = (String) event.getOldValue();
        String updatedExerciseTypeName = (String) event.getNewValue();
        if (updatedExerciseTypeName != null && !updatedExerciseTypeName.equals(exerciseTypeOldName)) {
            ExerciseTypeEntity exerciseTypeEntity = findExerciseTypeById(Integer.parseInt(event.getRowKey()));
            exerciseTypeEntity.setName(updatedExerciseTypeName);
            updateExerciseType(exerciseTypeEntity);
        }
    }

    public void deleteSelectedExerciseTypes() {
        for (ExerciseTypeEntity exerciseTypeEntity : selectedExerciseTypes) {
            deleteExerciseTypes(exerciseTypeEntity);
        }
    }

    private ExerciseTypeEntity findExerciseTypeById(int id) {
        return exerciseTypeDAO.findById(id);
    }

    private void saveExerciseType(ExerciseTypeEntity exerciseTypeEntity) {
        exerciseTypeDAO.save(exerciseTypeEntity);
    }

    private void deleteExerciseTypes(ExerciseTypeEntity exerciseTypeEntity) {
        exerciseTypeDAO.delete(exerciseTypeEntity);
    }

    private void updateExerciseType(ExerciseTypeEntity exerciseTypeEntity) {
        exerciseTypeDAO.update(exerciseTypeEntity);
    }

    private List<ExerciseTypeEntity> findAllExerciseTypes() {
        return exerciseTypeDAO.findAll();
    }
}
