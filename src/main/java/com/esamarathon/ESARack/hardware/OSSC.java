package com.esamarathon.ESARack.hardware;

import java.io.IOException;
import java.util.logging.Logger;

import com.esamarathon.ESARack.hardware.enums.OSSCCommand;
import static com.esamarathon.ESARack.hardware.enums.OSSCCommand.*;
import com.esamarathon.ESARack.hardware.enums.OSSCInput;

public class OSSC implements PresetSwitch {
	
	private Logger logger;
	private OSSCInput input;
	private boolean interlacePassthrough;
	private boolean lineTripple;
	private int preset;
	
	public OSSC() {
		logger = Logger.getLogger("OSSC");
		input = OSSCInput.AV1_RGBS;
	}

	public void togglePower() {
		callCommand(buildCommand(POWER));

	}

	public OSSCInput getInput() {
		return input;
	}

	public void setInput(OSSCInput input) {
		this.input = input;
		callCommand(buildCommand(input.getCommand()));
	}
	
	@Override
	public int getPreset() throws Exception {
		return preset;
	}
	
	@Override
	public void setPreset(int preset) {
		callCommand(buildCommand(PRESET));
		callCommand(buildCommandRaw("KEY_"+preset));
		this.preset = preset;
		this.interlacePassthrough = true;
		
		switch (preset) {
		case 0: this.lineTripple = true; break;
		case 1: this.lineTripple = false; break;
		}
	}

	public boolean getInterlacePassThrough() {
		return interlacePassthrough;
	}
	
	public void setInterlacePassThrough(boolean active) {
		if (this.interlacePassthrough != active) {
			callCommand(buildCommand(MENU));
			callCommand(buildCommand(DOWN));
			callCommand(buildCommand(DOWN));
			if (this.interlacePassthrough) {
				callCommand(buildCommand(RIGHT));
			} else {
				callCommand(buildCommand(LEFT));
			}
			callCommand(buildCommand(UP));
			callCommand(buildCommand(UP));
			callCommand(buildCommand(MENU));
			this.interlacePassthrough = active;
		}
	}
	
	public boolean getLineTripple() {
		return lineTripple;
	}
	
	public void setLineTripple(boolean active) {
		if (active != this.lineTripple) {
			callCommand(buildCommand(MENU));
			if (active) {
				callCommand(buildCommand(RIGHT));
			} else {
				callCommand(buildCommand(LEFT));
			}
			callCommand(buildCommand(MENU));
			this.lineTripple = active;
		}
	}
	
	private String buildCommandRaw(String cmd) {
		return String.format("irsend send_once OSSC %s", cmd);
	}
	
	protected String buildCommand(OSSCCommand cmd) {
		return buildCommandRaw(cmd.getCommand());
	}
	
	private void callCommand(String cmd) {
		try {
			Runtime.getRuntime().exec(cmd);
			Thread.sleep(50);
		} catch (IOException e) {
			logger.severe(e.getMessage());
		} catch (InterruptedException e) {
			
		}
	}

}
