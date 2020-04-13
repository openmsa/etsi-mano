package com.ubiqube.etsi.mano.dao.mano;

public class Qos {

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
