package com.ubiqube.etsi.mano.repository;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;

public interface NsdRepository extends CrudRepository<NsDescriptorsNsdInfo> {

	<T, U extends Class> T loadObject(@NotNull final String _id, final U t, final String _filename);
}
