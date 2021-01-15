package com.ubiqube.parser.tosca;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ubiqube.parser.tosca.deserializer.OccurrencesDeserializer;

/**
 * implied default of [1,UNBOUNDED]
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@JsonDeserialize(using = OccurrencesDeserializer.class)
public class Occurrences {

	private int min;
	private int max;

	public Occurrences(final int _min, final int _max) {
		min = _min;
		max = _max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(final int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(final int max) {
		this.max = max;
	}

}
