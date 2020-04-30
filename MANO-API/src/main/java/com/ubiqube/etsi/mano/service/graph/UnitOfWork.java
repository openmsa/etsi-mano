package com.ubiqube.etsi.mano.service.graph;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.vim.Vim;

public interface UnitOfWork extends Serializable {
	public enum UowType {
		VL("VL"),
		CP("CP"),
		EXTCP("EXTCP"),
		EXTVL("EXTVL"),
		EXTMANAGEDVL("EXTMANAGEDVL"),
		MONITORINGPARAM("MONITORINGPARAM"),
		COMPUTE("COMPUTE"),
		VSTORAGE("VSTORAGE");

		private String value;

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

	String getName();

	String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, Map<String, String> context);

	UowType getType();

	ResourceHandleEntity getResourceHandleEntity();

	String getToscaName();
}
