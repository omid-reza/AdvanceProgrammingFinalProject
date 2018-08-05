package server.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.travel.TravelController;

public class acceptTravelByDriver extends Base implements HttpHandler{
	@Override
	public void handle(HttpExchange t) throws IOException {
		Map<String, String> params = queryToMap(t.getRequestURI().toString());
		String response=TravelController.AcceptTravelByDriver(Integer.parseInt(params.get("travel")), Integer.parseInt(params.get("driver")));
   	 	t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
	}
}

