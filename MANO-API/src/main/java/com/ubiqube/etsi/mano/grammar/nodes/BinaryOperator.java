package com.ubiqube.etsi.mano.grammar.nodes;

import com.ubiqube.etsi.mano.grammar.Node.Operand;

public interface BinaryOperator<L, R> {

	L getLeft();

	Operand getOperand();

	R getRight();
}
