package com.esamarathon.ESARack;

import java.util.logging.Logger;

import com.esamarathon.ESARack.hardware.*;
import com.esamarathon.ESARack.web.API;

import jssc.SerialPort;

public class Main {

	public static void main(String[] args) {
		Logger.getLogger(Main.class.getCanonicalName()).info("Starting server...");
		VP50 vp50 = new VP50(new SerialPort("/dev/ttyUSB0"));
		OSSC ossc = new OSSC();
		
		Crosspoint crosspoint = new Crosspoint("192.168.0.21", 23);
		IN1606 in1606 = new IN1606("192.168.0.20", 23);
		
		crosspoint.testConnection();
		in1606.testConnection();
		
		new API(vp50, ossc, crosspoint, in1606);
	}

}
