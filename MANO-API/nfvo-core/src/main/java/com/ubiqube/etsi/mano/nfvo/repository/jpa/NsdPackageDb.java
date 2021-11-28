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
package com.ubiqube.etsi.mano.nfvo.repository.jpa;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.grammar.GrammarParser;
import com.ubiqube.etsi.mano.nfvo.jpa.NsdPackageJpa;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.jpa.AbstractDirectJpa;

@Service
public class NsdPackageDb extends AbstractDirectJpa<NsdPackage> implements NsdRepository {

	public NsdPackageDb(final EntityManager em, final NsdPackageJpa repository, final ContentManager contentManager, final ObjectMapper jsonMapper,
			final NamingStrategy namingStrategy, final GrammarParser grammarParser) {
		super(em, repository, contentManager, jsonMapper, namingStrategy, grammarParser);
	}

	@Override
	protected Class<NsdPackage> getFrontClass() {
		return NsdPackage.class;
	}

	@Override
	public void changeNsdUpdateState(final NsdPackage nsdPackage, final PackageUsageState state) {
		nsdPackage.setNsdUsageState(state);
		save(nsdPackage);
	}

}
