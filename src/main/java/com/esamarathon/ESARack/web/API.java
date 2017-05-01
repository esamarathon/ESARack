package com.esamarathon.ESARack.web;

import static spark.Spark.*;
import static com.esamarathon.ESARack.web.util.JsonUtil.json;

import com.esamarathon.ESARack.hardware.*;

public class API {

	public API(VP50 vp50, OSSC ossc, Crosspoint crosspoint, IN1606 in1606) {
		port(8080);
		
		Handler vp50Handler = new VP50Handler(vp50);
		Handler osscHandler = new OSSCHandler(ossc);
		Handler crosspointHandler = new CrosspointHandler(crosspoint);
		Handler in1606Handler = new IN1606Handler(in1606);
		
		get("/api/vp50", "text/json", vp50Handler.Get(), json());
		get("/api/ossc", "text/json", osscHandler.Get(), json());
		get("/api/crosspoint", "text/json", crosspointHandler.Get(), json());
		get("/api/in1606", "text/json", in1606Handler.Get(), json());
		
		post("/api/vp50", "text/json", vp50Handler.Post(), json());
		post("/api/ossc", "text/json", osscHandler.Post(), json());
		post("/api/crosspoint", "text/json", crosspointHandler.Post(), json());
		post("/api/in1606", "text/json", in1606Handler.Post(), json());
	}
}
