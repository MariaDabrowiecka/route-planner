package com.dabrowiecka.routeplanner.model;

import java.util.List;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Route {

    @Value.Parameter
    public abstract Station originStation();

    @Value.Parameter
    public abstract Station finalStation();

    @Value.Parameter
    public abstract Long totalTimeInMinutes();

    @Value.Parameter
    public abstract List<Connection> connections();

    @Override
    public String toString() {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append(String.format("Route from: %s to: %s", originStation(), finalStation()));
        resultBuilder.append(String.format("Number of changes: %s", connections().size() - 1));
        resultBuilder.append(String.format("Total time in minutes: %s", totalTimeInMinutes()));
        for (Connection connection : connections()) {
            resultBuilder.append(String.format("%s", connection));
        }
        return resultBuilder.toString();
    }

}
