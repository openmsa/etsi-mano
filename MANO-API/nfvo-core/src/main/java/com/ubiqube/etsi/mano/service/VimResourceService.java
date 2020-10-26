package com.ubiqube.etsi.mano.service;

import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;

public interface VimResourceService {

	void allocate(Blueprint plan);

}
