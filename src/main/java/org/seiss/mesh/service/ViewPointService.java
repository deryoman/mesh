package org.seiss.mesh.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.seiss.mesh.domain.Point;
import org.seiss.mesh.domain.Triangle;
import org.seiss.mesh.domain.Triangles;
import org.seiss.mesh.json.Mesh;
import org.seiss.mesh.json.Node;
import org.seiss.mesh.json.Value;

public class ViewPointService {

    public Triangles getViewPoints(Mesh mesh) {
        Integer numberOfViewSpots = Optional.ofNullable(mesh.getNumberOfViewSpots()).orElse(Integer.MAX_VALUE);
        return getViewPoints(mesh, numberOfViewSpots);
    }

    public Triangles getViewPoints(Mesh mesh, int numberOfViewSpots) {
        Triangles viewPoints = new Triangles();
        Triangles noViewPoints = new Triangles();

        if (numberOfViewSpots == 0) {
            return viewPoints;
        }

        List<Triangle> triangles = getTrianglesSortedByHeight(mesh);

        // The first one is always the highest and therefore a viewpoint
        viewPoints.add(triangles.get(0));

        if (numberOfViewSpots == 1) {
            return viewPoints;
        }

        for (int i = 1, trianglesSize = triangles.size(); i < trianglesSize; i++) {
            Triangle triangle = triangles.get(i);
            if (noViewPoints.isNeighbour(triangle) || viewPoints.isNeighbour(triangle)) {
                noViewPoints.add(triangle);
            } else {
                viewPoints.add(triangle);
            }

            if (viewPoints.size() == numberOfViewSpots) {
                break;
            }
        }

        return viewPoints;
    }

    private List<Triangle> getTrianglesSortedByHeight(Mesh mesh) {
        Map<Integer, Double> heights = mesh.getValues().stream()
            .collect(Collectors.toMap(Value::getId, Value::getValue));
        Map<Integer, Point> nodes = mesh.getNodes().stream()
            .collect(Collectors.toMap(Node::getId, node -> new Point(node.getX(), node.getY())));

        return mesh.getElements().stream()
            .map(element -> {
                List<Point> points = element.getNodes().stream()
                    .map(nodes::get)
                    .collect(Collectors.toList());

                return new Triangle(element.getId(), heights.get(element.getId()), points);
            })
            // Sort height descending
            .sorted((o1, o2) -> Double.compare(o2.getHeight(), o1.getHeight()))
            .collect(Collectors.toList());
    }
}
