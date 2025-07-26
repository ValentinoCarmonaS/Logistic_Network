package com.project.oln.model.graph.aux;

import java.math.BigDecimal;
import java.util.HashMap;

public class DijkstraResult {
    private HashMap<Long, Long> father;
    private HashMap<Long, BigDecimal> dist;

    public DijkstraResult(HashMap<Long, Long> father, HashMap<Long, BigDecimal> dist) {
        this.father = father;
        this.dist = dist;
    }

    public HashMap<Long, Long> getFather() {
        return father;
    }

    public HashMap<Long, BigDecimal> getDist() {
        return dist;
    }

}
