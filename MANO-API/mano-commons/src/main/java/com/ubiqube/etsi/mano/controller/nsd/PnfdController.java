package com.ubiqube.etsi.mano.controller.nsd;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;

public interface PnfdController {

	List<PnfDescriptor> pnfDescriptorsGet(String filter);

	void pnfDescriptorsPnfdInfoIdDelete(UUID id);

	PnfDescriptor pnfDescriptorsPnfdInfoIdGet(UUID id);

	PnfDescriptor pnfDescriptorsPost(Map<String, Object> userDefinedData);

}