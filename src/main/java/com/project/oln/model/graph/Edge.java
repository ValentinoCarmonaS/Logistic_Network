package com.project.oln.model.graph;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Edge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The distance in Km is obligatory")
    @DecimalMin(value = "0", inclusive = false )
    @Column(name = "distance_km", precision = 10, scale = 2, columnDefinition = "NUMERIC(10,2)", nullable = false)
    private BigDecimal distanceKm;

    @NotNull(message = "The time in minutes is obligatory")
    @DecimalMin(value = "0", inclusive = false )
    @Column(name = "time_min", precision = 10, scale = 2, columnDefinition = "NUMERIC(10,2)", nullable = false)
    private BigDecimal timeMin;

    @NotNull(message = "The cost in usd is obligatory")
    @DecimalMin(value = "0", inclusive = true )
    @Column(name = "cost_usd", precision = 10, scale = 2, columnDefinition = "NUMERIC(10,2)", nullable = false)
    private BigDecimal costUsd;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // Relaciones:
    @NotNull(message = "The origin is obligatory")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "origin_id", nullable = false)
    private Node origin; 

    @NotNull(message = "The destiny is obligatory")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destiny_id", nullable = false)
    private Node destiny; 

    public Edge(Long id, BigDecimal distanceKm, BigDecimal timeMin, BigDecimal costUsd, String description, Node originId, Node destinyId) {
        this.id = id;
        this.distanceKm = distanceKm;
        this.timeMin = timeMin;
        this.costUsd = costUsd;
        this.description = description;
        this.origin = originId;
        this.destiny = destinyId;
    }

    public Long getId() {
        return this.id;
    }

    public boolean nodesIn(Map<Long, List<Edge>> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return false;
        }
        return (nodes.containsKey(this.origin.getId()) && nodes.containsKey(this.destiny.getId()));
    }

    public Edge opposite() {
        return new Edge(this.id*2, this.distanceKm, this.timeMin, this.costUsd, "Opposite Edge", this.destiny, this.origin);
    }

    public Node getOrigin() {
        return this.origin;
    }

    public Node getDestiny() {
        return this.destiny;
    }

    public Long getOriginId() {
        return this.origin.getId();
    }

    public Long getDestinyId() {
        return this.destiny.getId();
    }
}
