package com.esamarathon.ESARack.web.models;

import com.esamarathon.ESARack.web.util.JsonUtil;

public class OSSCModel {
	public Boolean power;
	public String input;
	public Boolean interlacePassthrough;
	public Integer lineMultiplier;
	
	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
}
