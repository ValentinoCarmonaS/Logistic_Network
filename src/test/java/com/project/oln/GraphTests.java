package com.project.oln;

import com.project.oln.model.graph.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.oln.model.graph.Edge;
import com.project.oln.model.graph.enums.NodesType;

@SpringBootTest
class GraphTests {
    
    @Test
    void testGrafo() {
        // Crear nodos A-F
        Node nodeA = new Node((long) 1, "A", new BigDecimal("0.0000000"), new BigDecimal("0.0000000"), NodesType.INTERSECTION);
        Node nodeB = new Node((long) 2, "B", new BigDecimal("1.0000000"), new BigDecimal("0.0000000"), NodesType.INTERSECTION);
        Node nodeC = new Node((long) 3, "C", new BigDecimal("0.0000000"), new BigDecimal("1.0000000"), NodesType.INTERSECTION);
        Node nodeD = new Node((long) 4, "D", new BigDecimal("2.0000000"), new BigDecimal("0.0000000"), NodesType.DELIVERYPOINT);
        Node nodeE = new Node((long) 5, "E", new BigDecimal("1.0000000"), new BigDecimal("1.0000000"), NodesType.INTERSECTION);
        Node nodeF = new Node((long) 6, "F", new BigDecimal("0.0000000"), new BigDecimal("2.0000000"), NodesType.DELIVERYPOINT);

        // Crear lista de aristas con los costos especificados
        List<Edge> edges = new ArrayList<>();
        
        // A - B con costo 3
        edges.add(new Edge(
            (long) 1,
            new BigDecimal("10.0"), // distanceKm (valor ejemplo)
            new BigDecimal("15.0"), // timeMin (valor ejemplo)
            new BigDecimal("3.0"),  // costUsd
            "Conexión A-B", 
            nodeA, 
            nodeB
        ));
        
        // A - C con costo 5
        edges.add(new Edge(
            (long) 2,
            new BigDecimal("12.0"), 
            new BigDecimal("18.0"), 
            new BigDecimal("5.0"), 
            "Conexión A-C", 
            nodeA, 
            nodeC
        ));
        
        // B - D con costo 1
        edges.add(new Edge(
            (long) 3,
            new BigDecimal("5.0"), 
            new BigDecimal("8.0"), 
            new BigDecimal("1.0"), 
            "Conexión B-D", 
            nodeB, 
            nodeD
        ));
        
        // B - E con costo 4
        edges.add(new Edge(
            (long) 4,
            new BigDecimal("8.0"), 
            new BigDecimal("12.0"), 
            new BigDecimal("4.0"), 
            "Conexión B-E", 
            nodeB, 
            nodeE
        ));
        
        // C - E con costo 1
        edges.add(new Edge(
            (long) 5,
            new BigDecimal("6.0"), 
            new BigDecimal("9.0"), 
            new BigDecimal("1.0"), 
            "Conexión C-E", 
            nodeC, 
            nodeE
        ));
        
        // C - F con costo 4
        edges.add(new Edge(
            (long) 6,
            new BigDecimal("11.0"), 
            new BigDecimal("16.0"), 
            new BigDecimal("4.0"), 
            "Conexión C-F", 
            nodeC, 
            nodeF
        ));
        
        // E - D con costo 6
        edges.add(new Edge(
            (long) 7,
            new BigDecimal("14.0"), 
            new BigDecimal("20.0"), 
            new BigDecimal("6.0"), 
            "Conexión E-D", 
            nodeE, 
            nodeD
        ));
        
        // E - F con costo 2
        edges.add(new Edge(
            (long) 8,
            new BigDecimal("7.0"), 
            new BigDecimal("10.0"), 
            new BigDecimal("2.0"), 
            "Conexión E-F", 
            nodeE, 
            nodeF
        ));
        
        // F - D con costo 7
        edges.add(new Edge(
            (long) 9,
            new BigDecimal("16.0"), 
            new BigDecimal("22.0"), 
            new BigDecimal("7.0"), 
            "Conexión F-D", 
            nodeF, 
            nodeD
        ));

        // Crear el grafo
        Graph graph = new Graph(edges);
        
        // Verificar que el grafo se haya creado correctamente
        assertNotNull(graph, "El grafo no debe ser null");
    }
}
