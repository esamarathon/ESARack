package com.esamarathon.ESARack.hardware.enums;

import static com.esamarathon.ESARack.hardware.enums.OSSCCommand.*; 

public enum OSSCInput {
	AV1_RGBS(1, ONE),
	AV2_YPbPr(2, TWO),
	AV3_RGBHV(3, THREE),
	AV1_RGsB(4, FOUR),
	AV2_RGsB(5, FIVE),
	AV3_RGBS(6, SIX),
	AV1_YPbPr(7, SEVEN),
	AV3_RGsB(9, NINE),
	AV3_YPbPr(0, ZERO);
	
	private final int channel;
	private final OSSCCommand cmd;
	
	OSSCInput(int channel, OSSCCommand cmd) {
		this.channel = channel;
		this.cmd = cmd;
	}

	public int getChannel() {
		return this.channel;
	}
	
	public OSSCCommand getCommand() {
		return this.cmd;
	}

	public static OSSCInput fromString(String input) {
		switch(input.toLowerCase()) {
			case "av1_rgbs": return AV1_RGBS;
			case "av1_rgsb": return AV1_RGsB;
			case "av1_ypbpr": return AV1_YPbPr;
			case "av2_ypbpr": return AV2_YPbPr;
			case "av2_rgsb": return AV2_RGsB;
			case "av3_rgbhv": return AV3_RGBHV;
			case "av3_rgbs": return AV3_RGBS;
			case "av3_rgsb": return AV3_RGsB;
			case "av3_ypbpr": return AV3_YPbPr;
			default: return AV1_RGBS;
		}
	}
}
