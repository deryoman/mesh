package org.seiss.mesh.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Value {

    @JsonProperty("element_id")
    private int id;
    private double value;
}
