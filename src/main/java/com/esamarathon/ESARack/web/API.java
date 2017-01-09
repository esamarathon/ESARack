package com.esamarathon.ESARack.web;

import static spark.Spark.*;
import static com.esamarathon.ESARack.web.util.JsonUtil.json;

import spark.Request;
import spark.Response;
import spark.Route;

import com.esamarathon.ESARack.hardware.VP50;
import com.esamarathon.ESARack.web.models.VP50Model;
import com.google.gson.*;

public class API {

	public API(VP50 vp50) {
		port(8080);

		post("/api/vp50", "text/json", new Route() {
			// This is a proof of concept of how the API could work.
			// I would like to cut down a bit on the boilerplate code.
			// Maybe with some reflection magic.
			// For each field in model, call corresponding method of device with value of model if it is not null.
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

		}, json());
	}
}
