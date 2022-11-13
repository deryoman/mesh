package org.seiss.mesh.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Triangles {

    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private final List<Triangle> triangles = new ArrayList<>();
    // For comparison only
    @JsonIgnore
    private final Set<Triangle> trianglesInternal = new HashSet<>();

    public void add(Triangle triangle) {
        triangles.add(triangle);
        trianglesInternal.add(triangle);
    }

    public boolean isNeighbour(Triangle other) {
        return trianglesInternal.stream()
            .anyMatch(t -> t.isNeighbour(other));
    }

    public String toJson() throws JsonProcessingException {
        return mapper.writeValueAsString(triangles);
    }

    public int size() {
        return triangles.size();
    }

    public List<Triangle> get() {
        return List.copyOf(triangles);
    }
}
