package org.seiss.mesh.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Triangle {

    @JsonProperty("element_id")
    private final int id;
    @JsonProperty("value")
    private final double height;
    @JsonIgnore
    private final Set<Point> points = new HashSet<>();

    public Triangle(int id, double height, List<Point> points) {
        if (points.size() != 3) {
            throw new IllegalArgumentException("A triangle must have three vertices");
        }

        this.id = id;
        this.height = height;
        this.points.addAll(points);
    }

    Triangle(int id, double height, Point a, Point b, Point c) {
        this(id, height, List.of(a, b, c));
    }

    public boolean isNeighbour(Triangle other) {
        return points.stream()
            .anyMatch(point -> other.getPoints().contains(point));
    }
}
