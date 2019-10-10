package entities;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "repvalues")
public class RepValuesEntity extends ValuesEntity{
    private static final String REPS = "повторения";

    @ManyToOne
    @JoinTable(name = ValuesEntity.REPVALUES_TRAINING_PART_JOIN_TABLE_NAME, joinColumns = {@JoinColumn(name = ValuesEntity.TRAINING_PART_JOIN_COLUMN_NAME)})
    private TrainingPartEntity trainigPartEntity;

    @Override
    public String toString() {
        return String.join(" ", super.toString(), REPS);
    }
}
