package com.ubiqube.etsi.mano.grammar.nodes;

import com.ubiqube.etsi.mano.grammar.Node.Operand;

public interface UnaryOperator<T> {

	T getValue();

	Operand getOperand();
}
