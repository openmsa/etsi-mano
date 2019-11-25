package com.ubiqube.bean;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.factory.NsdFactories;
import com.ubiqube.etsi.mano.model.ProblemDetails;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOnboardingStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOperationalStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoOnboardingFailureDetails;
import com.ubiqube.etsi.mano.model.vnf.sol005.Checksum;
import com.ubiqube.etsi.mano.model.vnf.sol005.PackageOperationalStateType;
import com.ubiqube.etsi.mano.model.vnf.sol005.PackageUsageStateType;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageSoftwareImageInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageSoftwareImageInfo.ContainerFormatEnum;

public class TestFactory {

	public static NsDescriptorsNsdInfo createNsDescriptorsNsdInfo() {
		final NsDescriptorsNsdInfo nsd = NsdFactories.createNsDescriptorsNsdInfo();
		final List<String> nestedNsdInfoIds = new ArrayList<>();
		nestedNsdInfoIds.add("25dca365-ff1b-4204-a9ca-c3745e6d3244");
		nestedNsdInfoIds.add("52d993dc-7a50-46da-b30c-e8fb344ef140");
		nsd.setNestedNsdInfoIds(nestedNsdInfoIds);
		nsd.setPnfdInfoIds(nestedNsdInfoIds);
		nsd.setVnfPkgIds(nestedNsdInfoIds);
		nsd.setNsdOnboardingState(NsdOnboardingStateEnum.ONBOARDED);
		nsd.setNsdOperationalState(NsdOperationalStateEnum.ENABLED);
		nsd.setNsdUsageState(NsdUsageStateEnum.IN_USE);
		final NsDescriptorsNsdInfoOnboardingFailureDetails onboardingFailureDetails = createOnboardingFailureDetails();
		nsd.setOnboardingFailureDetails(onboardingFailureDetails);
		return nsd;
	}

	public static NsDescriptorsNsdInfoOnboardingFailureDetails createOnboardingFailureDetails() {
		final NsDescriptorsNsdInfoOnboardingFailureDetails fd = new NsDescriptorsNsdInfoOnboardingFailureDetails();
		fd.setDetail("detail");
		return fd;
	}

	public static VnfPackageSoftwareImageInfo createVnfPackagesVnfPkgInfoSoftwareImages() {
		final VnfPackageSoftwareImageInfo si = new VnfPackageSoftwareImageInfo();
		si.setChecksum(new Checksum().algorithm("SHA-512").hash("e7c22b994c59d9cf2b48e549b1e24666636045930d3da7c1acb299d1c3b7f931f94aae41edda2c2b207a36e10f8bcb8d45223e54878f5b316e7ce3b6bc019629"));
		si.setContainerFormat(ContainerFormatEnum.BARE);
		si.setCreatedAt(OffsetDateTime.now());
		si.setImagePath("/mnt/images/myimages.raw");
		si.setSize(12345);
		return si;
	}

	public static ProblemDetails createProblemDetails() {
		final ProblemDetails pd = new ProblemDetails();
		pd.setDetail("detail");
		pd.setInstance("instance");
		pd.setStatus(123);
		pd.setTitle("title");
		pd.setType("type");
		return pd;
	}

	public static NsdPackage createNsdPackage() {
		final NsdPackage nsdPackage = new NsdPackage();
		nsdPackage.setNsdOnboardingState(NsdOnboardingStateEnum.ONBOARDED);
		nsdPackage.setNsdOperationalState(PackageOperationalStateType.ENABLED);
		nsdPackage.setNsdUsageState(PackageUsageStateType.IN_USE);
		return nsdPackage;
	}
}
