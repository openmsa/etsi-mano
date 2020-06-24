package com.ubiqube.etsi.mano.dao.mano.common;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Embeddable
public class FailureDetails implements Serializable {

	private static final Logger LOG = LoggerFactory.getLogger(FailureDetails.class);

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private String type;

	private String title;

	private long status;

	@Column(length = 500)
	private String detail;

	private String instance;

	public FailureDetails() {
		// Nothing.
	}

	public FailureDetails(final long _status, final String _detail) {
		try {
			instance = InetAddress.getLocalHost().getCanonicalHostName();
		} catch (final UnknownHostException e) {
			LOG.warn("", e);
		}
		status = _status;
		detail = _detail;
	}

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
