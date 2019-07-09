package com.ubiqube.etsi.mano.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.ubiqube.etsi.mano.exception.GenericException;

/**
 * Naive implementation of a Patch engine.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class WeakPatcher implements Patcher {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(WeakPatcher.class);
	/** Json object mapper. */
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void patch(String _patchDocument, Object _entity) {
		try {
			final JsonNode patch = mapper.readTree(_patchDocument);
			walk(patch, _entity);
		} catch (final IOException _e) {
			throw new GenericException(_e);
		}
	}

	private void walk(JsonNode _patch, Object _entity) {
		try {
			_walk(_patch, new ArrayDeque<String>(), _entity);
		} catch (final Exception e) {
			throw new GenericException(e);
		}
	}

	private void _walk(JsonNode jsonNode, Deque<String> _stack, Object _entity) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (jsonNode.isObject()) {
			final Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.fields();

			while (iter.hasNext()) {
				final Map.Entry<String, JsonNode> entry = iter.next();
				_stack.push(entry.getKey());
				_walk(entry.getValue(), _stack, _entity);
				_stack.pop();
			}
		} else if (jsonNode.isArray()) {
			final ArrayNode arrayNode = (ArrayNode) jsonNode;

			for (int i = 0; i < arrayNode.size(); i++) {
				System.out.println("array node");
			}

		} else if (jsonNode.isValueNode()) {
			final ValueNode valueNode = (ValueNode) jsonNode;
			patchEntity(_stack, valueNode.asText(), _entity);
		}

	}

	private void patchEntity(Deque<String> _stack, String _value, Object _entity) throws IllegalAccessException, InvocationTargetException {
		final String key = StringUtils.join(_stack.descendingIterator(), ".");
		if (LOG.isDebugEnabled()) {
			// LOG.debug("Patching object {} on field {} with value {}",
			// _entity.getClass().getName(), key, _value);
			LOG.debug("Patching object " + _entity.getClass().getName() + " on field " + key + " with value " + _value);
		}
		BeanUtils.setProperty(_entity, key, _value);
	}

}
