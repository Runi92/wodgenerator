package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "exercises", schema = "wodgenerator")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinTable(schema = "wodgenerator",
            name = "exercise_exercisetype",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "exercisetype_id"))
    private ExerciseTypeEntity exerciseTypeEntity;
    @ManyToMany
    @JoinTable(schema = "wodgenerator",
            name = "exercise_trainingpart",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "trainingpart_id"))
    private Set<TrainingPartEntity> trainingPartEntities;
    @ManyToOne
    @JoinTable(schema = "wodgenerator",
            name = "exercise_measurementunit",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "measurementunit_id"))
    private MeasurementUnitEntity measurementUnitEntity;

    @Override
    public String toString() {
        return String.join(" ", "Упражнение", name);
    }
}
