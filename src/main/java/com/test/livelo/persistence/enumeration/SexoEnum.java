package com.test.livelo.persistence.enumeration;

public enum SexoEnum {

	M("M"), F("F");

	private String desc;

	private SexoEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

}
