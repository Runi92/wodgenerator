package entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "measurementunits")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MeasurementUnitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Override
    public String toString() {
        return String.join(" ", "Единица измерения", name);
    }
}
