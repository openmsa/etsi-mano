package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.graph.UnitOfWorkBase;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsUnitOfWork.NsUowType;
import com.ubiqube.etsi.mano.service.vim.Vim;

public interface NsUnitOfWork extends UnitOfWorkBase<NsUowType>, Serializable {
	public enum NsUowType {
		NSD("NSD"),
		VNF("VNF"),
		PNF("PNF"),
		SAP("SAP"),
		NSVL("NSVL"),
		VNFFG("VNFFG");

		private String value;

		NsUowType(final String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static NsUowType fromValue(final String text) {
			for (final NsUowType b : NsUowType.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	String exec(final VimConnectionInformation vimConnectionInformation, VnfmInterface vnfm, Vim vim, Map<String, String> context);

	NsInstantiatedBase getResourceHandleEntity();

	String rollback(VimConnectionInformation vimConnectionInformation, VnfmInterface vnfm, Vim vim, String resourceId, Map<String, String> context);

}
