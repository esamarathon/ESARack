package com.esamarathon.ESARack.hardware.enums;

public enum AutoImageType {
	Fill(1), Follow(2);
	
	private final int mode;
	
	AutoImageType(int mode) {
		this.mode = mode;
	}
	
	public int getMode() {
		return mode;
	}
}
