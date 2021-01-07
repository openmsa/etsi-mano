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
package com.ubiqube.etsi.mano.service.vim.mon;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public enum ManoMetrics {
	// NFVO
	N_BYTE_INCOMING_SAP("n_byte_incoming_sap"),
	N_BYTE_OUTGOING_SAP("n_byte_outgoing_sap"),
	N_PACKET_INCOMING_SAP("n_packet_incoming_sap"),
	N_PACKET_OUTGOING_SAP("n_packet_outgoing_sap"),
	N_BYTE_INCOMING("n_byte_incoming"),
	N_BYTE_OUTGOING("n_byte_outgoing"),
	N_PACKET_INCOMING("n_packet_incoming"),
	N_PACKET_OUTGOING("n_packet_outgoing"),
	// VNF/VNFC
	V_CPU_USAGE_MEAN_VNF("v_cpu_usage_mean_vnf"),
	V_CPU_USAGE_PEAK_VNF("v_cpu_usage_peak_vnf"),
	V_MEMORY_USAGE_MEAN_VNF("v_memory_usage_mean_vnf"),
	V_MEMORY_USAGE_PEAK_VNF("v_memory_usage_peak_vnf"),
	V_DISK_USAGE_MEAN_VNF("v_disk_usage_mean_vnf"),
	V_DISK_USAGE_PEAK_VNF("v_disk_usage_peak_vnf"),
	BYTE_INCOMING_VNF_EXT_CP("byte_incoming_vnf_ext_cp"),
	BYTE_OUTGOING_VNF_EXT_CP("byte_outgoing_vnf_ext_cp"),
	PACKET_INCOMING_VNF_EXT_CP("packet_incoming_vnf_ext_cp"),
	PACKET_OUTGOING_VNF_EXT_CP("packet_outgoing_vnf_ext_cp"),;

	private String value;

	ManoMetrics(final String string) {
		value = string;
	}

	@Override
	public String toString() {
		return value;
	}
}
