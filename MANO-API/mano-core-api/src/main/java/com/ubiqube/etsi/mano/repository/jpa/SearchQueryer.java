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
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.engine.search.query.dsl.SearchQuerySelectStep;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.common.EntityReference;
import org.hibernate.search.mapper.orm.search.loading.dsl.SearchLoadingOptionsStep;
import org.hibernate.search.mapper.orm.session.SearchSession;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.grammar.AstBuilder;
import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.grammar.Node.Operand;

public class SearchQueryer {

	private final EntityManager entityManager;

	public SearchQueryer(final EntityManager _entityManager) {
		super();
		this.entityManager = _entityManager;
	}

	public <T> List<T> getCriteria(final List<Node<?>> nodes, final Class<T> clazz) {
		final SearchSession session = Search.session(entityManager);
		final SearchQuerySelectStep<?, EntityReference, T, SearchLoadingOptionsStep, ?, ?> ss = session.search(clazz);
		final SearchPredicateFactory pf = session.scope(clazz).predicate();
		final List<SearchPredicate> sp = convertNodeList(nodes, pf);
		return ss.where(f -> f.bool(b -> {
			b.must(f.matchAll());
			for (final SearchPredicate predicate : sp) {
				b.must(predicate);
			}
		})).fetchAllHits();
	}

	public <T> List<T> getCriteria(final String filter, final Class<T> clazz) {
		final AstBuilder astBuilder = new AstBuilder(filter);
		return getCriteria((List<Node<?>>) (Object) astBuilder.getNodes(), clazz);
	}

	private static List<SearchPredicate> convertNodeList(final List<Node<?>> nodes, final SearchPredicateFactory pf) {
		return nodes.stream()
				.map(x -> applyOp(x.getName(), x.getOp(), x.getValue(), pf))
				.collect(Collectors.toList());
	}

	private static SearchPredicate applyOp(final String name, final Operand op, final Object value, final SearchPredicateFactory pf) {
		switch (op) {
		case EQ:
			return pf.match().field(name).matching(value).toPredicate();
		case NEQ:
			return pf.matchAll().except(pf.match().field(name).matching(value)).toPredicate();
		case GT:
			return pf.range().field(name).greaterThan(value).toPredicate();
		case GTE:
			return pf.range().field(name).atLeast(value).toPredicate();
		case LT:
			return pf.range().field(name).lessThan(value).toPredicate();
		case LTE:
			return pf.range().field(name).atMost(value).toPredicate();
		case CONT:
			return pf.match().field(name).matching(value).toPredicate();
		case NCONT:
			return pf.matchAll().except(pf.match().field(name).matching(value)).toPredicate();
		case IN:
		case NIN:
		default:
			throw new GenericException("Unknown query Op: " + op);
		}
	}
}
