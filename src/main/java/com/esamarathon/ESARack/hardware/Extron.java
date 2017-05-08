package com.esamarathon.ESARack.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Extron {
	
	protected Logger logger;
	protected String ip;
	protected int port;
	
	
	public String ConnectAndSendCommand(String command) throws IOException, InterruptedException {
		return ConnectAndSendCommandToExtronUnit(this.ip, this.port, command);
	}

	public String ConnectAndSendCommandToExtronUnit(String IP, int port, String command)
			throws IOException, InterruptedException {
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			socket = createSocket();//new Socket(IP, port);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (IOException e) {
			logger.severe("Extron failed to connect.");
			return null;			
		}

		readCopyrightMessage(in);

		// Send command to Extron Unit and wait 100 ms for the unit to react
		// with a response
		out.print(command);
		Thread.sleep(100);

		// Read response and return it
		char cbufResponse[] = new char[5000];
		in.read(cbufResponse);
		logger.info(String.valueOf(cbufResponse));
		//
		out.close();
		in.close();
		socket.close();
		
		return new String(cbufResponse).trim();

	}
	
	// Reads the copyright message sent from Extron unit and discards it.
	protected void readCopyrightMessage(Reader in) throws IOException, InterruptedException {
		
		char cbufCWMessage[] = new char[5000];
		in.read(cbufCWMessage);
		logger.fine(String.valueOf(cbufCWMessage));
		// Sleep to allow for Extron unit to catch up
		Thread.sleep(25);
	}
	
	protected Socket createSocket() throws UnknownHostException, IOException {
		return new Socket(this.ip, this.port);
	}

}
