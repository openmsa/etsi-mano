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
package org.mano.grammar.v1;

import java.util.ArrayList;
import java.util.List;

import com.mano.etsi.grammar.v1.EtsiFilter.AttrNameContext;
import com.mano.etsi.grammar.v1.EtsiFilter.OpContext;
import com.mano.etsi.grammar.v1.EtsiFilter.SimpleFilterExprContext;
import com.mano.etsi.grammar.v1.EtsiFilter.ValueContext;
import com.mano.etsi.grammar.v1.EtsiFilterBaseListener;
import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.grammar.Node.Operand;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class TreeBuilder extends EtsiFilterBaseListener {
	private Node<String> currentNode;
	private final List<Node<String>> listNode = new ArrayList<>();

	@Override
	public void exitOp(final OpContext ctx) {
		final Operand op = Operand.valueOf(ctx.getText().toUpperCase());
		currentNode.setOp(op);
		super.exitOp(ctx);
	}

	@Override
	public void enterSimpleFilterExpr(final SimpleFilterExprContext ctx) {
		currentNode = new Node<>();
		super.enterSimpleFilterExpr(ctx);
	}

	@Override
	public void exitValue(final ValueContext ctx) {
		currentNode.addValue(ctx.getText());
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
		for (final Node<String> node : listNode) {
			sb.append("\t").append(node.toString()).append("\n");
		}
		sb.append("]\n");
		return sb.toString();
	}

	public List<Node<String>> getListNode() {
		return listNode;
	}

}
