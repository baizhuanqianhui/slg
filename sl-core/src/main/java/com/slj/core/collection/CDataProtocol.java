package com.slj.core.collection;

import java.util.LinkedHashMap;
import java.util.Map;

public class CDataProtocol extends LinkedHashMap {
	private static final long serialVersionUID = 1L;

	/**
	 * CDataProtocol의 이름
	 */
	protected String name = null;

	/**
	 * Option속성으로, Null값을 Initialize해서 Return해 준다.
	 */
	protected boolean nullToInitialize = false;

	protected CMetaData metaData;

	/**
	 * CDataProtocol constructor
	 * 
	 * @param arg0
	 * @param arg1
	 */
	public CDataProtocol(int arg0, float arg1) {
		super(arg0, arg1);
	}

	/**
	 * CDataProtocol constructor
	 * 
	 * @param arg0
	 */
	public CDataProtocol(int arg0) {
		super(arg0);
	}

	/**
	 * CDataProtocol constructor
	 */
	public CDataProtocol() {
		super();
	}

	/**
	 * CDataProtocol constructor
	 * 
	 * @param arg0
	 */
	public CDataProtocol(Map arg0) {
		super(arg0);
	}

	/**
	 * CDataProtocol constructor
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public CDataProtocol(int arg0, float arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	/**
	 * Returns the name.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * 이름을 설정한다.
	 * 
	 * @param name - The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * nullToInitialize에 대한 설정 여부를 확인한다. nullToInitialize의 역할은 기본적으로 false이나,
	 * true로 설정하면, null대신 설정되어 있는 default값을 return한다.
	 * 
	 * @return boolean
	 */
	public boolean isNullToInitialize() {
		return nullToInitialize;
	}

	/**
	 * nullToInitialize의 값을 설정한다. nullToInitialize의 역할은 기본적으로 false이나, true로
	 * 설정하면, null대신 설정되어 있는 default값을 return한다.
	 * 
	 * @param nullToInitialize - The nullToInitialize to set
	 */
	public void setNullToInitialize(boolean nullToInitialize) {
		this.nullToInitialize = nullToInitialize;
	}

	/**
	 * MetaData getter
	 * 
	 * @return CMetaData
	 */
	public CMetaData getMetaData() {
		return metaData;
	}

	/**
	 * MetaData setter
	 * 
	 * @param metaData - CMetaData
	 */
	public void setMetaData(CMetaData metaData) {
		this.metaData = metaData;
	}
}
