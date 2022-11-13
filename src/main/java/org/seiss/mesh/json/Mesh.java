package org.seiss.mesh.json;

import java.util.List;
import lombok.Data;

@Data
public class Mesh {

    private Integer numberOfViewSpots;
    private List<Node> nodes;
    private List<Element> elements;
    private List<Value> values;
}
