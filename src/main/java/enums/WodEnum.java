package enums;

import lombok.Getter;

@Getter
public enum WodEnum {
    EASY_MARY("EASY MARY"),
    GWEN("GWEN"),
    NASTY_GIRLS("NASTY GIRLS"),
    MARY("MARY"),
    LYNNE("LYNNE"),
    AMANDA("AMANDA"),
    FRELEN("FRELEN"),
    MAGGIE("MAGGIE"),
    KARABEL("KARABEL"),
    MARGURTA("MARGURTA"),
    NICOLE("NICOLE");

    /*CHRISTINE("CHRISTINE"),
    KELLY("KELLY"),
    EVA("EVA"),
    NANCY("NANCY"),
    ANNIE("ANNIE"),
    CINDY("CINDY"),
    CANDY("CANDY"),
    BARBARA("BARBARA"),
    ANGIE("ANGIE"),
    FRAN("FRAN"),
    GRACE("GRACE"),
    HELEN("HELEN"),
    ISABEL("ISABEL"),
    JACKIE("JACKIE"),
    KAREN("KAREN"),
    LINDA("LINDA"),
    CHELSEA("CHELSEA"),
    DIANE("DIANE"),
    ELIZABETH("ELIZABETH");*/

    private String wodName;

    WodEnum(String wodName) {
        this.wodName = wodName;
    }





}
