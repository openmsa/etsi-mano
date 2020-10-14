package com.ubiqube.etsi.mano.service.pkg;

public interface RegistryHandler {

	boolean isProcessable(final byte[] data);

	String getName();

	PackageProvider getNewInstance(final byte[] data);
}
