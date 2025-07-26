package com.project.oln.model.graph;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Function;

import com.project.oln.exceptions.OperationNotAllowedException;
import com.project.oln.model.graph.aux.PathResult;
import com.project.oln.model.graph.aux.NodeDistance;

public class Graph {
    
    private final BigDecimal infinity = BigDecimal.valueOf(Double.MAX_VALUE);
    private HashMap<Long, List<Edge>> nodes;
    private int numberOfEdges;
    private int numberOfNodes;
    
    public Graph(List<Edge> edges) {
        this.nodes = new HashMap<>();
        this.numberOfEdges = 0;
        this.numberOfNodes = 0;

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
            this.numberOfNodes++;
        }
    }

    public void addEdge(Edge edge) {
        if (!edge.nodesIn(this.nodes)) {
            throw new OperationNotAllowedException("The nodes or one node doesn't exist");
        }

        this.nodes.get(edge.getOriginId()).add(edge);
        
        Edge otherEdge = edge.opposite();
        this.nodes.get(edge.getDestinyId()).add(otherEdge);
        
        this.numberOfEdges++;
    }

    public PathResult bfsPath(Long origin) {
        HashMap<Long, Long> father = new HashMap<>();
        HashMap<Long, BigDecimal> lvl = new HashMap<>();
        HashMap<Long, Boolean> visit = new HashMap<>();
        Queue<Long> queue = new LinkedList<>();
        
        father.put(origin, null);
        lvl.put(origin, BigDecimal.ZERO);
        visit.put(origin, true);
        queue.add(origin);

        while (!queue.isEmpty()) {
            Long v = queue.remove();
            for (Edge edge : nodes.get(v)) {
                if (!visit.containsKey(edge.getDestinyId())) {
                    father.put(edge.getDestinyId(), v);
                    lvl.put(edge.getDestinyId(), lvl.get(v).add(BigDecimal.ONE));
                    visit.put(edge.getDestinyId(), true);
                    queue.add(edge.getDestinyId());
                }
            }
        }

        return new PathResult(origin, null, father, lvl);
    }

    public PathResult shortestPath(Long origin, Long destiny, Function<Edge, BigDecimal> weightSelector) {
        HashMap<Long, BigDecimal> dist = new HashMap<>();
        HashMap<Long, Long> father = new HashMap<>();
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(NodeDistance::cmp);

        for (Long v : this.nodes.keySet()) {
            dist.put(v, this.infinity);
        }

        father.put(origin, null);
        dist.put(origin, new BigDecimal(0));
        pq.add(new NodeDistance(origin, new BigDecimal(0)));

        while (!pq.isEmpty()) {

            NodeDistance v = pq.remove();

            if (v.getNodeId().equals(destiny)) {
                return new PathResult(origin, destiny, father, dist);
            }

            for (Edge e : this.nodes.get(v.getNodeId())) {
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

        return new PathResult(origin, destiny, father, dist);
    }

    public int numberOfNodes() {
        return this.numberOfNodes;
    }

    public int numberOfEdges() {
        return this.numberOfEdges;
    }

    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        HashMap<Long, Boolean> vist = new HashMap<>();

        for (Long v : this.nodes.keySet()) {
            for (Edge edge : this.nodes.get(v)) {
                if (!vist.containsKey(edge.getDestinyId())) {
                    edges.add(edge);
                }
            }
            vist.put(v, true);
        }

        return edges;
    }
}
