package com.ubiqube.bean;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.model.vnf.sol005.PackageOperationalStateType;
import com.ubiqube.etsi.mano.model.vnf.sol005.PkgmSubscription;

public class SpelTest {

	@Test
	void testName() throws Exception {
		final PkgmSubscription subsJson = new PkgmSubscription();
		final SpelParserConfiguration config = new SpelParserConfiguration(true, true); // auto create objects if null
		final ExpressionParser parser = new SpelExpressionParser(config);
		final StandardEvaluationContext modelContext = new StandardEvaluationContext(subsJson);
		parser.parseExpression("filter.vnfProductsFromProviders[0].vnfProvider").setValue(modelContext, "XXXYYY1");
		parser.parseExpression("filter.vnfProductsFromProviders[0].operationalState").setValue(modelContext, PackageOperationalStateType.ENABLED);
		// parser.parseExpression("filter.vnfProductsFromProviders[0].operationalState[1]").setValue(modelContext,
		// "DISABLED");
		parser.parseExpression("filter.vnfProductsFromProviders[0].vnfPkgId[0]").setValue(modelContext, "1111111111111111111111111");
		parser.parseExpression("filter.vnfProductsFromProviders[0].vnfPkgId[1]").setValue(modelContext, "222222222222");
		// BeanUtils.setProperty(subsJson,
		// "filter.vnfProductsFromProviders.0.vnfProvider", "hello");
		// PropertyUtils.setNestedProperty(subsJson,
		// "filter.vnfProductsFromProviders.0.vnfProvider", "value");
		System.out.println("" + subsJson);
	}

	@Test
	void testName2() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		final byte[] value = Files.readAllBytes(Paths.get("src/test/resources", "Sol003LcmSubscription-req.json"));

		final PkgmSubscription object = mapper.readValue(value, PkgmSubscription.class);

	}
}
