package com.ubiqube.etsi.mano.grammar;

import java.util.ArrayList;
import java.util.List;

import com.ubiqube.etsi.mano.grammar.EtsiFilter.AttrNameContext;
import com.ubiqube.etsi.mano.grammar.EtsiFilter.OpContext;
import com.ubiqube.etsi.mano.grammar.EtsiFilter.SimpleFilterExprContext;
import com.ubiqube.etsi.mano.grammar.EtsiFilter.ValueContext;
import com.ubiqube.etsi.mano.grammar.Node.Operand;

public class TreeBuilder extends EtsiFilterBaseListener {
	private Node currentNode;
	private final List<Node> listNode = new ArrayList<>();

	@Override
	public void exitOp(final OpContext ctx) {
		final Operand op = Operand.valueOf(ctx.getText().toUpperCase());
		currentNode.setOp(op);
		super.exitOp(ctx);
	}

	@Override
	public void enterSimpleFilterExpr(final SimpleFilterExprContext ctx) {
		currentNode = new Node();
		super.enterSimpleFilterExpr(ctx);
	}

	@Override
	public void exitValue(final ValueContext ctx) {
		currentNode.setValue(ctx.getText());
		super.exitValue(ctx);
	}

	@Override
	public void exitSimpleFilterExpr(final SimpleFilterExprContext ctx) {
		listNode.add(currentNode);
		currentNode = null;
	}

	@Override
	public void exitAttrName(final AttrNameContext ctx) {
		final String currentName = currentNode.getName();
		if (null == currentName) {
			currentNode.setName(ctx.getText());
		} else {
			currentNode.setName(currentNode.getName() + "." + ctx.getText());
		}
		super.exitAttrName(ctx);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("TreeBuilder [\n");
		for (final Node node : listNode) {
			sb.append("\t").append(node.toString()).append("\n");
		}
		sb.append("]\n");
		return sb.toString();
	}

	public List<Node> getListNode() {
		return listNode;
	}

}
