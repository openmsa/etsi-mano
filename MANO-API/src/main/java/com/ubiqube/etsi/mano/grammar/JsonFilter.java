package com.ubiqube.etsi.mano.grammar;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.grammar.Node.Operand;

@Service
public class JsonFilter {

	private static final Logger LOG = LoggerFactory.getLogger(JsonFilter.class);

	private final JsonBeanUtil jsonBeanUtil;

	public JsonFilter(JsonBeanUtil jsonBeanUtil) {
		super();
		this.jsonBeanUtil = jsonBeanUtil;
	}

	/**
	 * In fact this method could be more generic by taking an Object.
	 *
	 * @param subscriptionRepository
	 * @param nodes
	 * @return
	 */
	public boolean apply(Object _object, AstBuilder _astBuilder) {
		for (final Node node : _astBuilder.getNodes()) {
			if (!apply(_object, node)) {
				return false;
			}
		}
		return true;
	}

	private boolean apply(Object _object, Node _node) {
		final Map<String, JsonBeanProperty> props = jsonBeanUtil.getProperties(_object);
		final JsonBeanProperty realProperty = props.get(_node.getName());
		if (realProperty == null) {
			LOG.warn("Object of class {} doesn't have a {} property.", _object.getClass(), _node.getName());
			return true;
		}
		final String objectValue = invokeGetter(_object, realProperty);
		return decide(objectValue, _node.getValue(), _node.getOp());
	}

	private static String invokeGetter(Object _object, JsonBeanProperty realProperty) {
		try {
			return (String) realProperty.getPropertyDescriptor().getReadMethod().invoke(_object);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new GenericException(e);
		}
	}

	private static boolean decide(String _objectValue, String _value, Operand _operand) {
		if (null == _operand) {
			return true;
		}
		if (Operand.EQ.equals(_operand)) {
			return _value.equals(_objectValue);
		}
		if (Operand.NEQ.equals(_operand)) {
			return !_value.equals(_objectValue);
		}
		// GT LT GTE LTE are numerical so cast everything in integer
		final int left = Integer.parseInt(_objectValue);
		final int right = Integer.parseInt(_value);
		if (Operand.GT.equals(_operand)) {
			return left > right;
		}
		if (Operand.LT.equals(_operand)) {
			return left < right;
		}
		if (Operand.GTE.equals(_operand)) {
			return left >= right;
		}
		if (Operand.LTE.equals(_operand)) {
			return left <= right;
		}
		return false;
	}
}
