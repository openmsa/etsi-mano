/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.grammar.v25;

import java.util.ArrayList;
import java.util.List;

import com.mano.etsi.mano.grammar.v25.EtsiFilterV25.AttrNameContext;
import com.mano.etsi.mano.grammar.v25.EtsiFilterV25.FilterContext;
import com.mano.etsi.mano.grammar.v25.EtsiFilterV25.OpMultiContext;
import com.mano.etsi.mano.grammar.v25.EtsiFilterV25.OpOneContext;
import com.mano.etsi.mano.grammar.v25.EtsiFilterV25.SimpleFilterExprMultiContext;
import com.mano.etsi.mano.grammar.v25.EtsiFilterV25.SimpleFilterExprOneContext;
import com.mano.etsi.mano.grammar.v25.EtsiFilterV25.ValueContext;
import com.mano.etsi.mano.grammar.v25.EtsiFilterV25BaseListener;
import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.grammar.Node.Operand;

public class TreeBuilderV25 extends EtsiFilterV25BaseListener {
	private Node<String> currentNode;
	private final List<Node<String>> listNode = new ArrayList<>();

	public List<Node<String>> getListNode() {
		return listNode;
	}

	@Override
	public void enterSimpleFilterExprOne(final SimpleFilterExprOneContext ctx) {
		currentNode = new Node<>();
	}

	@Override
	public void enterSimpleFilterExprMulti(final SimpleFilterExprMultiContext ctx) {
		currentNode = new Node<>();
	}

	@Override
	public void exitSimpleFilterExprOne(final SimpleFilterExprOneContext ctx) {
		listNode.add(currentNode);
		currentNode = null;
	}

	@Override
	public void exitSimpleFilterExprMulti(final SimpleFilterExprMultiContext ctx) {
		listNode.add(currentNode);
		currentNode = null;
	}

	@Override
	public void exitFilter(final FilterContext ctx) {
		currentNode.addValue(ctx.getText());
	}

	@Override
	public void exitOpOne(final OpOneContext ctx) {
		final Operand op = Operand.valueOf(ctx.getText().toUpperCase());
		currentNode.setOp(op);
	}

	@Override
	public void exitOpMulti(final OpMultiContext ctx) {
		final Operand op = Operand.valueOf(ctx.getText().toUpperCase());
		currentNode.setOp(op);
	}

	@Override
	public void exitAttrName(final AttrNameContext ctx) {
		final String currentName = currentNode.getName();
		if (null == currentName) {
			currentNode.setName(ctx.getText());
		} else {
			currentNode.setName(currentNode.getName() + "." + ctx.getText());
		}
	}

	@Override
	public void exitValue(final ValueContext ctx) {
		currentNode.addValue(ctx.getText());
	}

}
