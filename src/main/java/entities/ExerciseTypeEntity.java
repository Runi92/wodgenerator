package entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exercisetype")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExerciseTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    /*@OneToMany
    @JoinTable(
            name = "exercise_exercisetype",
            joinColumns = @JoinColumn(name = "exercisetype_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    private List<ExerciseEntity> exerciseEntities;*/

    @Override
    public String toString() {
        return String.join(" ", "Тип упражнения", name);
    }

}
