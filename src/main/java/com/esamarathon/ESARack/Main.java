package com.esamarathon.ESARack;

import com.esamarathon.ESARack.hardware.*;
import com.esamarathon.ESARack.web.API;

import jssc.SerialPort;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World");
		VP50 vp50 = new VP50(new SerialPort("/dev/ttyUSB0"));
		OSSC ossc = new OSSC();
		Crosspoint crosspoint = new Crosspoint();
		IN1606 in1606 = new IN1606();
		
		API api = new API(vp50, ossc, crosspoint, in1606);
	}

}
