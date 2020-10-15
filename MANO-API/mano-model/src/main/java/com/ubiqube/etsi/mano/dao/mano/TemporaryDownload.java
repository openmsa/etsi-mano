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
package com.ubiqube.etsi.mano.dao.mano;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

@Entity
public class TemporaryDownload {
	@Id
	private String id;

	@NotNull
	private UUID objectId;

	@NotNull
	private ObjectType objectType;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationDate;

	public enum ObjectType {
		NSD("NSD"),
		VNFD("VNFD"),
		PNFD("PNFD");

		private final String value;

		ObjectType(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ObjectType fromValue(final String text) {
			for (final ObjectType b : ObjectType.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public @NotNull UUID getObjectId() {
		return objectId;
	}

	public void setObjectId(final UUID objectId) {
		this.objectId = objectId;
	}

	public ObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(final ObjectType objectType) {
		this.objectType = objectType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(final Date localDateTime) {
		this.expirationDate = localDateTime;
	}

}
