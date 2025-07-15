package com.project.oln.model.graph;
import com.project.oln.model.graph.enums.NodesType;

import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The name is obligatory")
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "The coordinate of latitude is obligatory")
    @Column(name = "latitude", precision = 10, scale = 7, columnDefinition = "NUMERIC(10,7)", nullable = false)
    private BigDecimal latitude;

    @NotNull(message = "The coordinate of longitude is obligatory")
    @Column(name = "longitude", precision = 10, scale = 7, columnDefinition = "NUMERIC(10,7)", nullable = false)
    private BigDecimal longitude;

    @NotNull(message = "The type is obligatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private NodesType type;

    public Node(Long id, String name, BigDecimal latitude, BigDecimal longitude, NodesType type) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }
    
    public Long getId() {
        return this.id;
    }
}
