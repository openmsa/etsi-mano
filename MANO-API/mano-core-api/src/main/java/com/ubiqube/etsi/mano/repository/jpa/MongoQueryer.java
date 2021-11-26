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
package com.ubiqube.etsi.mano.repository.jpa;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.grammar.AstBuilder;
import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.grammar.Node.Operand;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class MongoQueryer {

	public Query getCriteria(final List<Node<String>> nodes) {
		final Query query = new Query();
		final Criteria base = new Criteria();
		final List<Criteria> criterias = convertNodeList(nodes);
		if (!criterias.isEmpty()) {
			base.andOperator(criterias.toArray(new Criteria[criterias.size()]));
		}
		query.addCriteria(base);
		return query;
	}

	public Query getCriteria(final String filter) {
		final AstBuilder astBuilder = new AstBuilder(filter);
		return getCriteria(astBuilder.getNodes());
	}

	private List<Criteria> convertNodeList(final List<Node<String>> nodes) {
		return nodes.stream()
				.map(x -> applyOp(Criteria.where(x.getName()), x.getOp(), x.getValue()))
				.toList();
	}

	private Criteria applyOp(final Criteria crit, final Operand op, final String value) {
		switch (op) {
		case EQ:
			return crit.is(value);
		case NEQ:
			return crit.ne(value);
		case GT:
			return crit.gt(value);
		case GTE:
			return crit.gte(value);
		case LT:
			return crit.lt(value);
		case LTE:
			return crit.lte(value);
		case CONT:
			return crit.regex(".*" + escapeRegexp(value) + ".*");
		case NCONT:
			return crit.regex(".*" + escapeRegexp(value) + ".*").not();
		case IN, NIN:
		default:
			throw new GenericException("Unknown query Op: " + op);
		}
	}

	private String escapeRegexp(final String value) {
		// /[-\/\\^$*+?.()|[\]{}]/g, '\\$&'
		return Pattern.quote(value);
	}
}
