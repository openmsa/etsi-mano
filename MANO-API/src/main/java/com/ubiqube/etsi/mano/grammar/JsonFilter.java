package com.ubiqube.etsi.mano.grammar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.grammar.Node.Operand;

@Service
public class JsonFilter {

	private static final Logger LOG = LoggerFactory.getLogger(JsonFilter.class);

	private final JsonBeanUtil jsonBeanUtil;

	public JsonFilter(final JsonBeanUtil jsonBeanUtil) {
		super();
		this.jsonBeanUtil = jsonBeanUtil;
	}

	/**
	 *
	 * @param _object     An arbitraru not null object.
	 * @param _astBuilder An AST Builder.
	 * @return
	 */
	public boolean apply(@Nonnull final Object _object, @Nonnull final AstBuilder _astBuilder) {
		final Node node = _astBuilder.getNodes().stream()
				.filter(x -> !apply(_object, x))
				.findFirst()
				.orElse(null);
		return (null != node) ? false : true;
	}

	private boolean apply(@Nonnull final Object _object, @Nonnull final Node _node) {
		final Map<String, JsonBeanProperty> props = jsonBeanUtil.getProperties(_object);
		final JsonBeanProperty realProperty = props.get(_node.getName());
		if (realProperty == null) {
			LOG.warn("Object of class {} doesn't have a {} property.", _object.getClass(), _node.getName());
			return true;
		}
		final DocumentStatus documentStatus = invokeGetter(_object, realProperty, 0, _node);
		return documentStatus.getStatus().equals(DocumentStatus.Status.VALIDATED);
	}

	private static DocumentStatus invokeGetter(final Object _object, final JsonBeanProperty realProperty, final int index, final Node _node) {
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
			} else {
				// TODO: ???
			}
		}
		// Normally we should have the latest value.
		if (decide((String) temp, _node.getValue(), _node.getOp())) {
			return new DocumentStatus(DocumentStatus.Status.VALIDATED);
		}
		return new DocumentStatus(DocumentStatus.Status.REFUSED);
	}

	private static DocumentStatus exploreList(final List<?> list, final JsonBeanProperty realProperty, final int index, final Node _node) {
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

	private static boolean decide(final String _objectValue, final String _value, final Operand _operand) {
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
