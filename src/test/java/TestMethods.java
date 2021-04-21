import model.TrainRide;
import model.TrainStations;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestMethods {


    @Test
    public void testDeparture(){
        final TrainDeparture departures = prepareDepartures();

        assertThat(departures.trainRides.size(), is(24));
    }

    @Test
    public void testTimecalc(){
        final TrainDeparture departures = prepareDepartures();

        final int traveltime = departures.calculateTimeBetweenStations(TrainStations.LILLEHAMMER, TrainStations.BRUMUNDDAL);
        final int traveltime2 = departures.calculateTimeBetweenStations(TrainStations.LILLEHAMMER, TrainStations.OSLO);
        final int traveltime3 = departures.calculateTimeBetweenStations(TrainStations.OSLO_LUFTHAVN, TrainStations.OSLO);
        final int traveltime4 = departures.calculateTimeBetweenStations(TrainStations.MOELV, TrainStations.TANGEN);

        assertThat(traveltime, is(60));
        assertThat(traveltime2, is(150));
        assertThat(traveltime3, is(30));
        assertThat(traveltime4, is(70));
    }

    @Test
    public void decideTrain(){
        final TrainDeparture departures = prepareDepartures();

        final TrainRide train = departures.findWhichTrainToTake(LocalTime.of(9, 0), TrainStations.MOELV);
        final TrainRide train2 = departures.findWhichTrainToTake(LocalTime.of(15, 0), TrainStations.TANGEN);
        final TrainRide train3 = departures.findWhichTrainToTake(LocalTime.of(5, 0), TrainStations.BRUMUNDDAL);
        final TrainRide train4 = departures.findWhichTrainToTake(LocalTime.of(22, 0), TrainStations.OSLO);
        final TrainRide train5 = departures.findWhichTrainToTake(LocalTime.of(21, 59), TrainStations.OSLO);
        final TrainRide train6 = departures.findWhichTrainToTake(LocalTime.of(21, 30), TrainStations.OSLO);
        final TrainRide train7 = departures.findWhichTrainToTake(LocalTime.of(0, 30), TrainStations.OSLO);

        assertThat(train.getDepartureTime(), is(LocalTime.of(8,0)));
        assertThat(train2.getDepartureTime(), is(LocalTime.of(13,0)));
        assertThat(train3.getDepartureTime(), is(LocalTime.of(4,0)));
        assertThat(train4.getDepartureTime(), is(LocalTime.of(19,0)));
        assertThat(train5.getDepartureTime(), is(LocalTime.of(19,0)));
        assertThat(train6.getDepartureTime(), is(LocalTime.of(19,0)));
        assertThat(train7.getDepartureTime(), is(LocalTime.of(22,0)));

    }

    @Test
    public void countAmountOfPassengers(){
        final TrainDeparture departures = prepareDepartures();

        final int passengers = departures.calculatePassengers(TrainStations.LILLEHAMMER, TrainStations.OSLO, LocalTime.of(5, 0));

        assertThat(passengers, is(2025));
    }


    private TrainDeparture prepareDepartures() {
        final TrainDeparture trainDeparture = new TrainDeparture(new ArrayList<>());
        List<TrainRide> trainRidesToDepart = new ArrayList<>();

        for(int i = 0; i < 24; i++){
        trainRidesToDepart.add(new TrainRide(LocalTime.of(i, 0), "Lillehammer"));
        }

        trainRidesToDepart.forEach(trainDeparture::departTrain);

        return trainDeparture;
    }
}
