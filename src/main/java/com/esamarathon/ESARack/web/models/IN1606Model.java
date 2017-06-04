package com.esamarathon.ESARack.web.models;

import com.esamarathon.ESARack.web.util.JsonUtil;

public class IN1606Model {
	public String input;
	public Integer width;
	public Integer height;
	public Integer horizontalShift;
	public Integer verticalShift;
	public Integer contrast;
	public Integer brightness;
	
	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
}
