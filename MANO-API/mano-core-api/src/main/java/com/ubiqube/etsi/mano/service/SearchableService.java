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
package com.ubiqube.etsi.mano.service;

import static com.ubiqube.etsi.mano.Constants.getSingleField;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.grammar.GrammarParser;
import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.repository.jpa.SearchQueryer;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SearchableService {
	private final ManoSearchResponseService searchService;

	private final EntityManager em;

	private final Class<?> clazz;
	private final GrammarParser grammarParser;

	public SearchableService(final ManoSearchResponseService searchService, final EntityManager em, final Class<?> clazz, final GrammarParser grammarParser) {
		super();
		this.searchService = searchService;
		this.em = em;
		this.clazz = clazz;
		this.grammarParser = grammarParser;
	}

	@Transactional
	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final String excludeDefaults, final Set<String> mandatoryFields, final Consumer<U> makeLink) {
		final String filter = getSingleField(requestParams, "filter");
		final List<?> result = queryDb(filter);
		return searchService.search(requestParams, clazz, excludeDefaults, mandatoryFields, result, clazz, makeLink);
	}

	private List<?> queryDb(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em, grammarParser);
		final List<Node<String>> nodes = grammarParser.parse(filter);
		return sq.getCriteria((List<Node<?>>) (Object) nodes, clazz);
	}

}
