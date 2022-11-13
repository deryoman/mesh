package org.seiss.mesh.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.seiss.mesh.domain.Triangle;
import org.seiss.mesh.domain.Triangles;
import org.seiss.mesh.json.Mesh;

class ViewPointServiceTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private ViewPointService viewPointService;

    @BeforeEach
    void setUp() {
        viewPointService = new ViewPointService();
    }

    @Test
    void testGetViewPoints() throws IOException {
        Mesh mesh = objectMapper.readValue(new File(getClass().getClassLoader().getResource("mesh.json").getFile()), Mesh.class);

        Triangles viewPoints = viewPointService.getViewPoints(mesh, Integer.MAX_VALUE);

        assertThat(viewPoints.get()).extracting(Triangle::getId)
            .containsExactly(
                153,
                141,
                99,
                87,
                199,
                185,
                33,
                21,
                18
            );
    }

    @Test
    void testGetViewPointsWithCount() throws IOException {
        Mesh mesh = objectMapper.readValue(new File(getClass().getClassLoader().getResource("mesh.json").getFile()), Mesh.class);

        Triangles viewPoints = viewPointService.getViewPoints(mesh, 4);

        assertThat(viewPoints.get()).extracting(Triangle::getId)
            .containsExactly(
                153,
                141,
                99,
                87
            );
    }
}