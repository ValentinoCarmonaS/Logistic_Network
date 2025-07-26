package com.project.oln.model.graph.aux;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.project.oln.exceptions.OperationNotAllowedException;

public class PathResult {
    private Long origin;
    private Long destiny;
    private HashMap<Long, Long> father;
    private HashMap<Long, BigDecimal> dist;

    public PathResult(Long origin, Long destiny, HashMap<Long, Long> father, HashMap<Long, BigDecimal> dist) {
        this.origin = origin;
        this.destiny = destiny;
        this.father = father;
        this.dist = dist;
    }

    public List<Long> getPath(Long objetive) {
        List<Long> res = new ArrayList<>();
        Long act = destiny;

        if (objetive != null) {
            act = objetive;
        }

        while (act != null) {
            res.add(act);
            act = this.father.get(act);
        }

        Collections.reverse(res);
        return res;
    }

    public BigDecimal getDist() {
        if (this.dist == null) {
            throw new OperationNotAllowedException("Cant get the distance for a path with no weight");
        }
        return this.dist.get(destiny);
    }
}
