package com.ubiqube.etsi.mano.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;

import org.junit.jupiter.api.Test;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.mapper.JsonWalker;
import com.ubiqube.etsi.mano.mapper.SpelWriter;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class SpelParserTest {
	private final DefaultMapperFactory mapperFactory;

	public SpelParserTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testComplexPatch() throws Exception {
		final ObjectMapper _mapper = new ObjectMapper();
		final FileInputStream fi = new FileInputStream("src/test/resources/json-patch01.json");
		final MapperFacade _mapperFacade = mapperFactory.getMapperFacade();
		final SpelWriter _spelWriter = new SpelWriter(_mapperFacade);
		final JsonWalker _jsonWalker = new JsonWalker(_mapper);
		final SpelPatcher sp = new SpelPatcher(_mapper, _jsonWalker, _spelWriter);
		final VnfPkgInfo vpi = new VnfPkgInfo();
		final byte[] bytes = StreamUtils.copyToByteArray(fi);
		sp.patch(new String(bytes), vpi);
		assertEquals("ENABLED", vpi.getOperationalState().toString());
	}
}
