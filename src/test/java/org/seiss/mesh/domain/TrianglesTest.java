package org.seiss.mesh.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class TrianglesTest {

    @Test
    void testIsNeighbour() {
        Triangle t1 = new Triangle(
            1,
            0.1,
            List.of(
                new Point(0.0,0.0),
                new Point(0.0,1.0),
                new Point(1.0,1.0)
            )
        );

        Triangle t2 = new Triangle(
            2,
            0.2,
            List.of(
                new Point(0.0,10.0),
                new Point(0.0,9.0),
                new Point(1.0,10.0)
            )
        );

        Triangle t3 = new Triangle(
            3,
            0.3,
            List.of(
                new Point(1.0,0.0),
                new Point(1.0,1.0),
                new Point(2.0,1.0)
            )
        );

        Triangles points = new Triangles();
        points.add(t1);
        points.add(t2);

        assertThat(points.isNeighbour(t3)).isTrue();
    }

    @Test
    void testIsNoNeighbour() {
        Triangle t1 = new Triangle(
            1,
            0.1,
            List.of(
                new Point(0.0,0.0),
                new Point(0.0,1.0),
                new Point(1.0,1.0)
            )
        );

        Triangle t2 = new Triangle(
            2,
            0.2,
            List.of(
                new Point(0.0,10.0),
                new Point(0.0,9.0),
                new Point(1.0,10.0)
            )
        );

        Triangle t3 = new Triangle(
            3,
            0.3,
            List.of(
                new Point(6.0,0.0),
                new Point(6.0,1.0),
                new Point(7.0,1.0)
            )
        );

        Triangles points = new Triangles();
        points.add(t1);
        points.add(t2);

        assertThat(points.isNeighbour(t3)).isFalse();
    }
}