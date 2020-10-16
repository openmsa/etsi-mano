/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.service.pkg.bean;

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
