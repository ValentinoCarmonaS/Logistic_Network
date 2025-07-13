-- ========================================
-- INDEX FOR NODES
-- ========================================
CREATE UNIQUE INDEX idx_nodes_type ON nodes(type);
CREATE UNIQUE INDEX idx_nodes_name ON nodes(name);

-- ========================================
-- INDEX FOR EDGES
-- ========================================
CREATE INDEX idx_edges_origin_id ON edges(origin_id);
CREATE INDEX idx_edges_destiny_id ON edges(destiny_id);
CREATE INDEX idx_edges_distance_km ON edges(distance_km);
CREATE INDEX idx_edges_time_min ON edges(time_min);
CREATE INDEX idx_edges_cost_usd ON edges(cost_usd);