package com.ubiqube.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.ubiqube.etsi.mano.service.RepositoryServiceRest;

public class RepositoryServiceRestTest {
	RepositoryServiceRest repositoryServiceRest = new RepositoryServiceRest();

	@Test
	public void testDoSearch() throws Exception {
		final List<String> res = repositoryServiceRest.doSearch("Process/ETSI-MANO", null);
		assertNotNull("The list must not be null.", res);
	}
}
