package com.ubiqube.parser.tosca.convert;

public interface Converter<U> {

	U convert(Object value);

}
