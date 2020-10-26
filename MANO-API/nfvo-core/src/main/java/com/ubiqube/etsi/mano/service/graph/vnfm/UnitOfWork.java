package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.io.Serializable;
import java.util.Map;

import org.jgrapht.ListenableGraph;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;
import com.ubiqube.etsi.mano.service.graph.UnitOfWorkBase;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork.UowType;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.Vim;

public interface UnitOfWork extends UnitOfWorkBase<UowType>, Serializable {
	public enum UowType {
		START("START"),
		END("END"),
		VL("VL"),
		CP("CP"),
		EXTCP("EXTCP"),
		EXTVL("EXTVL"),
		EXTMANAGEDVL("EXTMANAGEDVL"),
		MONITORINGPARAM("MONITORINGPARAM"),
		COMPUTE("COMPUTE"),
		VSTORAGE("VSTORAGE"),
		DNSZONE("DNSZONE"), DNSHOST("DNSHOST");

		private final String value;

		UowType(final String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static UowType fromValue(final String text) {
			for (final UowType b : UowType.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, Map<String, String> context);

	VnfInstantiatedBase getResourceHandleEntity();

	String rollback(VimConnectionInformation vimConnectionInformation, Vim vim, String resourceId, Map<String, String> context);

	void connect(ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, Map<String, UnitOfWork> cache);
}
