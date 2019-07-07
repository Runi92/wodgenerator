package enums;

import lombok.Getter;

@Getter
public enum RestEnum {
    REST("Отдых");

    private String restName;

    RestEnum(String restName) {
        this.restName = restName;
    }
}
