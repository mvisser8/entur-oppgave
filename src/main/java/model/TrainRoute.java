package model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class TrainRoute {
    private final List<TrainStations> lillehammerToOslo = List.of(TrainStations.LILLEHAMMER, TrainStations.MOELV, TrainStations.BRUMUNDDAL, TrainStations.STANGE, TrainStations.TANGEN, TrainStations.OSLO_LUFTHAVN, TrainStations.OSLO);
}
