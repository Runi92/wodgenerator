package entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exercises", schema = "public")
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
    @JoinTable(
            name = "exercise_exercisetype",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "exercisetype_id"))
    private ExerciseTypeEntity exerciseTypeEntity;
    @ManyToMany
    @JoinTable(
            name = "exercise_trainingpart",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "trainingpart_id"))
    private List<TrainingPartEntity> trainingPartEntities;

    @Override
    public String toString() {
        return String.join(" ", "Упражнение", name);
    }
}
