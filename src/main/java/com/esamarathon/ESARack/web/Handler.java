package com.esamarathon.ESARack.web;

import spark.Route;

public interface Handler {
	Route Get();
	Route Post();
}
