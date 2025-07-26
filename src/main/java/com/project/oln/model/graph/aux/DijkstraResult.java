package com.project.oln.model.graph.aux;

import java.math.BigDecimal;
import java.util.HashMap;

public class DijkstraResult {
    private Long origin;
    private Long destiny;
    private HashMap<Long, Long> father;
    private HashMap<Long, BigDecimal> dist;

    public DijkstraResult(Long origin, Long destiny, HashMap<Long, Long> father, HashMap<Long, BigDecimal> dist) {
        this.origin = origin;
        this.destiny = destiny;
        this.father = father;
        this.dist = dist;
    }



    public HashMap<Long, Long> getFather() {
        return father;
    }

    public BigDecimal getDist() {
        return dist.get(destiny);
    }
}
