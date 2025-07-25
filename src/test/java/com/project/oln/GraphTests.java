package com.project.oln;

import com.project.oln.model.graph.*;
import com.project.oln.model.graph.aux.PathResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.oln.model.graph.enums.NodesType;

@SpringBootTest
class GraphTests {
    
    private static Graph graph;

    @BeforeAll
    static void setUp() {
        // Crear nodos A-F
        Node nodeA = new Node(1L, "A", new BigDecimal("0.0000000"), new BigDecimal("0.0000000"), NodesType.INTERSECTION);
        Node nodeB = new Node(2L, "B", new BigDecimal("1.0000000"), new BigDecimal("0.0000000"), NodesType.INTERSECTION);
        Node nodeC = new Node(3L, "C", new BigDecimal("0.0000000"), new BigDecimal("1.0000000"), NodesType.INTERSECTION);
        Node nodeD = new Node(4L, "D", new BigDecimal("2.0000000"), new BigDecimal("0.0000000"), NodesType.DELIVERYPOINT);
        Node nodeE = new Node(5L, "E", new BigDecimal("1.0000000"), new BigDecimal("1.0000000"), NodesType.INTERSECTION);
        Node nodeF = new Node(6L, "F", new BigDecimal("0.0000000"), new BigDecimal("2.0000000"), NodesType.DELIVERYPOINT);

        // Crear lista de aristas con los costos especificados
        List<Edge> edges = new ArrayList<>();
        
        // A - B con costo 3
        edges.add(new Edge(
            1L,
            new BigDecimal("10.0"), // distanceKm (valor ejemplo)
            new BigDecimal("15.0"), // timeMin (valor ejemplo)
            new BigDecimal("3.0"),  // costUsd
            "Conexión A-B", 
            nodeA, 
            nodeB
        ));
        
        // A - C con costo 5
        edges.add(new Edge(
            2L,
            new BigDecimal("12.0"), 
            new BigDecimal("18.0"), 
            new BigDecimal("5.0"), 
            "Conexión A-C", 
            nodeA, 
            nodeC
        ));
        
        // B - D con costo 1
        edges.add(new Edge(
            3L,
            new BigDecimal("5.0"), 
            new BigDecimal("8.0"), 
            new BigDecimal("1.0"), 
            "Conexión B-D", 
            nodeB, 
            nodeD
        ));
        
        // B - E con costo 4
        edges.add(new Edge(
            4L,
            new BigDecimal("8.0"), 
            new BigDecimal("12.0"), 
            new BigDecimal("4.0"), 
            "Conexión B-E", 
            nodeB, 
            nodeE
        ));
        
        // C - E con costo 1
        edges.add(new Edge(
            5L,
            new BigDecimal("6.0"), 
            new BigDecimal("9.0"), 
            new BigDecimal("1.0"), 
            "Conexión C-E", 
            nodeC, 
            nodeE
        ));
        
        // C - F con costo 4
        edges.add(new Edge(
            6L,
            new BigDecimal("11.0"), 
            new BigDecimal("16.0"), 
            new BigDecimal("4.0"), 
            "Conexión C-F", 
            nodeC, 
            nodeF
        ));
        
        // E - D con costo 6
        edges.add(new Edge(
            7L,
            new BigDecimal("14.0"), 
            new BigDecimal("20.0"), 
            new BigDecimal("6.0"), 
            "Conexión E-D", 
            nodeE, 
            nodeD
        ));
        
        // E - F con costo 2
        edges.add(new Edge(
            8L,
            new BigDecimal("7.0"), 
            new BigDecimal("10.0"), 
            new BigDecimal("2.0"), 
            "Conexión E-F", 
            nodeE, 
            nodeF
        ));
        
        // F - D con costo 7
        edges.add(new Edge(
            9L,
            new BigDecimal("16.0"), 
            new BigDecimal("22.0"), 
            new BigDecimal("7.0"), 
            "Conexión F-D", 
            nodeF, 
            nodeD
        ));

        // Crear el grafo
        graph = new Graph(edges);
    }

    @Test
    void test01GraphBase() {
        assertNotNull(graph, "El grafo no debe ser null");
        assertEquals(6, graph.numberOfNodes(), "El grafo debe contener 6 nodos: A-B-C-D-E-F");
        assertEquals(9, graph.numberOfEdges(), "El grafo debe contener 9 aristas");
    }

    @Test
    void test02GetAllEdges() {
        assertEquals(graph.numberOfEdges(), graph.getEdges().size());
    }

    @Test
    void test03ShortestPath() {
        PathResult res1 = graph.shortestPath(1L, 5L, Edge::getCostUsd);
        PathResult res2 = graph.shortestPath(2L, 6L, Edge::getCostUsd);
        PathResult res3 = graph.shortestPath(1L, 4L, Edge::getCostUsd);
        PathResult res4 = graph.shortestPath(4L, 3L, Edge::getCostUsd);

        assertEquals(0, BigDecimal.valueOf(6).compareTo(res1.getDist()) );
        assertEquals(0, BigDecimal.valueOf(6).compareTo(res2.getDist()) );
        assertEquals(0, BigDecimal.valueOf(4).compareTo(res3.getDist()) );
        assertEquals(0, BigDecimal.valueOf(6).compareTo(res4.getDist()) );
    }

    @Test
    void test04RebuildPath() {
        PathResult res = graph.shortestPath(1L, 5L, Edge::getCostUsd);
        List<Long> path = res.getPath(null);
        List<Long> exp = List.of(1L, 3L, 5L);
        
        assertEquals(3, path.size());
        assertEquals(exp, path);

        PathResult res2 = graph.bfsPath(1L);
        List<Long> path2 = res2.getPath(2L);
        
        assertEquals(2, path2.size());
    }
}
