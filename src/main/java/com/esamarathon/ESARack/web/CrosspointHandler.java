package com.esamarathon.ESARack.web;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.esamarathon.ESARack.hardware.Crosspoint;
import com.esamarathon.ESARack.web.models.CrosspointModel;
import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

public class CrosspointHandler extends AbstractHandler {
	
	protected Crosspoint crosspoint;

	public CrosspointHandler(Crosspoint crosspoint) {
		this.logger = Logger.getLogger("API");
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
		public Object handle(Request request, Response response) {
			logger.entering(PostRoute.class.getName(), "handle");
			boolean answer = false;
			logger.log(Level.INFO, request.body());
			CrosspointModel model = new Gson().fromJson(request.body(), CrosspointModel.class);	
			logger.info("model: " + model.toString());
			try {
				if (model.resetTies != null && model.resetTies == true) {
					logger.log(Level.INFO, "Clearing ties.");
					crosspoint.clearAllTies();
				}
				if (model.tieModel != null) {
					logger.log(Level.INFO, "Updating ties.");
					crosspoint.setTies(model.tieModel);
				}
				if (model.preset != null) {
					logger.log(Level.INFO, "Changing to preset " + model.preset + ".");
					crosspoint.setPreset(model.preset);
					logger.log(Level.INFO, "Changed preset to " + model.preset);
				}
				
				answer = true;
			} catch (Exception e) {
				logger.severe("Exception in Crosspoint POST.");
				logger.severe(e.getMessage());
				e.printStackTrace();
			}
			
			logger.exiting(PostRoute.class.getName(), "handle");
			return answer;
		}
	}

}
