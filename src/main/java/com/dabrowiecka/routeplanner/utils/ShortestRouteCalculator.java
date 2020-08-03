package com.dabrowiecka.routeplanner.utils;

import static com.dabrowiecka.routeplanner.model.ImmutableRoute.of;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.Multigraph;

import com.dabrowiecka.routeplanner.model.Connection;
import com.dabrowiecka.routeplanner.model.Route;
import com.dabrowiecka.routeplanner.model.Station;

public class ShortestRouteCalculator {

    private final List<Connection> connections;

    public ShortestRouteCalculator(final List<Connection> connections) {
        this.connections = connections;
    }

    public Route calculate(final Station originStation, final Station finalStation) {
        Graph<Station, Connection> connectionsGraph = createGraphFromConnections();
        GraphPath<Station, Connection> path = calculateShortestRoute(originStation, finalStation, connectionsGraph);
        return of(path.getStartVertex(), path.getEndVertex(), (long) path.getWeight(), path.getEdgeList());
    }

    private GraphPath<Station, Connection> calculateShortestRoute(final Station originStation,
                                                                  final Station finalStation,
                                                                  final Graph<Station, Connection> connectionsGraph) {
        DijkstraShortestPath<Station, Connection> dijkstraShortestPath = new DijkstraShortestPath<>(connectionsGraph);
        ShortestPathAlgorithm.SingleSourcePaths<Station, Connection>
            pathsFromOriginStation = dijkstraShortestPath.getPaths(originStation);
        return pathsFromOriginStation.getPath(finalStation);
    }

    private Graph<Station, Connection> createGraphFromConnections() {
        Graph<Station, Connection> connectionsGraph = new Multigraph<>(Connection.class);
        for (Connection connection : connections) {
            connectionsGraph.addVertex(connection.originStation());
            connectionsGraph.addVertex(connection.finalStation());
            connectionsGraph.addEdge(connection.originStation(), connection.finalStation(), connection);
        }
        return connectionsGraph;
    }

}
