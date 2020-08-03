package com.dabrowiecka.routeplanner.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.assertj.core.util.Preconditions;
import org.jgrapht.graph.DefaultWeightedEdge;

import com.dabrowiecka.routeplanner.utils.DurationCalculator;

@Entity
@Table(name = "Connections")
public class Connection extends DefaultWeightedEdge {

    @Id
    @Column(name = "connection_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_station_id", referencedColumnName = "station_id")
    private final Station originStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "final_station_id", referencedColumnName = "station_id")
    private final Station finalStation;

    @Temporal(TemporalType.DATE)
    @Column(name = "departure_date")
    private final Date departureDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "arrival_date")
    private final Date arrivalDate;

    public Connection(final Station originStation,
                      final Station finalStation,
                      final Date departureDate,
                      final Date arrivalDate) {
        Preconditions.checkArgument(arrivalDate.after(departureDate), "Dates must be provided in ascending order");
        this.originStation = originStation;
        this.finalStation = finalStation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public Station originStation() {
        return originStation;
    }

    public Station finalStation() {
        return finalStation;
    }

    public Date departureDate() {
        return departureDate;
    }

    public Date arrivalDate() {
        return arrivalDate;
    }

    public Long durationInMinutes() {
        return DurationCalculator.durationInMinutes(departureDate, arrivalDate);
    }

    @Override
    public double getWeight() {
        return this.durationInMinutes();
    }

    @Override
    public String toString() {
        return String.format("From: %s, to: %s, Departure time: %s, Arrival time: %s",
                             this.originStation, this.finalStation, this.departureDate, this.arrivalDate);
    }
}
