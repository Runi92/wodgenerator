package entities;

import enums.TrainingTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
@Builder
public class MetconEntity {
    private String name;
    private TrainingTypeEnum metconTrainingType;
    private String duration;
    private Map<String, String> metconMap;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (name != null) {
            stringBuilder
                    .append(name)
                    .append("\n");
        }
        stringBuilder
                .append(metconTrainingType.getTrainingTypeName())
                .append(" ")
                .append(duration)
                .append("\n");
        for (Map.Entry<String, String> entry : metconMap.entrySet()) {
            stringBuilder
                    .append(entry.getKey())
                    .append(" ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
