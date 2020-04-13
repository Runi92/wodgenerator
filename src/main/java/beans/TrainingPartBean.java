package beans;

import dao.TrainingPartDAOImpl;
import entities.TrainingPartEntity;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.CellEditEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ViewScoped
@ManagedBean(name = "trainingPartBean")
@Getter
@Setter
public class TrainingPartBean {

    private String trainingPartName;
    private List<TrainingPartEntity> trainingParts;
    private List<TrainingPartEntity> selectedTrainingParts;

    public TrainingPartBean() {
        trainingParts = findAllTrainingParts();
    }

    private TrainingPartDAOImpl trainingPartDAO = new TrainingPartDAOImpl();

    public void addTrainingPart() {
        saveTrainingPart(TrainingPartEntity.builder().name(trainingPartName).build());
        showAllTrainingParts();
    }

    public void editTrainingPart(CellEditEvent event) {
        String trainingPartOldName = (String) event.getOldValue();
        String updatedTrainingPartName = (String) event.getNewValue();
        if (updatedTrainingPartName != null && !updatedTrainingPartName.equals(trainingPartOldName)) {
            TrainingPartEntity trainingPartEntity = findTrainingPartById(Integer.parseInt(event.getRowKey()));
            trainingPartEntity.setName(updatedTrainingPartName);
            updateTrainingPart(trainingPartEntity);
        }
    }

    public void deleteSelectedTrainingParts() {
        for (TrainingPartEntity trainingPartEntity : selectedTrainingParts) {
            deleteTrainingPart(trainingPartEntity);
        }
        showAllTrainingParts();
    }

    protected TrainingPartEntity findTrainingPartById(int id) {
        return trainingPartDAO.findById(id);
    }

    private void saveTrainingPart(TrainingPartEntity trainingPartEntity) {
        trainingPartDAO.save(trainingPartEntity);
    }

    private void deleteTrainingPart(TrainingPartEntity trainingPartEntity) {
        trainingPartDAO.delete(trainingPartEntity);
    }

    private void updateTrainingPart(TrainingPartEntity trainingPartEntity) {
        trainingPartDAO.update(trainingPartEntity);
    }

    public List<TrainingPartEntity> findAllTrainingParts() {
        return trainingPartDAO.findAll();
    }

    private void showAllTrainingParts() {
        trainingParts = findAllTrainingParts();
    }

}
