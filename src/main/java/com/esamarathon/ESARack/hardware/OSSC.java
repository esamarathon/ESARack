package com.esamarathon.ESARack.hardware;

import java.io.IOException;

public class OSSC implements InputSwitch, PowerSwitch {
	
	private String input;
	private boolean interlacePassthrough;
	private boolean lineTripple;
	
	public OSSC() {
		input = "AV1";
	}

	@Override
	public void turnOn() {
		callCommand(buildCommand("irsend PowerOn"));

	}

	@Override
	public void turnOff() {
		callCommand(buildCommand("irsend PowerOff"));
	}

	@Override
	public String getInput() {
		return input;
	}

	@Override
	public void setInput(String input) {
		this.input = input;
		callCommand(buildCommand("Input1"));
	}
	
	public boolean getInterlacePassThrough() {
		return interlacePassthrough;
	}
	
	public void setInterlacePassThrough(boolean active) {
		this.interlacePassthrough = active;
		callCommand(buildCommand("InterlacePassThrough" + (active? "On" : "Off")));
	}
	
	public boolean getLineTripple() {
		return lineTripple;
	}
	
	public void setLineTripple(boolean active) {
		this.lineTripple = active;
		callCommand(buildCommand("LineTripple" + (active? "On" : "Off")));
	}
	
	protected String buildCommand(String parameters) {
		return String.format("echo %s", parameters);
	}
	
	private void callCommand(String cmd) {
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			
		}
	}

}
