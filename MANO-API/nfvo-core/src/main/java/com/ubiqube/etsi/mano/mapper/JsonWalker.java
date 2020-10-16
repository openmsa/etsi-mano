package com.ubiqube.etsi.mano.mapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ubiqube.etsi.mano.exception.GenericException;

@Service
public class JsonWalker {

	private static final Logger LOG = LoggerFactory.getLogger(JsonWalker.class);

	private final ObjectMapper mapper;

	public JsonWalker(final ObjectMapper _mapper) {
		mapper = _mapper;
	}

	public void walk(final String _patchDocument, final BeanListener beanListener) {
		try {
			LOG.debug("JsonWalking ");
			final JsonNode patch = mapper.readTree(_patchDocument);
			walk(patch, beanListener);
		} catch (final IOException _e) {
			throw new GenericException(_e);
		}
	}

	public void walk(final JsonNode _patch, final BeanListener beanListener) {
		try {
			walkInner(_patch, beanListener);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new GenericException(e);
		}
	}

	private static void walkInner(final JsonNode jsonNode, final BeanListener beanListener) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (jsonNode.isObject()) {
			final Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.fields();
			while (iter.hasNext()) {
				final Map.Entry<String, JsonNode> entry = iter.next();
				beanListener.complexStart(entry.getKey());
				walkInner(entry.getValue(), beanListener);
				beanListener.complexEnd();
			}
		} else if (jsonNode.isArray()) {
			final ArrayNode arrayNode = (ArrayNode) jsonNode;
			for (int i = 0; i < arrayNode.size(); i++) {
				final JsonNode val = arrayNode.get(i);
				beanListener.listElementStart(i);
				if (val.isValueNode()) {
					beanListener.addProperty(val.asText());
				} else {
					walkInner(val, beanListener);
				}
				beanListener.listElementEnd();
			}
		} else if (jsonNode.isValueNode()) {
			beanListener.addProperty(jsonNode.asText());
		}
	}

}
