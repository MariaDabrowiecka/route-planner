package com.dabrowiecka.routeplanner.service;

import java.util.Date;
import java.util.List;

import com.dabrowiecka.routeplanner.model.Connection;
import com.dabrowiecka.routeplanner.model.Route;
import com.dabrowiecka.routeplanner.model.Station;

public interface RoutePlannerService {

    void registerConnection(Connection connection);

    void registerConnections(List<Connection> connections);

    Route getShortestRoute(Station originStation, Station finalStation, Date date);
}
