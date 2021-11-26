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
package com.ubiqube.etsi.mano.dao.mano.grant;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.dao.mano.Audit;
import com.ubiqube.etsi.mano.dao.mano.AuditListener;
import com.ubiqube.etsi.mano.dao.mano.Auditable;
import com.ubiqube.etsi.mano.dao.mano.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
@Entity
@EntityListeners(AuditListener.class)
public class PlacementConstraint implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@Embedded
	private Audit audit;

	private AffinityOrAntiAffinityEnum affinityOrAntiAffinity = null;

	private ScopeEnum scope = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<ConstraintResourceRef> resource = new ArrayList<>();

	private Boolean fallbackBestEffort = null;

	/**
	 * The scope of the placement constraint indicating the category of the
	 * \"place\" where the constraint applies. Permitted values: * NFVI_POP * ZONE *
	 * ZONE_GROUP * NFVI_NODE
	 */
	public enum ScopeEnum {
		NFVI_POP("NFVI_POP"),

		ZONE("ZONE"),

		ZONE_GROUP("ZONE_GROUP"),

		NFVI_NODE("NFVI_NODE");

		private final String value;

		ScopeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ScopeEnum fromValue(final String text) {
			for (final ScopeEnum b : ScopeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	/**
	 * The type of the constraint. Permitted values: * AFFINITY * ANTI_AFFINITY
	 */
	public enum AffinityOrAntiAffinityEnum {
		AFFINITY("AFFINITY"),

		ANTI_AFFINITY("ANTI_AFFINITY");

		private final String value;

		AffinityOrAntiAffinityEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static AffinityOrAntiAffinityEnum fromValue(final String text) {
			for (final AffinityOrAntiAffinityEnum b : AffinityOrAntiAffinityEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

}
