package com.esamarathon.ESARack.hardware;

import java.io.IOException;
import java.util.List;

import com.esamarathon.ESARack.hardware.enums.CrosspointBindingType;
import com.esamarathon.ESARack.web.models.CrosspointTieModel;

public class Crosspoint extends Extron implements PresetSwitch {
	
	private int preset;

	/*
	 * (non-Javadoc)
	 * @see com.esamarathon.ESARack.hardware.PresetSwitch#setPreset(int)
	 * 
	 * Extron Crosspoint command to recall preset is int(dot)
	 */	
	
	@Override
	public void setPreset(int preset) throws IOException, InterruptedException {
		String sPreset = Integer.toString(preset);
		String command = sPreset + ".";
		
		this.ConnectAndSendCommandToExtronUnit(this.ip, this.port, command);
		this.preset = preset;
		
	}

	@Override
	public int getPreset() {
		
		return this.preset;
	}
	
	/*
	 *  Command 0*! clears all ties on Crosspoint.
	 *  This method does not need any input from user since it's a pure action.
	 */

	public void clearAllTies() throws IOException, InterruptedException {
		this.ConnectAndSendCommandToExtronUnit(this.ip, this.port, "0*!");
		
	}

	public void setTies(List<CrosspointTieModel> tieModel) throws IOException, InterruptedException {
		String in = null;
		String out = null;
		CrosspointBindingType bindingType = null;
		String command = null;
		
		for(CrosspointTieModel model : tieModel) {
			in = model.getInput();
			out = model.getOutput();
			bindingType = model.getType();
			
			command = in + "*" + out + bindingType;
			
			this.ConnectAndSendCommandToExtronUnit(this.ip, this.port, command);		
		}
		
	}
	
	
	
	// Some nice API to rewire the crosspoint and save presets.
	// This will take some considerations.
	
}
