package entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "trainingpart", schema = "wodgenerator")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TrainingPartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
