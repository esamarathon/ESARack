package com.esamarathon.ESARack.hardware.enums;

import com.google.gson.annotations.SerializedName;

// When using Extron SIS commands, different ending chars are used to 
// specify which type of tie is being made.

public enum CrosspointBindingType {
	@SerializedName("All")
	ALL("!"),
	
	@SerializedName("Audio")
	AUDIO("$"), 
	
	@SerializedName("Video")
	VIDEO("&");
	
	String value;
	
	CrosspointBindingType(String bindingType) {
		value = bindingType;		
	}
	
	public String GetCrosspointBindingType() {
		return value;
	}

}
