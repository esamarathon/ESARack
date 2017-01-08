package com.esamarathon.ESARack.hardware;

public interface PowerSwitch {
	public void turnOn() throws Exception;
	public void turnOff() throws Exception;
}
