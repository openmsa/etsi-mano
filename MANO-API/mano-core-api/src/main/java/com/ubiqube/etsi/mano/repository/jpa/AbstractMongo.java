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
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.BaseEntity;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.CrudRepositoryNg;
import com.ubiqube.etsi.mano.repository.NamingStrategy;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <U>
 */
public abstract class AbstractMongo<U extends BaseEntity> extends AbstractBinaryRepository implements CrudRepositoryNg<U> {
	private final MongoTemplate mongoTemplate;
	private final MongoQueryer queryier = new MongoQueryer();

	protected AbstractMongo(final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy, final MongoTemplate mongoTemplate) {
		super(contentManager, jsonMapper, namingStrategy);
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public final U get(final UUID id) {
		final Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, getFrontClass());
	}

	@Override
	protected abstract Class<U> getFrontClass();

	@Override
	public final void delete(final UUID id) {
		final Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		mongoTemplate.remove(query, getFrontClass());
		super.delete(id);
	}

	@Override
	public final U save(final U entity) {
		final U res = mongoTemplate.save(entity);
		mkdir(res.getId());
		return res;
	}

	@Override
	public final List<U> query(final String filter) {
		final Query query = queryier.getCriteria(filter);
		return mongoTemplate.find(query, getFrontClass());
	}

}
