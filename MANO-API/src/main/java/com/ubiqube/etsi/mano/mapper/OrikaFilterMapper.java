package com.ubiqube.etsi.mano.mapper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.ubiqube.etsi.mano.dao.mano.FilterAttributes;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultConstructorObjectFactory;
import ma.glasnost.orika.metadata.Type;

public class OrikaFilterMapper extends BidirectionalConverter<Object, List<FilterAttributes>> {

	private static final Logger LOG = LoggerFactory.getLogger(OrikaFilterMapper.class);
	private final BeanWalker beanWalker;
	private final MapperFacade mapper;

	public OrikaFilterMapper(final BeanWalker _beanBeanWalker, final MapperFacade _mapper) {
		beanWalker = _beanBeanWalker;
		mapper = _mapper;
	}

	@Override
	public List<FilterAttributes> convertTo(final Object source, final Type<List<FilterAttributes>> _destinationType, final MappingContext mappingContext) {
		LOG.info("A to B");
		final CollectNonNullListener beanListener = new CollectNonNullListener();
		beanWalker.walk(source, beanListener);
		final List<AttrHolder> attrs = beanListener.getAttrs();
		final SpelWriter sw = new SpelWriter(mapper);
		return sw.getFilterAttrs(attrs);
	}

	@Override
	public Object convertFrom(final List<FilterAttributes> source, final Type<Object> _destinationType, final MappingContext mappingContext) {
		LOG.info("B to A => ");
		// Create an empty object.
		final DefaultConstructorObjectFactory objectFactory = new DefaultConstructorObjectFactory(_destinationType.getRawType());
		final Object ret = objectFactory.create(source, mappingContext);

		final SpelParserConfiguration config = new SpelParserConfiguration(true, true); // auto create objects if null
		final ExpressionParser parser = new SpelExpressionParser(config);
		final StandardEvaluationContext modelContext = new StandardEvaluationContext(ret);
		source.forEach(x -> parser.parseExpression(x.getAttribute()).setValue(modelContext, x.getValue()));
		return ret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = (prime * result) + ((beanWalker == null) ? 0 : beanWalker.hashCode());
		result = (prime * result) + ((mapper == null) ? 0 : mapper.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final OrikaFilterMapper other = (OrikaFilterMapper) obj;
		if (beanWalker == null) {
			if (other.beanWalker != null) {
				return false;
			}
		} else if (!beanWalker.equals(other.beanWalker)) {
			return false;
		}
		if (mapper == null) {
			if (other.mapper != null) {
				return false;
			}
		} else if (!mapper.equals(other.mapper)) {
			return false;
		}
		return true;
	}

}
