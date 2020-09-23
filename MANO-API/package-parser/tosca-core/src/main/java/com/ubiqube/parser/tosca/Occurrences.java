package com.ubiqube.parser.tosca;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ubiqube.parser.tosca.deserializer.OccurrencesDeserializer;

@JsonDeserialize(using = OccurrencesDeserializer.class)
public class Occurrences {

}
