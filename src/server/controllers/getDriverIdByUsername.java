package server.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import controller.driver.DriverController;

public class getDriverIdByUsername extends Base implements HttpHandler{
	@Override
	public void handle(HttpExchange t) throws IOException {
		Map<String, String> params = queryToMap(t.getRequestURI().toString());
		String response;
		try {
			response = DriverController.getByUsername(params.get("username"))+"";
			t.sendResponseHeaders(200, response.length());
	        OutputStream os = t.getResponseBody();
	        os.write(response.getBytes());
	        os.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
