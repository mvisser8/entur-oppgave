package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public enum TrainStations {
    LILLEHAMMER(Municipalities.LILLEHAMMER, 30),
    MOELV(Municipalities.RINGSAKER, 30),
    BRUMUNDDAL(Municipalities.RINGSAKER, 20),
    STANGE(Municipalities.STANGE, 20),
    TANGEN(Municipalities.STANGE, 20),
    OSLO_LUFTHAVN(Municipalities.ULLENSAKER, 30),
    OSLO(Municipalities.OSLO, 0);

    private final Municipalities municipality;
    private final int minutesToNextStation;
}
