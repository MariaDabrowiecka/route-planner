package com.dabrowiecka.routeplanner;

import static com.dabrowiecka.routeplanner.InMemoryTrainRoutePlannerServiceTest.DATE_FORMAT;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;

import com.dabrowiecka.routeplanner.model.Connection;
import com.dabrowiecka.routeplanner.model.Station;

public class InMemoryTrainRoutePlannerServiceTestData {

    private InMemoryTrainRoutePlannerServiceTestData() {
    }

    public static Connection prepareSimpleConnection(final Station originStation, final Station finalStation) throws ParseException {
        return new Connection(originStation,
                              finalStation,
                              DATE_FORMAT.parse("2020/01/22 13:15"),
                              DATE_FORMAT.parse("2020/01/22 14:15"));
    }

    public static List<Connection> prepareMultipleConnections(final Station originStation, final Station finalStation)
        throws ParseException {
        Station warsawCentral = new Station("Warsaw Central");
        Station poznanGlowny = new Station("Poznan Glowny");
        Date departureDateFromLodz = DATE_FORMAT.parse("2015/01/22 13:15");
        Date arrivalDateToWarsaw = DATE_FORMAT.parse("2015/01/22 14:15");
        Date arrivalDateToGdanskFromWarsaw = DATE_FORMAT.parse("2015/01/22 18:15");
        Date arrivalDateToGdanskFromPoznan = DATE_FORMAT.parse("2015/01/22 17:15");
        Date arrivalDateToPoznan = DATE_FORMAT.parse("2015/01/22 14:15");

        Connection lodzWawConnection =
            new Connection(originStation, warsawCentral, departureDateFromLodz, arrivalDateToWarsaw);
        Connection wawGdnkConnection =
            new Connection(warsawCentral, finalStation, arrivalDateToWarsaw, arrivalDateToGdanskFromWarsaw);
        Connection lodzPoznanConnection =
            new Connection(originStation, poznanGlowny, departureDateFromLodz, arrivalDateToPoznan);
        Connection poznanGdanskConnection =
            new Connection(poznanGlowny, finalStation, arrivalDateToPoznan, arrivalDateToGdanskFromPoznan);
        return Lists.newArrayList(lodzWawConnection, wawGdnkConnection, lodzPoznanConnection, poznanGdanskConnection);
    }

}
