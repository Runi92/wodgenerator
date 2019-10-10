package entities;

import javax.persistence.*;

@Table(name = "mettervalues")
public class MetterValuesEntity extends ValuesEntity {
    private static final String METTERS = "метры";

    @ManyToOne
    @JoinTable(name = ValuesEntity.METTERVALUES_TRAINING_PART_JOIN_TABLE_NAME, joinColumns = {@JoinColumn(name = ValuesEntity.TRAINING_PART_JOIN_COLUMN_NAME)})
    private TrainingPartEntity trainigPartEntity;

    @Override
    public String toString() {
        return String.join(" ", super.toString(), METTERS);
    }
}
