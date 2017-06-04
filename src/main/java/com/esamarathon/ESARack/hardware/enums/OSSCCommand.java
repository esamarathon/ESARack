package com.esamarathon.ESARack.hardware.enums;

import java.util.HashMap;
import java.util.Map;

public enum OSSCCommand {
	
	POWER("KEY_POWER"),
	UP("KEY_UP"),
	LEFT("KEY_LEFT"),
	RIGHT("KEY_RIGHT"),
	DOWN("KEY_DOWN"),
	ZERO("KEY_0"),
	ONE("KEY_1"),
	TWO("KEY_2"),
	THREE("KEY_3"),
	FOUR("KEY_4"),
	FIVE("KEY_5"),
	SIX("KEY_6"),
	SEVEN("KEY_7"),
	EIGHT("KEY_8"),
	NINE("KEY_9"),
	PRESET("KEY_10ORCOMMANDS"),
	MENU("KEY_MENU");
	
	private final String command;
	
	private OSSCCommand(String cmd) {
		command = cmd;
	}
	
	public String getCommand() {
		return command;
	}
	
	public static OSSCCommand getNumberKey(int key) {
		switch(key) {
		case 1: return ONE;
		case 2: return TWO;
		case 3: return THREE;
		case 4: return FOUR;
		case 5: return FIVE;
		case 6: return SIX;
		case 7: return SEVEN;
		case 8: return EIGHT;
		case 9: return NINE;
		default: return ZERO;
		}
	}
}
