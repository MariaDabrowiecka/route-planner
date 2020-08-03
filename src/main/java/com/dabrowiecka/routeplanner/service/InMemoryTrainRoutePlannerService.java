package com.dabrowiecka.routeplanner.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.util.Lists;

import com.dabrowiecka.routeplanner.model.Connection;
import com.dabrowiecka.routeplanner.model.Route;
import com.dabrowiecka.routeplanner.model.Station;
import com.dabrowiecka.routeplanner.utils.ShortestRouteCalculator;

public class InMemoryTrainRoutePlannerService implements RoutePlannerService {

    private final List<Connection> connections = Lists.newArrayList();

    @Override
    public void registerConnection(final Connection connection) {
        connections.add(connection);
    }

    @Override
    public void registerConnections(List<Connection> connections) {
        this.connections.addAll(connections);
    }

    @Override
    public Route getShortestRoute(final Station originStation, final Station finalStation, final Date date) {
        List<Connection> allConnectionsAfterDate = getAllConnectionsAfterDate(date);
        ShortestRouteCalculator shortestRouteCalculator = new ShortestRouteCalculator(allConnectionsAfterDate);
        return shortestRouteCalculator.calculate(originStation, finalStation);
    }


    public List<Connection> getAllConnectionsAfterDate(final Date date) {
        return connections.parallelStream()
                          .filter(connection -> connection.departureDate().after(date))
                          .collect(Collectors.toList());
    }
}
