package com.esamarathon.ESARack.web;

import com.esamarathon.ESARack.hardware.OSSC;
import com.esamarathon.ESARack.web.models.OSSCModel;
import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

public class OSSCHandler implements Handler {
	
	protected OSSC ossc;
	
	public OSSCHandler(OSSC ossc) {
		this.ossc = ossc;
	}

	@Override
	public Route Get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Route Post() {
		return new PostRoute();
	}
	
	private class PostRoute implements Route {
		@Override
		public Object handle(Request request, Response response) throws Exception {
			OSSCModel model = new Gson().fromJson(request.body(), OSSCModel.class);
			if (model.power != null) {
				if (model.power) {
					ossc.turnOn();
				} else {
					ossc.turnOff();
				}
			}
			
			if (model.input != null)
				ossc.setInput(model.input);
			
			if (model.interlacePassthrough != null)
				ossc.setInterlacePassThrough(model.interlacePassthrough);
			
			return true;
		}
	}

}
