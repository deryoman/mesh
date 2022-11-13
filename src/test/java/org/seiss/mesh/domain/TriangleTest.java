package org.seiss.mesh.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TriangleTest {

    @Test
    void testIsNeighbourWithNoPointMatching() {
        Triangle t1 = new Triangle(
            1,
            0.1,
            new Point(1.0, 0.0),
            new Point(2.0, 0.0),
            new Point(2.0, 1.0)
        );

        Triangle t2 = new Triangle(
            2,
            0.2,
            new Point(5.0, 0.0),
            new Point(6.0, 1.0),
            new Point(6.0, 0.0)
        );

        assertThat(t1.isNeighbour(t2)).isFalse();
    }

    @Test
    void testIsNeighbourWithOnePointMatching() {
        Triangle t1 = new Triangle(
            1,
            0.1,
            new Point(1.0, 0.0),
            new Point(2.0, 0.0),
            new Point(2.0, 1.0)
        );

        Triangle t2 = new Triangle(
            2,
            0.2,
            new Point(2.0, 1.0),
            new Point(2.0, 2.0),
            new Point(3.0, 1.0)
        );

        assertThat(t1.isNeighbour(t2)).isTrue();
    }

    @Test
    void testIsNeighbourWithTwoPointMatching() {
        Triangle t1 = new Triangle(
            1,
            0.1,
            new Point(1.0, 0.0),
            new Point(2.0, 0.0),
            new Point(2.0, 1.0)
        );

        Triangle t2 = new Triangle(
            2,
            0.2,
            new Point(2.0, 1.0),
            new Point(2.0, 0.0),
            new Point(3.0, 1.0)
        );

        assertThat(t1.isNeighbour(t2)).isTrue();
    }
}