package com.ubiqube.etsi.mano.grammar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
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
		final DocumentStatus documentStatus = invokeGetter(_object, realProperty, 0, _node);
		return documentStatus.getStatus().equals(DocumentStatus.Status.VALIDATED);
	}

	private static DocumentStatus invokeGetter(Object _object, JsonBeanProperty realProperty, int index, Node _node) {
		final List<JsonBeanProperty> access = realProperty.getListAccessors();
		Object temp = _object;
		for (int i = index; i < access.size(); i++) {
			final JsonBeanProperty jsonBeanProperty = access.get(i);
			final Method readMethod = jsonBeanProperty.getPropertyDescriptor().getReadMethod();
			try {
				LOG.info("Invoking {} On {}", readMethod.getName(), temp.getClass());
				temp = readMethod.invoke(temp);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new GenericException(e);
			}
			if (temp == null) {
				return new DocumentStatus(DocumentStatus.Status.NOSTATE);
			}
			if (List.class.isAssignableFrom(temp.getClass())) {
				final List<?> list = (List<?>) temp;
				final DocumentStatus status = exploreList(list, realProperty, i, _node);
				if (!status.getStatus().equals(DocumentStatus.Status.NOSTATE)) {
					return status;
				}
			}
		}
		// Normally we should have the latest value.
		if (decide((String) temp, _node.getValue(), _node.getOp())) {
			return new DocumentStatus(DocumentStatus.Status.VALIDATED);
		}
		return new DocumentStatus(DocumentStatus.Status.REFUSED);
	}

	private static DocumentStatus exploreList(List<?> list, JsonBeanProperty realProperty, int index, Node _node) {
		LOG.debug("Exploring sub list.");
		for (final Object object : list) {
			final DocumentStatus status = invokeGetter(object, realProperty, index + 1, _node);
			if (status.getStatus().equals(DocumentStatus.Status.NOSTATE)) {
				continue;
			}
			return status;
		}
		return new DocumentStatus(DocumentStatus.Status.NOSTATE);
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
