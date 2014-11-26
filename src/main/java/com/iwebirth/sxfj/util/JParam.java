package com.iwebirth.sxfj.util;

public class JParam {
	public final static String DATA_CONNECT_OK = "DATA_CONNECT_OK";
	
	public final static int AIR_DATA_COMMAND = 0x01;
	public final static int AIR_CONNECT_COMMAND = 0X02;
	
	private String clearTime;
	private String clearPeriod;
	private String whitelistRegex;
	public String getClearTime() {
		return clearTime;
	}
	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
	}
	public String getClearPeriod() {
		return clearPeriod;
	}
	public void setClearPeriod(String clearPeriod) {
		this.clearPeriod = clearPeriod;
	}
	public String getWhitelistRegex() {
		return whitelistRegex;
	}
	public void setWhitelistRegex(String whitelistRegex) {
		this.whitelistRegex = whitelistRegex;
	}
	
}
