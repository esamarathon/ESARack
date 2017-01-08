package com.esamarathon.ESARack.hardware;

public interface PresetSwitch {

	void setPreset(int preset) throws Exception;

	int getPreset() throws Exception;

}