package com.esamarathon.ESARack.hardware.enums;

// When using Extron SIS commands, different ending chars are used to 
// specify which type of tie is being made.

public enum CrosspointBindingType {
	ALL("!"), AUDIO("$"), VIDEO("&");
	
	String value;
	
	CrosspointBindingType(String bindingType) {
		value = bindingType;		
	}
	
	public String GetCrosspointBindingType() {
		return value;
	}

}
