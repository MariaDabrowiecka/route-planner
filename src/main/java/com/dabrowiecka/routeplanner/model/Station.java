package com.dabrowiecka.routeplanner.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "Stations")
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "station_id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private final String name;

    public Station(final String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final Station station = (Station) o;

        return new EqualsBuilder()
            .append(name, station.name)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(name)
            .toHashCode();
    }
}
