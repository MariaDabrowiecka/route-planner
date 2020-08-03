package com.dabrowiecka.routeplanner;

import static com.dabrowiecka.routeplanner.InMemoryTrainRoutePlannerServiceTestData.prepareMultipleConnections;
import static com.dabrowiecka.routeplanner.InMemoryTrainRoutePlannerServiceTestData.prepareSimpleConnection;
import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import com.dabrowiecka.routeplanner.model.Route;
import com.dabrowiecka.routeplanner.model.Station;
import com.dabrowiecka.routeplanner.service.InMemoryTrainRoutePlannerService;
import com.dabrowiecka.routeplanner.service.RoutePlannerService;

public class InMemoryTrainRoutePlannerServiceTest {

    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd hh:mm", Locale.getDefault());

    @Test
    public void correctRouteIsReturnedForSimpleConnection() throws ParseException {
        // Given
        Date inputDate = DATE_FORMAT.parse("2015/01/22 10:15");
        Station originStation = new Station("Warsaw Central");
        Station finalStation = new Station("Lodz Widzew");

        RoutePlannerService service = new InMemoryTrainRoutePlannerService();
        service.registerConnection(prepareSimpleConnection(originStation, finalStation));

        // When
        Route shortestRoute = service.getShortestRoute(originStation, finalStation, inputDate);

        // Then
        assertThat(shortestRoute.originStation()).isEqualTo(originStation);
        assertThat(shortestRoute.finalStation()).isEqualTo(finalStation);
        assertThat(shortestRoute.connections().size()).isEqualTo(1);
    }

    @Test
    public void correctRouteIsReturnedForMultipleConnections() throws ParseException {
        // Given
        Date inputDate = DATE_FORMAT.parse("2015/01/20 10:15");
        Station lodzWidzew = new Station("Lodz Widzew");
        Station gdanskGlowny = new Station("Gdansk Glowny");

        RoutePlannerService service = new InMemoryTrainRoutePlannerService();
        service.registerConnections(prepareMultipleConnections(lodzWidzew, gdanskGlowny));

        // When
        Route shortestRoute = service.getShortestRoute(lodzWidzew, gdanskGlowny, inputDate);

        // Then
        assertThat(shortestRoute.originStation()).isEqualTo(lodzWidzew);
        assertThat(shortestRoute.finalStation()).isEqualTo(gdanskGlowny);
        assertThat(shortestRoute.connections().size()).isEqualTo(2);
        assertThat(shortestRoute.totalTimeInMinutes()).isEqualTo(240);
    }

}
