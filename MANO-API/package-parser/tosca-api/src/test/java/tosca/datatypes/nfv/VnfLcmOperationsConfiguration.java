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
package tosca.datatypes.nfv;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

public class VnfLcmOperationsConfiguration {
	/**
	 * Configuration parameters for the OperateVnf operation
	 */
	@Valid
	@JsonProperty("operate")
	private VnfOperateOperationConfiguration operate;

	/**
	 * Configuration parameters for the ScaleVnfToLevel operation
	 */
	@Valid
	@JsonProperty("scale_to_level")
	private VnfScaleToLevelOperationConfiguration scaleToLevel;

	/**
	 * Configuration parameters for the ChangeCurrentVnfPackage operation
	 */
	@Valid
	@JsonProperty("change_current_package")
	private VnfChangeCurrentPackageOperationConfiguration changeCurrentPackage;

	/**
	 * Configuration parameters for the changeVnfFlavourOpConfig operation
	 */
	@Valid
	@JsonProperty("change_flavour")
	private VnfChangeFlavourOperationConfiguration changeFlavour;

	/**
	 * Configuration parameters for the HealVnf operation
	 */
	@Valid
	@JsonProperty("heal")
	private VnfHealOperationConfiguration heal;

	/**
	 * Configuration parameters for the ScaleVnf operation
	 */
	@Valid
	@JsonProperty("scale")
	private VnfScaleOperationConfiguration scale;

	/**
	 * Configuration parameters for the changeExtVnfConnectivityOpConfig operation
	 */
	@Valid
	@JsonProperty("change_ext_connectivity")
	private VnfChangeExtConnectivityOperationConfiguration changeExtConnectivity;

	/**
	 * Configuration parameters for the TerminateVnf operation
	 */
	@Valid
	@JsonProperty("terminate")
	private VnfTerminateOperationConfiguration terminate;

	/**
	 * Configuration parameters for the InstantiateVnf operation
	 */
	@Valid
	@JsonProperty("instantiate")
	private VnfInstantiateOperationConfiguration instantiate;

	/**
	 * Configuration parameters for the RevertToVnfSnapshot operation
	 */
	@Valid
	@JsonProperty("revert_to_snapshot")
	private VnfRevertToSnapshotOperationConfiguration revertToSnapshot;

	/**
	 * Configuration parameters for the CreateVnfSnapshot operation
	 */
	@Valid
	@JsonProperty("create_snapshot")
	private VnfCreateSnapshotOperationConfiguration createSnapshot;

	public VnfOperateOperationConfiguration getOperate() {
		return this.operate;
	}

	public void setOperate(final VnfOperateOperationConfiguration operate) {
		this.operate = operate;
	}

	public VnfScaleToLevelOperationConfiguration getScaleToLevel() {
		return this.scaleToLevel;
	}

	public void setScaleToLevel(final VnfScaleToLevelOperationConfiguration scaleToLevel) {
		this.scaleToLevel = scaleToLevel;
	}

	public VnfChangeCurrentPackageOperationConfiguration getChangeCurrentPackage() {
		return this.changeCurrentPackage;
	}

	public void setChangeCurrentPackage(
			final VnfChangeCurrentPackageOperationConfiguration changeCurrentPackage) {
		this.changeCurrentPackage = changeCurrentPackage;
	}

	public VnfChangeFlavourOperationConfiguration getChangeFlavour() {
		return this.changeFlavour;
	}

	public void setChangeFlavour(final VnfChangeFlavourOperationConfiguration changeFlavour) {
		this.changeFlavour = changeFlavour;
	}

	public VnfHealOperationConfiguration getHeal() {
		return this.heal;
	}

	public void setHeal(final VnfHealOperationConfiguration heal) {
		this.heal = heal;
	}

	public VnfScaleOperationConfiguration getScale() {
		return this.scale;
	}

	public void setScale(final VnfScaleOperationConfiguration scale) {
		this.scale = scale;
	}

	public VnfChangeExtConnectivityOperationConfiguration getChangeExtConnectivity() {
		return this.changeExtConnectivity;
	}

	public void setChangeExtConnectivity(
			final VnfChangeExtConnectivityOperationConfiguration changeExtConnectivity) {
		this.changeExtConnectivity = changeExtConnectivity;
	}

	public VnfTerminateOperationConfiguration getTerminate() {
		return this.terminate;
	}

	public void setTerminate(final VnfTerminateOperationConfiguration terminate) {
		this.terminate = terminate;
	}

	public VnfInstantiateOperationConfiguration getInstantiate() {
		return this.instantiate;
	}

	public void setInstantiate(final VnfInstantiateOperationConfiguration instantiate) {
		this.instantiate = instantiate;
	}

	public VnfRevertToSnapshotOperationConfiguration getRevertToSnapshot() {
		return this.revertToSnapshot;
	}

	public void setRevertToSnapshot(final VnfRevertToSnapshotOperationConfiguration revertToSnapshot) {
		this.revertToSnapshot = revertToSnapshot;
	}

	public VnfCreateSnapshotOperationConfiguration getCreateSnapshot() {
		return this.createSnapshot;
	}

	public void setCreateSnapshot(final VnfCreateSnapshotOperationConfiguration createSnapshot) {
		this.createSnapshot = createSnapshot;
	}

}
