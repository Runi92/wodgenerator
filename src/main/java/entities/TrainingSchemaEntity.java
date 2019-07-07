package entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TrainingSchemaEntity {
    private String schemaName;
    private int weeksCount;
    private List<String> baseSchemas;
}
