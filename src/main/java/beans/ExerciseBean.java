package beans;

import dao.ExerciseDAOImpl;
import entities.ExerciseEntity;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ViewScoped
@ManagedBean(name = "exerciseBean")
@Getter
@Setter
public class ExerciseBean {

    private int exerciseTypeEntityId;
    private String exerciseName;
    private List<ExerciseEntity> exercises;
    private List<ExerciseEntity> selectedExercises;
    private List<String> exerciseTrainingPartIds;

    @ManagedProperty(value = "#{exerciseTypeBean}")
    private ExerciseTypeBean exerciseTypeBean;

    @ManagedProperty(value = "#{trainingPartBean}")
    private TrainingPartBean trainingPartBean;

    public ExerciseBean() {
        exercises = findAllExercise();
    }

    private ExerciseDAOImpl exerciseDAO = new ExerciseDAOImpl();

    public void addExercise() {
        saveExercise(
                ExerciseEntity.
                        builder().
                        name(exerciseName).
                        exerciseTypeEntity(exerciseTypeBean.findExerciseTypeById(exerciseTypeEntityId)).
                        trainingPartEntities(Arrays.stream(exerciseTrainingPartIds.toArray()).map(id -> trainingPartBean.findTrainingPartById(Integer.parseInt((String) id))).collect(Collectors.toList())).
                        build());
        showAllExercises();
    }

    public void showAllExercises() {
        exercises = findAllExercise();
    }

    public void editExercise(CellEditEvent event) {
        //TODO Дописать метод так, чтобы менять можно было любое количество параметров в строке таблицы
        UIComponentBase currentColumn = (UIComponentBase) event.getColumn();
        switch (currentColumn.getId()) {
            case "exerciseNameColumn":
                editExerciseName(event);
                break;
            case "exerciseTypeColumn":
                editExerciseType(event);
                break;
            case "trainingPartColumn":
                editExerciseTrainingPart(event);
                break;
            default:
                FacesMessage msg = new FacesMessage("Ошибка при редактировании таблицы");
                FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    private void editExerciseName(CellEditEvent event) {
        String exerciseOldName = (String) event.getOldValue();
        String updatedExerciseName = (String) event.getNewValue();
        if (updatedExerciseName != null && !updatedExerciseName.equals(exerciseOldName)) {
            ExerciseEntity exerciseEntity = findExerciseById(Integer.parseInt(event.getRowKey()));
            exerciseEntity.setName(updatedExerciseName);
            updateExercise(exerciseEntity);
        }
    }

    private void editExerciseTrainingPart(CellEditEvent event) {
        //TODO Дописать метод изменения части тренирвоки - приходит список идентифифкаторов сущности
        System.out.println();
    }

    private void editExerciseType(CellEditEvent event) {
        //TODO Дописать метод изменения типа упражнения - приходит идентифифкатор сущности
        System.out.println();
    }

    public void deleteSelectedExercises() {
        for (ExerciseEntity exerciseEntity : selectedExercises) {
            deleteExercise(exerciseEntity);
        }
        showAllExercises();
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
