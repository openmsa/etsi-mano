package com.ubiqube.etsi.mano.jpa;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.NsVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;

public interface NsVirtualLinkJpa extends CrudRepository<NsVirtualLink, UUID> {

	Set<NsVirtualLink> findByNsdPackage(NsdPackage nsdPackage);
}
