package org.seiss.mesh;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.seiss.mesh.domain.Triangles;
import org.seiss.mesh.json.Mesh;
import org.seiss.mesh.service.ViewPointService;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String... args) throws IOException {
        if (args.length != 2) {
            LOG.error("usage: view-points <mesh file> <number of view spots>");
            System.exit(1);
        }

        Path path = Paths.get(args[0]);
        if (!path.toFile().exists() || !path.toFile().canRead()) {
            LOG.error("Provided file '{}' does not exists or is not readable", path);
            System.exit(1);
        }

        int numberOfViewSpots = Integer.MAX_VALUE;
        try {
            numberOfViewSpots = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            LOG.error("Number of view spots must be a valid integer", e);
            System.exit(1);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Mesh mesh = objectMapper.readValue(path.toFile(), Mesh.class);
        ViewPointService viewPointService = new ViewPointService();

        Triangles viewPoints = viewPointService.getViewPoints(mesh, numberOfViewSpots);
        System.out.println(viewPoints.toJson());
    }
}
