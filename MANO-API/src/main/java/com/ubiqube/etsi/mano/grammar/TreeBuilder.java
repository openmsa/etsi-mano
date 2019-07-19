package com.ubiqube.etsi.mano.grammar;

import java.util.ArrayList;
import java.util.List;

import com.ubiqube.etsi.mano.grammar.Etsifilter.AttrNameContext;
import com.ubiqube.etsi.mano.grammar.Etsifilter.OpContext;
import com.ubiqube.etsi.mano.grammar.Etsifilter.SimpleFilterExprContext;
import com.ubiqube.etsi.mano.grammar.Etsifilter.ValueContext;
import com.ubiqube.etsi.mano.grammar.Node.Operand;

public class TreeBuilder extends EtsifilterBaseListener {
	private Node currentNode;
	private final List<Node> listNode = new ArrayList<>();

	@Override
	public void exitOp(OpContext ctx) {
		final Operand op = Operand.valueOf(ctx.getText().toUpperCase());
		currentNode.setOp(op);
		super.exitOp(ctx);
	}

	@Override
	public void enterSimpleFilterExpr(SimpleFilterExprContext ctx) {
		currentNode = new Node();
		super.enterSimpleFilterExpr(ctx);
	}

	@Override
	public void exitValue(ValueContext ctx) {
		currentNode.setValue(ctx.getText());
		super.exitValue(ctx);
	}

	@Override
	public void exitSimpleFilterExpr(SimpleFilterExprContext ctx) {
		listNode.add(currentNode);
		currentNode = null;
		super.exitSimpleFilterExpr(ctx);
	}

	@Override
	public void exitAttrName(AttrNameContext ctx) {
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
