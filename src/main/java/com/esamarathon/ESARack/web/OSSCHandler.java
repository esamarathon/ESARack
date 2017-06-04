package com.esamarathon.ESARack.web;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.esamarathon.ESARack.hardware.OSSC;
import com.esamarathon.ESARack.hardware.enums.OSSCInput;
import com.esamarathon.ESARack.web.models.OSSCModel;
import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

public class OSSCHandler extends AbstractHandler {
	
	protected OSSC ossc;
	
	public OSSCHandler(OSSC ossc) {
		this.logger = Logger.getLogger("API");
		this.ossc = ossc;
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
			OSSCModel model = new OSSCModel();
			model.input = ossc.getInput().toString().toLowerCase();
			model.interlacePassthrough = ossc.getInterlacePassThrough();
			model.lineMultiplier = ossc.getLineTripple() ? 3 : 2;
			return model;
		}
	}
	
	private class PostRoute implements Route {
		@Override
		public Object handle(Request request, Response response) throws Exception {
			logger.log(Level.INFO, "Request to change settings of IN1606.");
			logger.log(Level.FINEST, request.body());
			OSSCModel model = new Gson().fromJson(request.body(), OSSCModel.class);
			if (model.power != null) {
				if (model.power) {
					ossc.togglePower();
				} else {
					ossc.togglePower();
				}
			}
			
			if (model.input != null) {
				logger.log(Level.INFO, "Changing to input " + model.input + ".");
				ossc.setInput(OSSCInput.fromString(model.input));
			}
			
			if (model.interlacePassthrough != null) {
				logger.log(Level.INFO, (model.interlacePassthrough ? "Activating" : "Deactivating") + " interlace Passthrough.");
				ossc.setInterlacePassThrough(model.interlacePassthrough);
			}
			
			if (model.lineMultiplier != null) {
				logger.log(Level.INFO, "Changing to line multiplier " + model.lineMultiplier + ".");
				switch (model.lineMultiplier) {
				case 3:
					logger.log(Level.FINE, "Choose line multiplier 3");
					ossc.setLineTripple(true);
					break;
				default:
					logger.log(Level.FINE, "Choose line multiplier 2");
					ossc.setLineTripple(false);
					break;
				}
			}
			
			return true;
		}
	}

}
