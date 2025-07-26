package com.project.oln.model.graph.aux;

import java.math.BigDecimal;

public class NodeDistance {
    private Long nodeId;
    private BigDecimal distance;

    public NodeDistance(Long id, BigDecimal distance) {
        this.nodeId = id;
        this.distance = distance;
    }

    public int cmp(NodeDistance other) {
        return this.distance.compareTo(other.distance);
    }

    public Long getNodeId() {
        return nodeId;
    }

    public BigDecimal getDistance() {
        return distance;
    }
}
