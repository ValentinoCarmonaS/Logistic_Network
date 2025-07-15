package com.project.oln.model.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.project.oln.exceptions.OperationNotAllowedException;

public class Graph {
    private HashMap<Long, List<Edge>> nodes;
    
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
}
