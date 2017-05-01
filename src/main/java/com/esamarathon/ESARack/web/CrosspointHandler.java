package com.esamarathon.ESARack.web;

import com.esamarathon.ESARack.hardware.Crosspoint;
import com.esamarathon.ESARack.web.models.CrosspointModel;
import com.esamarathon.ESARack.web.models.VP50Model;
import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

public class CrosspointHandler implements Handler {
	
	protected Crosspoint crosspoint;

	public CrosspointHandler(Crosspoint crosspoint) {
		this.crosspoint = crosspoint;
	}

	@Override
	public Route Get() {
		return new GetRoute();
	}

	@Override
	public Route Post() {
		return new PostRoute();
	}
	
	private class GetRoute implements Route {

		@Override
		public Object handle(Request request, Response response) throws Exception {
			CrosspointModel model = new CrosspointModel();
			model.preset = crosspoint.getPreset();
			return model;
		}

	}
	
	private class PostRoute implements Route {
		

		@Override
		public Object handle(Request request, Response response) throws Exception {
			CrosspointModel model = new Gson().fromJson(request.body(), CrosspointModel.class);	
			if (model.resetTies == true) {
				crosspoint.clearAllTies();
			}
			if (model.tieModel != null) {
				crosspoint.setTies(model.tieModel);
			}
			if (model.preset != null) {
				crosspoint.setPreset(model.preset);				
			}
			return true;
		}
	}

}
