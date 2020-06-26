package com.ubiqube.parser.tosca.constraints;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ubiqube.parser.tosca.deserializer.ConstraintsDeserializer;

@JsonDeserialize(using = ConstraintsDeserializer.class)
public interface Constraint {

}
