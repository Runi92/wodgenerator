package beans;

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

    private static final String EXERCISE_TYPES_MISSING = "Типы упражнений отсутствуют";
    private static final String CHOSE_EXERCISE_TYPE = "Выберете тип упражения";
    private static final String ADD_EXERCISE_TYPES = "Добавьте типы упражений";

    private String exerciseTypeName;
    private List<ExerciseTypeEntity> exerciseTypes;
    private List<ExerciseTypeEntity> selectedExerciseTypes;
    private String exerciseTypeSelectItem;
    private String exerciseTypeSelectLabel;

    public ExerciseTypeBean() {
        exerciseTypes = findAllExerciseTypes();
        exerciseTypeSelectItem = exerciseTypes.isEmpty() ? EXERCISE_TYPES_MISSING : CHOSE_EXERCISE_TYPE;
        exerciseTypeSelectLabel = exerciseTypes.isEmpty() ? ADD_EXERCISE_TYPES : CHOSE_EXERCISE_TYPE;
    }

    private ExerciseTypeDAOImpl exerciseTypeDAO = new ExerciseTypeDAOImpl();

    public void addExerciseType() {
        saveExerciseType(ExerciseTypeEntity.builder().name(exerciseTypeName).build());
        showAllExerciseTypes();
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
        showAllExerciseTypes();
    }

    public ExerciseTypeEntity findExerciseTypeById(int id) {
        return exerciseTypeDAO.findById(id);
    }

    public void saveExerciseType(ExerciseTypeEntity exerciseTypeEntity) {
        exerciseTypeDAO.save(exerciseTypeEntity);
    }

    public void deleteExerciseTypes(ExerciseTypeEntity exerciseTypeEntity) {
        exerciseTypeDAO.delete(exerciseTypeEntity);
    }

    public void updateExerciseType(ExerciseTypeEntity exerciseTypeEntity) {
        exerciseTypeDAO.update(exerciseTypeEntity);
    }

    public List<ExerciseTypeEntity> findAllExerciseTypes() {
        return exerciseTypeDAO.findAll();
    }
}
