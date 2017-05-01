package com.esamarathon.ESARack.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class Extron {

	protected Logger logger;
	protected String ip;
	protected int port;

	public boolean ConnectAndSendCommandToExtronUnit(String IP, int port, String command)
			throws IOException, InterruptedException {
		Socket pingSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			pingSocket = new Socket(IP, port);
			out = new PrintWriter(pingSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));

		} catch (IOException e) {
			logger.severe("Extron failed to connect.");
			return false;			
		}

		// Get copywright message
		char cbufCWMessage[] = new char[5000];
		in.read(cbufCWMessage);
		logger.fine(String.valueOf(cbufCWMessage));
		// Sleep to allow for Extron unit to catch up
		Thread.sleep(25);

		// Send command to Extron Unit and wait 100 ms for the unit to react
		// with a response
		out.println(command);
		Thread.sleep(100);

		// Read response and return it
		char cbufResponse[] = new char[5000];
		in.read(cbufResponse);
		logger.info(String.valueOf(cbufResponse));
		//
		out.close();
		in.close();
		pingSocket.close();
		
		return true;

	}

}
