package com.ubiqube.etsi.mano.service.pkg;

import javax.annotation.Nullable;

public interface PackageManager {

	@Nullable
	PackageProvider getProviderFor(byte[] data);

}
