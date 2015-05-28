package com.kornuit.core.security;

import java.util.ArrayList;
import java.util.Date;

public class SecurityLog {

	private String remoteAddress;
	private String remoteHost;
	private int remotePort;
	
	private Date lastVisit = new Date();
	
	private boolean isBanned = false;
	private SecurityLog masterRecord;
	private ArrayList<SecurityLog> relatedRecords;
	
	
	public String getRemoteAddress() {
		return remoteAddress;
	}
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	public String getRemoteHost() {
		return remoteHost;
	}
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
	public int getRemotePort() {
		return remotePort;
	}
	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}
	public boolean isBanned() {
		return isBanned;
	}
	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	public SecurityLog getMasterRecord() {
		return masterRecord;
	}
	public void setMasterRecord(SecurityLog masterRecord) {
		this.masterRecord = masterRecord;
	}
	public ArrayList<SecurityLog> getRelatedRecords() {
		return relatedRecords;
	}
	public void setRelatedRecords(ArrayList<SecurityLog> relatedRecords) {
		this.relatedRecords = relatedRecords;
	}
	public SecurityLog(String remoteAddress, String remoteHost, int remotePort,
			boolean isBanned, SecurityLog masterRecord,
			ArrayList<SecurityLog> relatedRecords) {
		super();
		this.remoteAddress = remoteAddress;
		this.remoteHost = remoteHost;
		this.remotePort = remotePort;
		this.isBanned = isBanned;
		this.masterRecord = masterRecord;
		this.relatedRecords = relatedRecords;
	}
	public Date getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}
	
}
