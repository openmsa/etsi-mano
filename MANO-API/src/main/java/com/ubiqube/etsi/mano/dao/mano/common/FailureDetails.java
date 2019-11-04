package com.ubiqube.etsi.mano.dao.mano.common;

import javax.persistence.Embeddable;

@Embeddable
public class FailureDetails {
	private String type;
	private String title;
	private Long status;
	private String detail;
	private String instance;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(final long status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(final String detail) {
		this.detail = detail;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(final String instance) {
		this.instance = instance;
	}

}
