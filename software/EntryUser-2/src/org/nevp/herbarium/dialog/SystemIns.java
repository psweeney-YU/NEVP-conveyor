package org.nevp.herbarium.dialog;

import org.nevp.herbarium.wsclient.System;

public class SystemIns{
	System system;
	String instituteName;
	public SystemIns(System system, String instituteName) {
		super();
		this.system = system;
		this.instituteName = instituteName;
	}
	public System getSystem() {
		return system;
	}
	public void setSystem(System system) {
		this.system = system;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	
}