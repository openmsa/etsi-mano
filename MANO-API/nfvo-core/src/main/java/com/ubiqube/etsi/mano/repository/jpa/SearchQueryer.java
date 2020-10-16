package com.ubiqube.etsi.mano.repository.jpa;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;

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

	public Query getCriteria(final List<Node> nodes, final Class<?> clazz) {
		final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(clazz)
				.get();
		final BooleanJunction<BooleanJunction> bj = queryBuilder.bool();
		convertNodeList(nodes, queryBuilder, bj);
		final FullTextQuery jpaQuery;
		if (nodes.isEmpty()) {
			jpaQuery = fullTextEntityManager.createFullTextQuery(queryBuilder.all().createQuery(), clazz);
		} else {
			jpaQuery = fullTextEntityManager.createFullTextQuery(bj.createQuery(), clazz);
		}

		return jpaQuery;
	}

	public Query getCriteria(final String filter, final Class<?> clazz) {
		final AstBuilder astBuilder = new AstBuilder(filter);
		return getCriteria(astBuilder.getNodes(), clazz);
	}

	private static List<BooleanJunction> convertNodeList(final List<Node> nodes, final QueryBuilder qb, final BooleanJunction bj) {
		return nodes.stream()
				.map(x -> applyOp(x.getName(), x.getOp(), x.getValue(), qb, bj))
				.collect(Collectors.toList());
	}

	private static BooleanJunction applyOp(final String name, final Operand op, final String value, final QueryBuilder qb, final BooleanJunction bj) {
		switch (op) {
		case EQ:
			return bj.must(qb.keyword().onField(name).matching(value).createQuery());
		case NEQ:
			return bj.must(qb.keyword().onField(name).matching(value).createQuery()).not();
		case GT:
			return bj.must(qb.range().onField(name).above(value).createQuery());
		case GTE:
			return bj.must(qb.range().onField(name).above(value).createQuery());
		case LT:
			return bj.must(qb.range().onField(name).below(value).createQuery());
		case LTE:
			return bj.must(qb.range().onField(name).below(value).createQuery());
		case CONT:
			return bj.must(qb.keyword().onField(name).matching(value).createQuery());
		case NCONT:
			return bj.must(qb.keyword().onField(name).matching(value).createQuery()).not();
		case IN:
		case NIN:
		default:
			throw new GenericException("Unknown query Op: " + op);
		}
	}
}
