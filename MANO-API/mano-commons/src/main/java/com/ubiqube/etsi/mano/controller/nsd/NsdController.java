package com.ubiqube.etsi.mano.controller.nsd;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;

public interface NsdController {

	List<NsdPackage> nsDescriptorsGet(String filter);

	void nsDescriptorsNsdInfoIdDelete(UUID id);

	NsdPackage nsDescriptorsNsdInfoIdGet(UUID id);

	byte[] nsDescriptorsNsdInfoIdNsdContentGet(UUID id);

	void nsDescriptorsNsdInfoIdNsdContentPut(UUID id, InputStream is);

	NsdPackage nsDescriptorsNsdInfoIdPatch(UUID id, String body);

	NsdPackage nsDescriptorsPost(Map<String, String> userDefinedData);

}