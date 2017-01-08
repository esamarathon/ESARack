package com.esamarathon.ESARack.hardware;

public class OSSC implements InputSwitch, PowerSwitch {

	@Override
	public void turnOn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInput(String input) {
		// TODO Auto-generated method stub

	}
	
	public boolean getInterlacePassThrough() {
		return false;
	}
	
	public void setInterlacePassThrough(boolean active) {
		
	}
	
	public boolean getLineTripple() {
		return false;
	}
	
	public void setLineTripple(boolean active) {
		
	}

}
