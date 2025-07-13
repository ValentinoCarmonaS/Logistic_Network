package com.project.oln.model.graph;

import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Edges {
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
    private Long originId; 

    @NotNull(message = "The destiny is obligatory")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destiny_id", nullable = false)
    private Long destinyId; 

    public Edges(BigDecimal distanceKm, BigDecimal timeMin, BigDecimal costUsd, String description, Long originId, Long destinyId) {
        this.distanceKm = distanceKm;
        this.timeMin = timeMin;
        this.costUsd = costUsd;
        this.description = description;
        this.originId = originId;
        this.destinyId = destinyId;
    }
}
