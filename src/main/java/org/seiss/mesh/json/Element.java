package org.seiss.mesh.json;

import java.util.List;
import lombok.Data;

@Data
public class Element {

    private int id;
    private List<Integer> nodes;
}
