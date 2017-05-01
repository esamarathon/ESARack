package com.esamarathon.ESARack.web;

import com.esamarathon.ESARack.hardware.IN1606;

import spark.Request;
import spark.Response;
import spark.Route;

public class IN1606Handler implements Handler {
	
	protected IN1606 in1606;

	public IN1606Handler(IN1606 in1606) {
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
