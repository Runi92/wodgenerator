package entities;

import lombok.*;

import javax.persistence.*;

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

    @Override
    public String toString() {
        return String.join(" ", "Упражнение", name);
    }
}
