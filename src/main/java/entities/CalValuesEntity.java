package entities;

import javax.persistence.*;

@Table(name = "calvalues")
public class CalValuesEntity extends ValuesEntity {
    private static final String CALORIES = "калории";

    @ManyToOne
    @JoinTable(name = ValuesEntity.CALVALUES_TRAINING_PART_JOIN_TABLE_NAME, joinColumns = {@JoinColumn(name = ValuesEntity.TRAINING_PART_JOIN_COLUMN_NAME)})
    private TrainingPartEntity trainigPartEntity;

    @Override
    public String toString() {
        return String.join(" ", super.toString(), CALORIES);
    }
}
