package com.esamarathon.ESARack.web;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.esamarathon.ESARack.hardware.IN1606;
import com.esamarathon.ESARack.web.models.IN1606Model;
import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

public class IN1606Handler extends AbstractHandler {
	
	protected IN1606 in1606;

	public IN1606Handler(IN1606 in1606) {
		this.logger = Logger.getLogger("API");
		this.in1606 = in1606;
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
			IN1606Model model = new IN1606Model();
			model.input = in1606.getInput();
			return model;
		}

	}
	
	private class PostRoute implements Route {

		@Override
		public Object handle(Request request, Response response) throws Exception {
			logger.log(Level.INFO, "Request to change settings of IN1606.");
			logger.log(Level.FINEST, request.body());
			IN1606Model model = new Gson().fromJson(request.body(), IN1606Model.class);
			logger.info("model: " + model.toString());
			if (model.input != null) {
				logger.log(Level.INFO, "Changing to input " + model.input + ".");
				in1606.setInput(model.input);
			}
			
			if (model.width != null) {
				logger.log(Level.INFO, "Changing to width " + model.width + ".");
				in1606.setWidth(model.width);
			}
			if (model.height != null) {
				logger.log(Level.INFO, "Changing to height " + model.height + ".");
				in1606.setHeight(model.height);
			}
			
			if (model.horizontalShift != null) {
				logger.log(Level.INFO, "Changing to horizontal shift " + model.horizontalShift + ".");
				in1606.setHorizontalShift(model.horizontalShift);
			}
			if (model.verticalShift != null) {
				logger.log(Level.INFO, "Changing to horizontal shift " + model.verticalShift + ".");
				in1606.setVerticalShift(model.verticalShift);
			}
			
			/* Not implemented yet.
			if (model.brightness != null) {
				in1606.setBrightness(model.brightness);
			}
			if (model.contrast != null) {
				in1606.setContrast(model.contrast);
			}
			*/
			
	
			return true;
		}

	}

}
