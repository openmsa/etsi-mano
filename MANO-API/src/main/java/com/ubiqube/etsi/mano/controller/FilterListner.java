package com.ubiqube.etsi.mano.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.ubiqube.etsi.mano.grammar.Etsifilter.AttrNameContext;
import com.ubiqube.etsi.mano.grammar.Etsifilter.OpContext;
import com.ubiqube.etsi.mano.grammar.Etsifilter.SimpleFilterExprContext;
import com.ubiqube.etsi.mano.grammar.Etsifilter.ValueContext;
import com.ubiqube.etsi.mano.grammar.EtsifilterBaseListener;

/**
 * Filter listner. For more information see .g4 files or 4.3.2 Attribute-based
 * filtering in ETSI files.
 *
 * @author ovi@ubiqube.com
 *
 */
public class FilterListner extends EtsifilterBaseListener {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(FilterListner.class);

	/** Final list of filter. */
	private final List<Filter> _filters = new ArrayList<>();

	private final Deque<String> _attr = new ArrayDeque<>();

	private String _attribute = null;

	private String _op;

	private String _value;

	@Override
	public void enterOp(OpContext _ctx) {
		_attribute = _attr.stream().map(String::valueOf).collect(Collectors.joining(", "));
		Joiner.on(".").join(_attr);
		_attr.clear();
		super.enterOp(_ctx);
	}

	@Override
	public void exitOp(OpContext _ctx) {
		_op = _ctx.getText();
		super.exitOp(_ctx);
	}

	@Override
	public void exitSimpleFilterExpr(SimpleFilterExprContext _ctx) {
		final Filter filter = new Filter(_attribute, _op, _value);
		_filters.add(filter);
		super.exitSimpleFilterExpr(_ctx);
	}

	@Override
	public void exitValue(ValueContext _ctx) {
		LOG.info("exitValue " + _ctx.getText());
		_value = _ctx.getText();
		super.exitValue(_ctx);
	}

	@Override
	public void exitAttrName(AttrNameContext _ctx) {
		_attr.push(_ctx.getText());
		super.exitAttrName(_ctx);
	}

	@Override
	public void visitErrorNode(ErrorNode _node) {
		LOG.info("visitErrorNode");
		super.visitErrorNode(_node);
	}

	public List<Filter> getFilters() {
		return _filters;
	}

}
