package com.esamarathon.ESARack.web.models;

import com.esamarathon.ESARack.web.util.JsonUtil;

public class VP50Model {
	public Boolean power;
	public Integer preset;
	public String input;
	
	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
}
