package server.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import controller.travel.TravelController;

public class createTravel extends Base implements HttpHandler{
	@Override
	public void handle(HttpExchange t) throws IOException {
		Map<String, String> params = queryToMap(t.getRequestURI().toString());
		String response=TravelController.reate(params.get("passenger_id"), params.get("cost"), params.get("From_place_latitude"), params.get("From_place_longitude"), params.get("To_place_lltitude"), params.get("To_place_longitude"));
   	 	t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
	}
}

