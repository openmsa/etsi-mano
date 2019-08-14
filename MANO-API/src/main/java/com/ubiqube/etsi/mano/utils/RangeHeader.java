package com.ubiqube.etsi.mano.utils;

import com.ubiqube.etsi.mano.exception.NotAcceptableException;

/**
 * This class allows to handle the range from the HTTP request header.
 *
 * @see https://tools.ietf.org/html/rfc7233 It's a lot more complex :
 *
 *      <pre>
 * Additional examples, assuming a representation of length 10000:

   o  The final 500 bytes (byte offsets 9500-9999, inclusive):

        bytes=-500

   Or:

        bytes=9500-

   o  The first and last bytes only (bytes 0 and 9999):

        bytes=0-0,-1

   o  Other valid (but not canonical) specifications of the second 500
      bytes (byte offsets 500-999, inclusive):

        bytes=500-600,601-999
        bytes=500-700,601-999
 *      </pre>
 */
public class RangeHeader {

	/**
	 * The unit of the range.
	 */
	public enum Unit {
		BYTES;
	}

	private Unit unit = Unit.BYTES;

	private Integer from = null;
	private Integer to = null;

	/**
	 * RangeHeader class constructor.
	 *
	 * @param unit the unit of the range (e.g: BYTES)
	 * @param from the start value of the range
	 * @param to   the end value of the range
	 * @deprecated Use the string constructor. Have to be changed to private.
	 */
	@Deprecated
	public RangeHeader(final Unit _unit, final Integer _from, final Integer _to) {
		this.unit = _unit;
		this.from = _from;
		this.to = _to;
	}

	public RangeHeader(final String range) {
		final Uft uft = parse(range);
		unit = uft.uftUnit;
		from = uft.uftFrom;
		to = uft.uftTo;
	}

	private static class Uft {
		public Uft(final Unit unit, final Integer from, final Integer to) {
			uftUnit = unit;
			uftFrom = from;
			uftTo = to;
		}

		Unit uftUnit;
		Integer uftFrom;
		Integer uftTo;
	}

	private static Uft parse(final String range) {
		if (range == null) {
			return null;
		}

		final String[] tokens = range.replace("Range: ", "").split("=");
		final Unit unit = Unit.valueOf(tokens[0].toUpperCase());
		final String[] fromTo = tokens[1].split("-");
		Integer localFrom;
		Integer localTo = null;
		if (fromTo[0].isEmpty()) {
			localFrom = null;
		} else {
			localFrom = Integer.valueOf(fromTo[0]);
		}
		if (fromTo.length > 1) {
			localTo = Integer.decode(fromTo[1]);
		}
		return new Uft(unit, localFrom, localTo);
	}

	/**
	 * Get the RangeHeader object instance.
	 *
	 * @param range the value of
	 * @return the RangeHeader object instantiated with range.
	 */
	public static RangeHeader fromValue(final String range) {
		final Uft uft = parse(range);
		if (null == uft) {
			return null;
		}
		return new RangeHeader(uft.uftUnit, uft.uftFrom, uft.uftTo);
	}

	/**
	 * Get range unit.
	 *
	 * @return the unit of the range (e.g: BYTES)
	 * @deprecated use getValues.
	 */
	@Deprecated
	public Unit getUnit() {

		return unit;
	}

	/**
	 * Get the start value of range.
	 *
	 * @return the start value of range.
	 * @deprecated use getValues.
	 */
	@Deprecated
	public Integer getFrom() {
		return from;
	}

	/**
	 * Get the end value of the range.
	 *
	 * @return the end value the range.
	 * @deprecated use getValues.
	 */
	@Deprecated
	public Integer getTo() {
		return to;
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

	/**
	 * This method is server to Client range header.
	 *
	 * @param length The lenght of the complete file.
	 * @return A Range Header.
	 */
	public String getContentRange(final int length) {
		final StringBuilder sb = new StringBuilder("bytes ");
		sb.append(from).append('-').append(to);
		sb.append('/').append(length);
		return sb.toString();
	}

	public FromToBean getValues(final int length) {
		int finalFrom = 0;
		int finalTo = 0;

		if ((null == from) && (null == to)) {
			throw new NotAcceptableException("Range is not acceptable!");
		}
		if (null == from) {
			// In this case to mean read N last bytes
			finalFrom = length - to.intValue();
			finalTo = to.intValue();
		} else if (null == to) {
			finalFrom = from.intValue();
			finalTo = length;
		} else {
			finalFrom = from.intValue();
			finalTo = (to > length) ? length : to.intValue();
		}
		return new FromToBean(finalFrom, finalTo);
	}

	public static class FromToBean {
		FromToBean(final int _from, final int _to) {
			from = _from;
			to = _to;
		}

		public final int from;
		public final int to;
	}
}
