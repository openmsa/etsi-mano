package com.ubiqube.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.ubiqube.etsi.mano.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.PkgmNotificationsFilter;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.PkgmSubscription;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.VnfPkgInfo;

public class SpelTest {
	final ExpressionParser parser;

	public SpelTest() {
		final SpelParserConfiguration config = new SpelParserConfiguration(true, true); // auto create objects if null
		parser = new SpelExpressionParser(config);
	}

	@Test
	void testOnPkgSubscription() throws Exception {
		final PkgmSubscription subsJson = new PkgmSubscription();
		final StandardEvaluationContext modelContext = new StandardEvaluationContext(subsJson);
		parser.parseExpression("filter.vnfProductsFromProviders[0].vnfProvider").setValue(modelContext, "XXXYYY1");
		parser.parseExpression("filter.vnfProductsFromProviders[0].operationalState[0]").setValue(modelContext, PackageOperationalStateType.ENABLED);
		parser.parseExpression("filter.vnfProductsFromProviders[0].vnfPkgId[0]").setValue(modelContext, "1111111111111111111111111");
		parser.parseExpression("filter.vnfProductsFromProviders[0].vnfPkgId[1]").setValue(modelContext, "222222222222");
		final PkgmNotificationsFilter filter = subsJson.getFilter();
		assertEquals("XXXYYY1", filter.getVnfProductsFromProviders().get(0).getVnfProvider());
		assertEquals(PackageOperationalStateType.ENABLED, filter.getVnfProductsFromProviders().get(0).getOperationalState().get(0));
		assertEquals("1111111111111111111111111", filter.getVnfProductsFromProviders().get(0).getVnfPkgId().get(0));
		assertEquals("222222222222", filter.getVnfProductsFromProviders().get(0).getVnfPkgId().get(1));
	}

	@Test
	public void testHashmap() throws Exception {
		final VnfPkgInfo pkg = new VnfPkgInfo();
		final StandardEvaluationContext modelContext = new StandardEvaluationContext(pkg);
		parser.parseExpression("userDefinedData[vnfProvider]").setValue(modelContext, "XXXYYY1");
		assertEquals("XXXYYY1", pkg.getUserDefinedData().get("vnfProvider"));
	}
}
