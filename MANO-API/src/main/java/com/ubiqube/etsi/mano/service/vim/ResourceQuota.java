package com.ubiqube.etsi.mano.service.vim;

public interface ResourceQuota {

	int getFloatingIpUsed();

	int getFloatingIpMax();

	int getFloatingFree();

	int getSecurityGroupsUsed();

	int getSecurityGroupsMax();

	int getSecurityGroupsFree();

	long getRamUsed();

	long getRamMax();

	long getRamFree();

	int getKeyPairsUsed();

	int getKeyPairsMax();

	int getKeyPairsFree();

	int getInstanceUsed();

	int getInstanceMax();

	int getInstanceFree();

	int getVcpuUsed();

	int getVcpuMax();

	int getVcpuFree();

}