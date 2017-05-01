package com.esamarathon.ESARack.web.models;

import com.esamarathon.ESARack.hardware.enums.CrosspointBindingType;

public class CrosspointTieModel {

	private String input;
	private String output;
	private CrosspointBindingType type;
	
	
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public CrosspointBindingType getType() {
		return type;
	}
	public void setType(CrosspointBindingType type) {
		this.type = type;
	}
	
	
}
