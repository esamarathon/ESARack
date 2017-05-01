package com.esamarathon.ESARack.web;

import com.esamarathon.ESARack.hardware.VP50;
import com.esamarathon.ESARack.web.models.VP50Model;
import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

public class VP50Handler implements Handler {
	
	protected VP50 vp50;
	
	public VP50Handler(VP50 vp50) {
		this.vp50 = vp50;
	}
	
	public Route Get() {
		return new GetRoute();
	}
	
	public Route Post() {
		return new PostRoute();
	}
	
	private class GetRoute implements Route {
		@Override
		public Object handle(Request request, Response response) throws Exception {
			VP50Model model = new VP50Model();
			model.input = vp50.getInput();
			model.preset = vp50.getPreset();
			return model;
		}
	}
	
	private class PostRoute implements Route {
	
		@Override
		public Object handle(Request request, Response response) throws Exception {
			VP50Model model = new Gson().fromJson(request.body(), VP50Model.class);
			if (model.power != null) {
				if (model.power) {
					vp50.turnOn();
				} else {
					vp50.turnOff();
				}
			}
	
			if (model.preset != null)
				vp50.setPreset(model.preset);
			if (model.input != null)
				vp50.setInput(model.input);
	
			return true;
		}
	}

}
