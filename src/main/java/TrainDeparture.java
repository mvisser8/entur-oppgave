import lombok.RequiredArgsConstructor;
import lombok.Value;
import model.TrainRide;
import model.TrainRoute;
import model.TrainStations;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Value
@RequiredArgsConstructor
public class TrainDeparture {
    public List<TrainRide> trainRides;
    TrainRoute trainRoute = new TrainRoute();

    public void departTrain(final TrainRide trainRide) {
        trainRides.add(trainRide);
    }

    public int calculateTimeBetweenStations(final TrainStations startingStation, final TrainStations endStation) {
        final List<TrainStations> route = trainRoute.getLillehammerToOslo();

        final int start = route.indexOf(startingStation);
        final int end = route.indexOf(endStation);
        int travelTime = 0;

        for (int i = start; i < end; i++) {
            travelTime = travelTime + route.get(i).getMinutesToNextStation();
        }

        return travelTime;
    }

    public TrainRide findWhichTrainToTake(final LocalTime time, final TrainStations endStation) {
        final int travelTime = calculateTimeBetweenStations(TrainStations.LILLEHAMMER, endStation);
        final List<TrainRide> possibleTrains = new ArrayList<>();

        for (final TrainRide trainRide : trainRides) {
            if (trainRide.getDepartureTime().isBefore(time.minusMinutes(travelTime)) || trainRide.getDepartureTime().equals(time.minusMinutes(travelTime))) {
                possibleTrains.add(trainRide);
            }
        }

        return possibleTrains.get(possibleTrains.size() - 1);
    }

    public int calculatePassengers(final TrainStations startingStation, final TrainStations endStation, final LocalTime departureTime) {
        final List<TrainStations> route = trainRoute.getLillehammerToOslo();
        LocalTime currentTime = departureTime;
        int passengers = 0;

        for (int i = route.indexOf(startingStation); i < route.indexOf(endStation); i++) {
            final TrainStations currentStation = route.get(i);
            final int inhabitantsForCurrentStation = currentStation.getMunicipality().getInhabitants() / determineMunicipalityModifier(currentStation, route);
            passengers = (int) (passengers + inhabitantsForCurrentStation / 100 * determineTimeModifier(currentTime));
            currentTime = currentTime.plusMinutes(currentStation.getMinutesToNextStation());
        }

        return passengers;
    }

    public int determineMunicipalityModifier(final TrainStations trainStation, final List<TrainStations> route) {
        return (int) route.stream().filter(station -> station.getMunicipality().equals(trainStation.getMunicipality())).count();
    }

    public double determineTimeModifier(final LocalTime currentTime) {
        if (currentTime.isAfter(LocalTime.of(5, 59)) && currentTime.isBefore(LocalTime.of(9, 1))) {
            return 2;
        }
        if (currentTime.isAfter(LocalTime.of(8, 59)) && currentTime.isBefore(LocalTime.of(15, 1))) {
            return 0.5;
        }
        return 1;
    }

}
