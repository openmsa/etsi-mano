package com.ubiqube.etsi.mano.grammar;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.beanutils.BeanUtils;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.grammar.Node.Operand;

/**
 * Handle filter parsing and filering on objects for VNFPackage Subscription.
 *
 * @author ovi@ubiqube.com
 *
 */
public class ObjectFilter {

	private final TreeBuilder treeBuilder;
	private final List<Node> nodes;

	public ObjectFilter(String filter) {
		treeBuilder = new TreeBuilder();
		if ((null != filter) && !filter.isEmpty()) {
			final EtsiLexer el = new EtsiLexer(new ANTLRInputStream(filter));
			final CommonTokenStream tokens = new CommonTokenStream(el);
			final Etsifilter parser = new Etsifilter(tokens);
			parser.addParseListener(treeBuilder);
			parser.filterExpr();

			nodes = treeBuilder.getListNode();
		} else {
			nodes = new ArrayList<>();
		}
	}

	/**
	 * In fact this method could be more generic by taking an Object.
	 *
	 * @param subscriptionRepository
	 * @param nodes
	 * @return
	 */
	public boolean apply(Object object) {
		for (final Node node : nodes) {
			if (!apply(object, node)) {
				return false;
			}
		}

		return true;
	}

	private static boolean apply(Object _object, Node _node) {
		try {
			final String objectValue = BeanUtils.getNestedProperty(_object, _node.getName());
			return decide(objectValue, _node.getValue(), _node.getOp());
		} catch (final InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
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
