package com.ubiqube.etsi.mano.utils;

/**
 * This class allows to handle the range from the HTTP request header.
 */
public class RangeHeader {

	/**
	 * The unit of the range.
	 */
	public enum Unit {
		BYTES;
	}

	private final Unit unit;

	private final long from;

	private Long to = null;

	/**
	 * RangeHeader class constructor.
	 *
	 * @param unit the unit of the range (e.g: BYTES)
	 * @param from the start value of the range
	 * @param to   the end value of the range
	 */
	public RangeHeader(Unit _unit, long _from, Long _to) {
		this.unit = _unit;
		this.from = _from;
		this.to = _to;
	}

	public RangeHeader(String range) {
		final String[] tokens = range.replace("Range: ", "").split("=");
		unit = Unit.valueOf(tokens[0].toUpperCase());
		final String[] fromTo = tokens[1].split("-");
		from = Long.parseLong(fromTo[0]);
		if (fromTo.length > 1) {
			to = Long.decode(fromTo[1]);
		}
	}

	/**
	 * Get range unit.
	 *
	 * @return the unit of the range (e.g: BYTES)
	 */
	public Unit getUnit() {

		return unit;
	}

	/**
	 * Get the start value of range.
	 *
	 * @return the start value of range.
	 */
	public long getFrom() {

		return from;
	}

	/**
	 * Get the end value of the range.
	 *
	 * @return the end value the range.
	 *
	 */
	public Long getTo() {
		return to;
	}

	/**
	 * Get the RangeHeader object instance.
	 *
	 * @param range the value of
	 * @return the RangeHeader object instantiated with range.
	 */
	public static RangeHeader fromValue(String range) {
		if (range == null) {
			return null;
		}
		final String[] tokens = range.replace("Range: ", "").split("=");
		final Unit unit = Unit.valueOf(tokens[0].toUpperCase());
		final String[] fromTo = tokens[1].split("-");
		final long from = Long.parseLong(fromTo[0]);
		final long to = Long.parseLong(fromTo[1]);
		return new RangeHeader(unit, from, to);
	}

	/**
	 * Get the String value of the RangeHeader object.
	 *
	 * @return the string value of the RangeHeader object
	 */
	@Override
	public String toString() {
		return String.format("Range: %s=%d-%d", unit.name().toLowerCase(), from, to);
	}

}
