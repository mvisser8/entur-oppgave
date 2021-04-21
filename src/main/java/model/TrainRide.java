package model;

import lombok.Getter;
import lombok.Value;

import java.time.LocalTime;

@Value
@Getter
public class TrainRide {
    private final LocalTime departureTime;
    private final String startingStation;
}
