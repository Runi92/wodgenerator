package beans;

import dao.DAOImpl;
import dao.ExerciseDAOImpl;
import entities.ExerciseEntity;
import entities.ExerciseTypeEntity;
import entities.MeasurementUnitEntity;
import entities.TrainingPartEntity;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.CellEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ViewScoped
@ManagedBean(name = "exerciseBean")
@Getter
@Setter
public class ExerciseBean {

    private int exerciseMeasurementUnitId;
    private int exerciseTypeEntityId;
    private String exerciseName;
    private List<ExerciseEntity> exercises;
    private List<ExerciseEntity> selectedExercises;
    private List<String> exerciseTrainingPartIds;

    @ManagedProperty(value = "#{exerciseTypeBean}")
    private ExerciseTypeBean exerciseTypeBean;

    @ManagedProperty(value = "#{trainingPartBean}")
    private TrainingPartBean trainingPartBean;

    @ManagedProperty(value = "#{measurementUnitBean}")
    private MeasurementUnitBean measurementUnitBean;

    public ExerciseBean() {
        exercises = findAllExercise();
    }

    private DAOImpl<ExerciseEntity> exerciseDAO = new ExerciseDAOImpl();

    public void addExercise() {
        try {
            saveExercise(
                    ExerciseEntity.
                            builder().
                            name(exerciseName).
                            exerciseTypeEntity(exerciseTypeBean.findExerciseTypeById(exerciseTypeEntityId)).
                            trainingPartEntities(Arrays.stream(exerciseTrainingPartIds.toArray()).map(id -> trainingPartBean.findTrainingPartById(Integer.parseInt((String) id))).collect(Collectors.toSet())).
                            measurementUnitEntity(measurementUnitBean.findMeasurementUnitById(exerciseMeasurementUnitId)).
                            build());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Ошибка при добавлении упражнения");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        showAllExercises();
    }

    public void showAllExercises() {
        exercises = findAllExercise();
    }

    public void editExercise(CellEditEvent event) {
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
            case "exerciseMeasurementUnit":
                editExerciseMeasurementUnit(event);
                break;
            default:
                FacesMessage msg = new FacesMessage("Ошибка при редактировании таблицы");
                FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        showAllExercises();
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
        List<String> oldTrainingPartIds = (List<String>) event.getOldValue();
        List<String> updatedTrainingPardIds = (List<String>) event.getNewValue();
        if (oldTrainingPartIds == null || !oldTrainingPartIds.equals(updatedTrainingPardIds)) {
            ExerciseEntity exerciseEntity = findExerciseById(Integer.parseInt(event.getRowKey()));
            Set<TrainingPartEntity> trainingPartEntities = updatedTrainingPardIds.stream().map(id -> trainingPartBean.findTrainingPartById(Integer.parseInt(id))).collect(Collectors.toSet());
            exerciseEntity.setTrainingPartEntities(trainingPartEntities);
            updateExercise(exerciseEntity);
        }

    }

    private void editExerciseType(CellEditEvent event) {
        int exerciseOldTypeId = (int) event.getOldValue();
        int updatedExerciseTypeId = (int) event.getNewValue();
        if (updatedExerciseTypeId != exerciseOldTypeId) {
            ExerciseEntity exerciseEntity = findExerciseById(Integer.parseInt(event.getRowKey()));
            ExerciseTypeEntity exerciseTypeEntity = exerciseTypeBean.findExerciseTypeById(updatedExerciseTypeId);
            exerciseEntity.setExerciseTypeEntity(exerciseTypeEntity);
            updateExercise(exerciseEntity);
        }
    }

    private void editExerciseMeasurementUnit(CellEditEvent event) {
        int oldMeasurementUnitId = (int) event.getOldValue();
        int updatedMeasurementUnitId = (int) event.getNewValue();
        if (updatedMeasurementUnitId != oldMeasurementUnitId) {
            ExerciseEntity exerciseEntity = findExerciseById(Integer.parseInt(event.getRowKey()));
            MeasurementUnitEntity measurementUnitEntity = measurementUnitBean.findMeasurementUnitById(updatedMeasurementUnitId);
            exerciseEntity.setMeasurementUnitEntity(measurementUnitEntity);
            updateExercise(exerciseEntity);
        }
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
