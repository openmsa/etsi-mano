package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;

public class Qos implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private long latency;

	private long packetLossRatio;

	private long packetDelayVariation;

	public long getLatency() {
		return latency;
	}

	public void setLatency(final long latency) {
		this.latency = latency;
	}

	public long getPacketLossRatio() {
		return packetLossRatio;
	}

	public void setPacketLossRatio(final long packetLossRatio) {
		this.packetLossRatio = packetLossRatio;
	}

	public long getPacketDelayVariation() {
		return packetDelayVariation;
	}

	public void setPacketDelayVariation(final long packetDelayVariation) {
		this.packetDelayVariation = packetDelayVariation;
	}

}
