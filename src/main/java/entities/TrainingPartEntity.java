package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trainingpart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainingPartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany
    @JoinTable(name = "calvalues_trainingpart", joinColumns = {@JoinColumn(name = "calvalues_id")})
    private List<CalValuesEntity> calValuesEntities;
    @OneToMany
    @JoinTable(name = "mettervalues_trainingpart", joinColumns = {@JoinColumn(name = "mettervalues_id")})
    private List<MetterValuesEntity>  metterValuesEntities;
    @OneToMany
    @JoinTable(name = "repvalues_trainingpart", joinColumns = {@JoinColumn(name = "repvalues_id")})
    private List<RepValuesEntity> repValuesEntities;
    @OneToMany
    @JoinTable(name = "secvalues_trainingpart", joinColumns = {@JoinColumn(name = "secvalues_id")})
    private List<SecValuesEntity> secValuesEntity;

    @Override
    public String toString() {
        return String.join(" ", "Часть тренировки", name);
    }
}
