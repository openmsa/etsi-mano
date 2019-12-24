package com.ubiqube.etsi.mano.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.FilterAttributes;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.mapper.AttrHolder;
import com.ubiqube.etsi.mano.mapper.CollectNonNullListener;
import com.ubiqube.etsi.mano.mapper.JsonWalker;
import com.ubiqube.etsi.mano.mapper.SpelWriter;

@Service
public class SpelPatcher implements Patcher {

	private static final Logger LOG = LoggerFactory.getLogger(SpelPatcher.class);

	private final ObjectMapper mapper;
	private final JsonWalker jsonWalker;
	private final SpelWriter spelWriter;

	public SpelPatcher(final ObjectMapper _mapper, final JsonWalker _jsonWalker, final SpelWriter _spelWriter) {
		mapper = _mapper;
		jsonWalker = _jsonWalker;
		spelWriter = _spelWriter;
		LOG.info("SpelPatcher activated.");
	}

	@Override
	public void patch(final String _patchDocument, final Object _entity) {
		try {
			final CollectNonNullListener beanListener = new CollectNonNullListener();
			final JsonNode patch = mapper.readTree(_patchDocument);
			jsonWalker.walk(patch, beanListener);
			final List<AttrHolder> attrsHolders = beanListener.getAttrs();
			final List<FilterAttributes> attrs = spelWriter.getFilterAttrs(attrsHolders);
			patchAttrs(attrs, _entity);
		} catch (final IOException _e) {
			throw new GenericException(_e);
		}
	}

	private static void patchAttrs(final List<FilterAttributes> attrs, final Object _entity) {
		final SpelParserConfiguration config = new SpelParserConfiguration(true, true); // auto create objects if null
		final ExpressionParser parser = new SpelExpressionParser(config);
		final StandardEvaluationContext modelContext = new StandardEvaluationContext(_entity);
		LOG.debug("Patching attr: {}", attrs);
		attrs.forEach(x -> parser.parseExpression(x.getAttribute()).setValue(modelContext, x.getValue()));
	}

}
