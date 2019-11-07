package com.ubiqube.etsi.mano.repository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

/**
 * Maybe we should rename this class ;)
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ClassPathConverter {
	private final Map<Class<?>, Path> path = new HashMap<>();

	public ClassPathConverter() {
		path.put(NsDescriptorsNsdInfo.class, Paths.get("nsd"));
		path.put(NsLcmOpOccsNsLcmOpOcc.class, Paths.get("nsd-lcm-op-occs"));
		path.put(NsInstance.class, Paths.get("nsd-instances"));

		path.put(VnfPkgInfo.class, Paths.get("vnf-packages"));
		path.put(VnfLcmOpOcc.class, Paths.get("vnf-lcm-op-occs"));
		path.put(VnfInstance.class, Paths.get("vnf-instances"));

		path.put(PnfDescriptorsPnfdInfo.class, Paths.get("pnfd"));

	}

	/**
	 *
	 * @param clazz
	 * @return Should be a Path ?
	 */
	public Path convert(final Class<?> clazz) {
		final Path ret = path.get(clazz);
		if (null == ret) {
			throw new GenericException("Class " + clazz + " doesn't have a path.");
		}
		return ret;
	}
}
