package server.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.driver.DriverController;

public class driverTravelKindCount extends Base implements HttpHandler{
	@Override
	public void handle(HttpExchange t) throws IOException {
		Map<String, String> params = queryToMap(t.getRequestURI().toString());
		String response=DriverController.travelkindsCount(Integer.parseInt(params.get("id")), params.get("kind"))+"";
   	 	t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
	}
}
