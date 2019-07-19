package com.ubiqube.bean;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.jupiter.api.Tag;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;

@Tag("Remote")
public class Test {

	@org.junit.Test
	public void testName() throws Exception {
		final Hashtable<String, String> props = new Hashtable<String, String>();

		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		props.put(Context.PROVIDER_URL, "jnp://10.30.18.103:1099");
		// create the InitialContext
		InitialContext context;
		try {
			context = new javax.naming.InitialContext(props);
			final OrchestrationService orchestrationService = (OrchestrationService) context.lookup("ubi-jentreprise/OrchestrationBean/remote-com.ubiqube.api.interfaces.orchestration.OrchestrationService");
			final Map<String, String> varsMap = new HashMap<>();
			final String processName = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
			final String serviceName = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/NS_Mgmt_Based_On_Heat";
			final String ubiqubeId = "TMAA6";
			final long serviceId = 0;
			final ProcessInstance resp = orchestrationService.scheduleServiceImmediateMode(ubiqubeId, serviceId, serviceName, processName, varsMap);
			System.out.println("================ " + resp);
		} catch (final NamingException e) {
			e.printStackTrace();
		}
	}

}
