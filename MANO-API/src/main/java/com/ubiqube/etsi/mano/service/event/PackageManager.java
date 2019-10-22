package com.ubiqube.etsi.mano.service.event;

import javax.annotation.Nullable;

public interface PackageManager {

	@Nullable
	PackageProvider getProviderFor(byte[] data);

}
