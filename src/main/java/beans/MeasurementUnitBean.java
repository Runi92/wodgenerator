package beans;

import dao.DAOImpl;
import dao.MeasurementUnitDAOImpl;
import entities.MeasurementUnitEntity;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.CellEditEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ViewScoped
@ManagedBean(name = "measurementUnitBean")
@Getter
@Setter
public class MeasurementUnitBean {

    private String measurementUnitName;
    private List<MeasurementUnitEntity> measurementUnits;
    private List<MeasurementUnitEntity> selectedMeasurementUnits;
    private DAOImpl<MeasurementUnitEntity> measurementUnitDAO = new MeasurementUnitDAOImpl();

    public MeasurementUnitBean() {
        measurementUnits = findAllMeasurementUnits();
    }

    public void addMeasurementUnit() {
        saveMeasurementUnit(MeasurementUnitEntity.builder().name(measurementUnitName).build());
        showAllMeasurementUnits();
    }

    public void editMeasurementUnit(CellEditEvent editEvent) {
        String measurementUnitOldName = (String) editEvent.getOldValue();
        String updatedMeasurementUnitName = (String) editEvent.getNewValue();
        if (updatedMeasurementUnitName != null && !updatedMeasurementUnitName.equals(measurementUnitOldName)) {
            MeasurementUnitEntity measurementUnitEntity = findMeasurementUnitById(Integer.parseInt(editEvent.getRowKey()));
            measurementUnitEntity.setName(updatedMeasurementUnitName);
            updateMeasurementUnit(measurementUnitEntity);
        }
    }

    public void deleteSelectedMeasurementUnits() {
        for (MeasurementUnitEntity measurementUnitEntity : selectedMeasurementUnits) {
            deleteMeasurementUnit(measurementUnitEntity);
        }
        showAllMeasurementUnits();
    }

    public void showAllMeasurementUnits() {
        measurementUnits = findAllMeasurementUnits();
    }

    protected MeasurementUnitEntity findMeasurementUnitById(int id) {
        return measurementUnitDAO.findById(id);
    }

    public List<MeasurementUnitEntity> findAllMeasurementUnits() {
        return measurementUnitDAO.findAll();
    }

    private void saveMeasurementUnit(MeasurementUnitEntity measurementUnitEntity) {
        measurementUnitDAO.save(measurementUnitEntity);
    }

    private void deleteMeasurementUnit(MeasurementUnitEntity measurementUnitEntity) {
        measurementUnitDAO.delete(measurementUnitEntity);
    }

    private void updateMeasurementUnit(MeasurementUnitEntity measurementUnitEntity) {
        measurementUnitDAO.update(measurementUnitEntity);
    }
}
