package com.esamarathon.ESARack.hardware.enums;

public enum VP50Inputs {	
	Composite1(0x31), Composite2(0x32), SVideo1(0x33), SVideo2(0x34),
	Component1(0x35), Component2(0x36), Component3(0x37), RGBHV(0x37),
	HDMI1(0x38), HDMI2(0x39), HDMI3(0x3130), HDMI4(0x3131),
	SDI1(0x3132), SDI2(0x3134), Auto(0x3133);
	
	short value;
	
	VP50Inputs(short val) {
		value = val;
	}
	
	VP50Inputs(int val) {
		value = (short)val;
	}	
	
	public short GetControlValue() {
		return value;
	}
}
