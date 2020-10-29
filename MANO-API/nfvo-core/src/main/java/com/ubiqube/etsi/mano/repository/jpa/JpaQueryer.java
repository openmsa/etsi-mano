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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.grammar.Node.Operand;

/**
 * Maybe more an Abstract.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class JpaQueryer {
	private final EntityManager em;

	public JpaQueryer(final EntityManager em) {
		super();
		this.em = em;
	}

	public Predicate getCriteria(final List<Node> nodes, final Class<?> clazz, final Map<String, From<?, ?>> joins) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final List<Predicate> predicates = new ArrayList<>();
		for (final Node node : nodes) {
			final Optional<Predicate> res = applyOp(node.getName(), node.getOp(), node.getValue(), joins);
			if (res.isPresent()) {
				predicates.add(res.get());
			}
		}
		if (!predicates.isEmpty()) {
			return cb.and(predicates.toArray(new Predicate[0]));
		}
		return null;
	}

	private Optional<Predicate> applyOp(final String name, final Operand op, final String value, final Map<String, From<?, ?>> joins) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final Attr attr = getParent(name, joins);
		Predicate pred = null;
		final From<?, ?> p = attr.parent.orElse(joins.get("ROOT"));
		switch (op) {
		case EQ:
			pred = cb.equal(p.get(attr.name), value);
			break;
		case NEQ:
			pred = cb.notEqual(p.get(attr.name), value);
			break;
		case GT:
			pred = cb.greaterThan(p.get(attr.name), value);
			break;
		case GTE:
			pred = cb.greaterThanOrEqualTo(p.get(attr.name), value);
			break;
		case LT:
			pred = cb.lessThan(p.get(attr.name), value);
			break;
		case LTE:
			pred = cb.lessThanOrEqualTo(p.get(attr.name), value);
			break;
		case CONT:
		case NCONT:
		case IN:
		case NIN:
		default:
			throw new GenericException("Unknown query Op: " + op);
		}
		return Optional.ofNullable(pred);
	}

	private Attr getParent(final String name, final Map<String, From<?, ?>> joins) {
		final String[] arr = name.split("\\/");
		final Attr attr = new Attr();
		if (arr.length > 1) {
			final String ro[] = new String[arr.length - 1];
			System.arraycopy(arr, 0, ro, 0, ro.length);
			final String key = Arrays.asList(ro).stream().collect(Collectors.joining("."));
			attr.parent = Optional.ofNullable(joins.get(key));
		}
		attr.name = arr[arr.length - 1];
		return attr;
	}

	private class Attr {
		private String name;
		private Optional<From<?, ?>> parent = Optional.empty();
	}
}
