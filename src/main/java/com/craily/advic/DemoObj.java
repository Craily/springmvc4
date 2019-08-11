package com.craily.advic;

import java.io.Serializable;

public class DemoObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8528851491677234789L;
	private String id;
	private String name;
	private Long tempArr1;
	private String tempArr2;

	/**
	 * 
	 */
	public DemoObj() {
		super();
	}
	
	public DemoObj(Long tempArr1, String tempArr2) {
		this.tempArr1 = tempArr1;
		this.tempArr2 = tempArr2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTempArr1() {
		return tempArr1;
	}

	public void setTempArr1(Long tempArr1) {
		this.tempArr1 = tempArr1;
	}

	public String getTempArr2() {
		return tempArr2;
	}

	public void setTempArr2(String tempArr2) {
		this.tempArr2 = tempArr2;
	}

}
