package com.company.tour.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "STOP")
@Entity
public class Stop {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "LOCATION", nullable = false)
    @NotNull
    private Point location;

    @Column(name = "ROUTE")
    private LineString route;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Column(name = "DURATION")
    private Integer duration;

    @JoinTable(name = "TOUR_STOP_LINK",
            joinColumns = @JoinColumn(name = "STOP_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TOUR_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Tour> tours;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public LineString getRoute() {
        return route;
    }

    public void setRoute(LineString route) {
        this.route = route;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}