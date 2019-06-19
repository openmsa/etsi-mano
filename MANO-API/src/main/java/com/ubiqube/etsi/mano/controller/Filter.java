package com.ubiqube.etsi.mano.controller;

/**
 * Store our simple filter informations.s
 * 
 * @author ovi@ubiube.com
 *
 */
public class Filter {
	/** Attribute name, in commons bean format. */
	private String attr;
	/** Operator. */
	private String op;
	/** Value. */
	private String value;

	public Filter(String _attr, String _op, String _value) {
		super();
		attr = _attr;
		op = _op;
		value = _value;
	}

	@Override
	public String toString() {
		return "Filter [attr=" + attr + ", op=" + op + ", value=" + value + "]";
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String _attr) {
		attr = _attr;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String _op) {
		op = _op;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String _value) {
		value = _value;
	}

}
