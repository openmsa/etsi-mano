/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.grammar.v25;

import java.util.ArrayList;
import java.util.List;

import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.grammar.Node.Operand;
import com.ubiqube.etsi.mano.grammar.v25.EtsiFilterV25.AttrNameContext;
import com.ubiqube.etsi.mano.grammar.v25.EtsiFilterV25.FilterContext;
import com.ubiqube.etsi.mano.grammar.v25.EtsiFilterV25.OpMultiContext;
import com.ubiqube.etsi.mano.grammar.v25.EtsiFilterV25.OpOneContext;
import com.ubiqube.etsi.mano.grammar.v25.EtsiFilterV25.SimpleFilterExprMultiContext;
import com.ubiqube.etsi.mano.grammar.v25.EtsiFilterV25.SimpleFilterExprOneContext;
import com.ubiqube.etsi.mano.grammar.v25.EtsiFilterV25.ValueContext;

public class TreeBuilderV25 extends EtsiFilterV25BaseListener {
	private Node currentNode;
	private final List<Node> listNode = new ArrayList<>();

	public List<Node> getListNode() {
		return listNode;
	}

	@Override
	public void enterSimpleFilterExprOne(final SimpleFilterExprOneContext ctx) {
		currentNode = new Node();
	}

	@Override
	public void enterSimpleFilterExprMulti(final SimpleFilterExprMultiContext ctx) {
		currentNode = new Node();
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
