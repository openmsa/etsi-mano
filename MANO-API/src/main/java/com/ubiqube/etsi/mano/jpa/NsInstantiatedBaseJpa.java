package com.ubiqube.etsi.mano.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;

public interface NsInstantiatedBaseJpa<T extends NsInstantiatedBase> extends CrudRepository<T, UUID> {
	@Query("SELECT nib from #{#entityName} nib\n" +
			"	left outer join NsLiveInstance nli  on nib.id = nli.nsInstantiatedBase \n" +
			"	where nli.nsInstance =?1")
	List<T> findByLiveInstanceOfVnfInstance(NsdInstance nsdInstance);
}
