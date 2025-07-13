CREATE TABLE edges (
    id BIGSERIAL PRIMARY KEY,
    origin_id BIGINT NOT NULL REFERENCES nodes(id) ON DELETE CASCADE,
    destiny_id BIGINT NOT NULL REFERENCES nodes(id) ON DELETE CASCADE,
    distance_km NUMERIC(10,2) NOT NULL CHECK(distance_km > 0),
    time_min NUMERIC(10,2) NOT NULL CHECK(time_min > 0),
    cost_usd NUMERIC(10,2) NOT NULL CHECK(cost_usd >= 0),
    description TEXT
);