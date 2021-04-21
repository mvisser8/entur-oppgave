package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Municipalities {
    LILLEHAMMER(30000),
    RINGSAKER(35000),
    STANGE(20000),
    ULLENSAKER(40000),
    OSLO(700000);

    private final int inhabitants;
}
