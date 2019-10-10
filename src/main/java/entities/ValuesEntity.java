package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValuesEntity {
    public static final String CALVALUES_TRAINING_PART_JOIN_TABLE_NAME = "calvalues_trainigpart";
    public static final String METTERVALUES_TRAINING_PART_JOIN_TABLE_NAME = "mettervalues_trainingpart";
    public static final String REPVALUES_TRAINING_PART_JOIN_TABLE_NAME = "repvalues_trainingpart";
    public static final String SECVALUES_TRAINING_PART_JOIN_TABLE_NAME = "secvalues_trainingpart";
    public static final String TRAINING_PART_JOIN_COLUMN_NAME = "trainingpart_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int min;
    private int max;

    @Override
    public String toString() {
        return String.join(" ", "Минимальное значение", String.valueOf(min), "Максимальное значение", String.valueOf(max));
    }
}
