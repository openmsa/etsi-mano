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
package com.ubiqube.api.entities.orchestration;

public class ProcessTaskStatus {
	public enum ProcessStatusEnum {
		RUNNING("RUNNING"),
		WAITING("WAITING"),
		FAIL("FAIL"),
		WARNING("WARNING"),
		ENDED("ENDED"),
		NONE("NONE"),
		OK("OK"),
		TODO("TODO"),
		STANDBY("STANDBY");

		private final String value;

		ProcessStatusEnum(final String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static ProcessStatusEnum fromValue(final String v) {
			for (final ProcessStatusEnum b : ProcessStatusEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}

	}

	private String details;
	private String endingDate;
	private Object newParameters;
	private long order;
	private long processInstanceId;
	private String scriptName;
	private String startingDate;

	private ProcessStatusEnum status;

	public String getDetails() {
		return details;
	}

	public void setDetails(final String details) {
		this.details = details;
	}

	public String getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(final String endingDate) {
		this.endingDate = endingDate;
	}

	public Object getNewParameters() {
		return newParameters;
	}

	public void setNewParameters(final Object newParameters) {
		this.newParameters = newParameters;
	}

	public long getOrder() {
		return order;
	}

	public void setOrder(final long order) {
		this.order = order;
	}

	public long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(final long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(final String scriptName) {
		this.scriptName = scriptName;
	}

	public String getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(final String startingDate) {
		this.startingDate = startingDate;
	}

	public ProcessStatusEnum getStatus() {
		return status;
	}

	public void setStatus(final ProcessStatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProcessTaskStatus [details=" + details + ", endingDate=" + endingDate + ", newParameters=" + newParameters + ", order=" + order + ", processInstanceId=" + processInstanceId + ", scriptName=" + scriptName + ", startingDate=" + startingDate + ", status=" + status + "]";
	}

}
