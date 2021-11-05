package com.ubiqube.etsi.mano.test;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.LifecycleChangeNotificationsFilter.NotificationTypesEnum;

public class TestEnum {

	@Test
	void testName() throws Exception {
		// VnfIdentifierCreationNotification
		Enum.valueOf(NotificationTypesEnum.class, "VNFIDENTIFIERCREATIONNOTIFICATION");
	}
}
