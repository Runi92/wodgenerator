package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "exercises")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinTable(name = "exercise_exercisetype", joinColumns = {@JoinColumn(name = "exercisetype_id")})
    private ExerciseTypeEntity exerciseTypeEntity;

    @Override
    public String toString() {
        return String.join(" ", "Упражнение", name);
    }
}
