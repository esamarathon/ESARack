package com.esamarathon.ESARack.web.models;

import java.util.List;

import com.esamarathon.ESARack.web.util.JsonUtil;

public class CrosspointModel {
	public Integer preset;
	public List<CrosspointTieModel> tieModel;
	public Boolean resetTies;
	
	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
}
