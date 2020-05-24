package com.ubiqube.etsi.mano.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedVirtualLink;

public interface VnfInstantiedVirtualLinkJpa extends CrudRepository<VnfInstantiedVirtualLink, UUID> {

	List<VnfInstantiedVirtualLink> findAllByVnfLcmOpOccs_VnfInstance(VnfInstance vnfInstance);
}
