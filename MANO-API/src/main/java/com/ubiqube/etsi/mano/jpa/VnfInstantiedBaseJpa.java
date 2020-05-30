package com.ubiqube.etsi.mano.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;

public interface VnfInstantiedBaseJpa<T extends VnfInstantiatedBase> extends CrudRepository<T, UUID> {

	@Query("SELECT vib from #{#entityName} vib\n" +
			"	left outer join VnfLiveInstance vli  on vib.id = vli.vnfInstantiatedBase \n" +
			"	where vli.vnfInstance =?1")
	List<T> findByLiveInstanceOfVnfInstance(VnfInstance vnfInstance);
}
