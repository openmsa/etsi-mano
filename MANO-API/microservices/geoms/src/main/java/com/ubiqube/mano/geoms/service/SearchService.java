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
package com.ubiqube.mano.geoms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.model.CaPair;
import com.ubiqube.etsi.mano.model.SearchResult;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class SearchService {
	private final IndexManager indexManager;

	public SearchService(final IndexManager _indexManager) {
		indexManager = _indexManager;
	}

	public SearchResult searchForCa(final String countryCode, final List<CaPair> caElements) {
		final CaPair caPair = findCaByCaType(caElements, 3);
		try {
			return indexManager.search(countryCode, caPair.getCaValue());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static CaPair findCaByCaType(final List<CaPair> caElements, final int i) {
		return caElements.stream().filter(x -> x.getCaType() == i).findFirst().orElse(null);
	}

}
