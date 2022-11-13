package org.seiss.mesh;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.seiss.mesh.domain.Triangles;
import org.seiss.mesh.json.Mesh;
import org.seiss.mesh.service.ViewPointService;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> event, Context context) {
        ViewPointService viewPointService = new ViewPointService();

        try {
            Mesh mesh = objectMapper.readValue(objectMapper.writeValueAsString(event), Mesh.class);

            Triangles viewPoints = viewPointService.getViewPoints(mesh);

            return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(viewPoints.get())
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
