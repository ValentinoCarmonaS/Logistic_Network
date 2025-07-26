package com.project.oln.model.graph;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;

import com.project.oln.exceptions.OperationNotAllowedException;
import com.project.oln.model.graph.aux.DijkstraResult;
import com.project.oln.model.graph.aux.NodeDistance;

public class Graph {
    private HashMap<Long, List<Edge>> nodes;
    private final BigDecimal infinity = BigDecimal.valueOf(Double.MAX_VALUE);
    
    public Graph(List<Edge> edges) {
        this.nodes = new HashMap<>();

        if (edges != null) {
            for (Edge edge : edges) {
                this.addNode(edge.getOrigin());
                this.addNode(edge.getDestiny());
                this.addEdge(edge);
            }
        }
    }

    public void addNode(Node node) {
        if (!this.nodes.containsKey(node.getId())) {
            this.nodes.put(node.getId(), new ArrayList<>());
        }
    }

    public void addEdge(Edge edge) {
        if (!edge.nodesIn(nodes)) {
            throw new OperationNotAllowedException("The nodes or one node doesn't exist");
        }
        nodes.get(edge.getOriginId()).add(edge);
        nodes.get(edge.getDestinyId()).add(edge);
    }

    public DijkstraResult shortestPath(Node origin, Node destiny, Function<Edge, BigDecimal> weightSelector) {
        HashMap<Long, BigDecimal> dist = new HashMap<>();
        HashMap<Long, Long> father = new HashMap<>();
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(NodeDistance::cmp);

        for (Long v : nodes.keySet()) {
            dist.put(v, this.infinity);
        }

        father.put(origin.getId(), null);
        dist.put(origin.getId(), new BigDecimal(0));
        pq.add(new NodeDistance(origin.getId(), new BigDecimal(0)));

        while (!pq.isEmpty()) {

            NodeDistance v = pq.remove();

            if (v.getNodeId().equals(destiny.getId())) {
                return new DijkstraResult(father, dist);
            }

            for (Edge e : nodes.get(v.getNodeId())) {
                Long w = e.getDestinyId(); 
                BigDecimal weigth = weightSelector.apply(e);
                BigDecimal pathToHere = v.getDistance().add(weigth);

                if (pathToHere.compareTo(dist.get(w)) < 0) {
                    dist.put(w, pathToHere);
                    father.put(w, v.getNodeId());
                    pq.add(new NodeDistance(w, pathToHere));
                }
            }
        }

        return new DijkstraResult(father, dist);
    }
}
