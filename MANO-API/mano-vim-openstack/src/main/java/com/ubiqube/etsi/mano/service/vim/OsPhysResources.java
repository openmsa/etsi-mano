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

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class OsPhysResources implements PhysResources {

	private int freeDisk;
	private int freeRam;
	private int leastAvailableDisk;
	private int memory;
	private int memoryUsed;
	private int runningVM;
	private int virtualCPU;
	private int virtualUsedCPU;

	@Override
	public int getFreeDisk() {
		return freeDisk;
	}

	public void setFreeDisk(final int freeDisk) {
		this.freeDisk = freeDisk;
	}

	@Override
	public int getFreeRam() {
		return freeRam;
	}

	public void setFreeRam(final int freeRam) {
		this.freeRam = freeRam;
	}

	@Override
	public int getLeastAvailableDisk() {
		return leastAvailableDisk;
	}

	public void setLeastAvailableDisk(final int leastAvailableDisk) {
		this.leastAvailableDisk = leastAvailableDisk;
	}

	@Override
	public int getMemory() {
		return memory;
	}

	public void setMemory(final int memory) {
		this.memory = memory;
	}

	@Override
	public int getMemoryUsed() {
		return memoryUsed;
	}

	public void setMemoryUsed(final int memoryUsed) {
		this.memoryUsed = memoryUsed;
	}

	@Override
	public int getRunningVM() {
		return runningVM;
	}

	public void setRunningVM(final int runningVM) {
		this.runningVM = runningVM;
	}

	@Override
	public int getVirtualCPU() {
		return virtualCPU;
	}

	public void setVirtualCPU(final int virtualCPU) {
		this.virtualCPU = virtualCPU;
	}

	@Override
	public int getVirtualUsedCPU() {
		return virtualUsedCPU;
	}

	public void setVirtualUsedCPU(final int virtualUsedCPU) {
		this.virtualUsedCPU = virtualUsedCPU;
	}

}
