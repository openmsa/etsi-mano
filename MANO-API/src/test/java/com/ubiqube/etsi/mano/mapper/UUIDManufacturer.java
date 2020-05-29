package com.ubiqube.etsi.mano.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.typeManufacturers.StringTypeManufacturerImpl;

public class UUIDManufacturer extends StringTypeManufacturerImpl {

	private final List<String> list;

	public UUIDManufacturer() {
		list = new ArrayList<>();
		list.add("id");
		list.add("vnfInstanceId");
		list.add("grantId");
		list.add("resourceId");
		list.add("vduId");
		list.add("vimConnectionId");
		list.add("vimId");
		list.add("virtualLinkDescId");
		list.add("virtualStorageDescId");
		list.add("cpdId");
		list.add("extManagedVirtualLinkId");

	}

	@Override
	public String getType(final DataProviderStrategy strategy, final AttributeMetadata attributeMetadata, final Map genericTypesArgumentsMap) {
		if (list.contains(attributeMetadata.getAttributeName())) {
			return UUID.randomUUID().toString();
		}
		return super.getType(strategy, attributeMetadata, genericTypesArgumentsMap);
	}

}
