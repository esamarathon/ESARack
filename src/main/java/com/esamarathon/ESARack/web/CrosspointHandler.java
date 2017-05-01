package com.esamarathon.ESARack.web;

import com.esamarathon.ESARack.hardware.Crosspoint;

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
			// TODO Auto-generated method stub
			return null;
		}

	}
	
	private class PostRoute implements Route {
		

		@Override
		public Object handle(Request request, Response response) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}
	}

}
