/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.mano.geoms.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.model.SearchRequest;
import com.ubiqube.etsi.mano.model.SearchResult;
import com.ubiqube.mano.geoms.service.SearchService;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class SearchController {
	private final SearchService searchService;

	public SearchController(final SearchService _searchService) {
		searchService = _searchService;
	}

	@PostMapping(value = "/city", consumes = "application/json")
	public ResponseEntity<SearchResult> search(@RequestBody final SearchRequest sr) {
		final SearchResult srs = searchService.searchForCa(sr.getCountryCode(), sr.getCaElements());
		return ResponseEntity.ok(srs);
	}

}
