package com.ubiqube.etsi.mano.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.json.OperationalStateConverter;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOperationalStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum;

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
	private final BeanUtilsBean beanUtils;

	public WeakPatcher() {
		final ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
		convertUtilsBean.register(new OperationalStateConverter(), OperationalStateEnum.class);
		convertUtilsBean.register(new OperationalStateConverter(), NsdOperationalStateEnum.class);
		beanUtils = new BeanUtilsBean(convertUtilsBean);
	}

	@Override
	public void patch(final String _patchDocument, final Object _entity) {
		try {
			final JsonNode patch = mapper.readTree(_patchDocument);
			walk(patch, _entity);
		} catch (final IOException _e) {
			throw new GenericException(_e);
		}
	}

	private void walk(final JsonNode _patch, final Object _entity) {
		try {
			_walk(_patch, new ArrayDeque<String>(), _entity);
		} catch (final Exception e) {
			throw new GenericException(e);
		}
	}

	private void _walk(final JsonNode jsonNode, final Deque<String> _stack, final Object _entity) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
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
			final List<String> res = new ArrayList<>();
			for (int i = 0; i < arrayNode.size(); i++) {
				final JsonNode val = arrayNode.get(i);
				if (val.isValueNode()) {
					res.add(val.asText());
				} else {
					LOG.warn("Unable to set/walk {}", val);
				}
			}
			patchEntity(_stack, res, _entity);
		} else if (jsonNode.isValueNode()) {
			final ValueNode valueNode = (ValueNode) jsonNode;
			patchEntity(_stack, valueNode.asText(), _entity);
		}

	}

	private void patchEntity(final Deque<String> _stack, final Object _value, final Object _entity) throws IllegalAccessException, InvocationTargetException {
		final String key = StringUtils.join(_stack.descendingIterator(), ".");
		if (LOG.isDebugEnabled()) {
			// LOG.debug("Patching object {} on field {} with value {}",
			// _entity.getClass().getName(), key, _value);
			LOG.debug("Patching object " + _entity.getClass().getName() + " on field " + key + " with value " + _value);
		}
		beanUtils.setProperty(_entity, key, _value);
	}

}
