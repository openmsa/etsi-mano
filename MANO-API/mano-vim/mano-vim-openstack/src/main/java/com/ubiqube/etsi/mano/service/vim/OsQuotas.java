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
package com.ubiqube.etsi.mano.service.vim;

public class OsQuotas implements ResourceQuota {

	private int floatingIpUsed;
	private int floatingIpMax;

	private int securityGroupsUsed;
	private int securityGroupsMax;
	private int securityGroupsFree;

	private long ramUsed;
	private long ramMax;

	private int keyPairsUsed;
	private int keyPairsMax;

	private int instanceUsed;
	private int instanceMax;

	private int vcpuUsed;
	private int vcpuMax;

	@Override
	public int getFloatingIpUsed() {
		return floatingIpUsed;
	}

	public void setFloatingIpUsed(final int floatingIpUsed) {
		this.floatingIpUsed = floatingIpUsed;
	}

	@Override
	public int getFloatingIpMax() {
		return floatingIpMax;
	}

	public void setFloatingIpMax(final int floatingIpMax) {
		this.floatingIpMax = floatingIpMax;
	}

	@Override
	public int getFloatingFree() {
		if (floatingIpMax == -1) {
			return Integer.MAX_VALUE;
		}
		return floatingIpMax - floatingIpUsed;
	}

	@Override
	public int getSecurityGroupsUsed() {
		return securityGroupsUsed;
	}

	public void setSecurityGroupsUsed(final int securityGroupsUsed) {
		this.securityGroupsUsed = securityGroupsUsed;
	}

	@Override
	public int getSecurityGroupsMax() {
		return securityGroupsMax;
	}

	public void setSecurityGroupsMax(final int securityGroupsMax) {
		this.securityGroupsMax = securityGroupsMax;
	}

	@Override
	public int getSecurityGroupsFree() {
		return securityGroupsFree;
	}

	public void setSecurityGroupsFree(final int securityGroupsFree) {
		this.securityGroupsFree = securityGroupsFree;
	}

	@Override
	public long getRamUsed() {
		return ramUsed;
	}

	public void setRamUsed(final long ramUsed) {
		this.ramUsed = ramUsed;
	}

	@Override
	public long getRamMax() {
		return ramMax;
	}

	public void setRamMax(final long ramMax) {
		this.ramMax = ramMax;
	}

	@Override
	public long getRamFree() {
		if (ramMax == -1) {
			return Long.MAX_VALUE;
		}
		return ramMax - ramUsed;
	}

	@Override
	public int getKeyPairsUsed() {
		return keyPairsUsed;
	}

	public void setKeyPairsUsed(final int keyPairsUsed) {
		this.keyPairsUsed = keyPairsUsed;
	}

	@Override
	public int getKeyPairsMax() {
		return keyPairsMax;
	}

	public void setKeyPairsMax(final int keyPairsMax) {
		this.keyPairsMax = keyPairsMax;
	}

	@Override
	public int getKeyPairsFree() {
		if (keyPairsMax == -1) {
			return Integer.MAX_VALUE;
		}
		return keyPairsMax - keyPairsUsed;
	}

	@Override
	public int getInstanceUsed() {
		return instanceUsed;
	}

	public void setInstanceUsed(final int instanceUsed) {
		this.instanceUsed = instanceUsed;
	}

	@Override
	public int getInstanceMax() {
		return instanceMax;
	}

	public void setInstanceMax(final int instanceMax) {
		this.instanceMax = instanceMax;
	}

	@Override
	public int getInstanceFree() {
		if (instanceMax == -1) {
			return Integer.MAX_VALUE;
		}
		return instanceMax - instanceUsed;
	}

	@Override
	public int getVcpuUsed() {
		return vcpuUsed;
	}

	public void setVcpuUsed(final int vcpuUsed) {
		this.vcpuUsed = vcpuUsed;
	}

	@Override
	public int getVcpuMax() {
		return vcpuMax;
	}

	public void setVcpuMax(final int vcpuMax) {
		this.vcpuMax = vcpuMax;
	}

	@Override
	public int getVcpuFree() {
		if (vcpuMax == -1) {
			return Integer.MAX_VALUE;
		}
		return vcpuMax - vcpuUsed;

	}

}
