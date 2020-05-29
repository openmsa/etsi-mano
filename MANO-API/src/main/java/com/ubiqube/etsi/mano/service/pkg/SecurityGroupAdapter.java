package com.ubiqube.etsi.mano.service.pkg;

import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.SecurityGroup;

public class SecurityGroupAdapter {
	private SecurityGroup securityGroup;

	private List<String> targets;

	public SecurityGroupAdapter() {
		// Nothing.
	}

	public SecurityGroupAdapter(final SecurityGroup securityGroup, final List<String> targets) {
		super();
		this.securityGroup = securityGroup;
		this.targets = targets;
	}

	public SecurityGroup getSecurityGroup() {
		return securityGroup;
	}

	public void setSecurityGroup(final SecurityGroup securityGroup) {
		this.securityGroup = securityGroup;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}

}
