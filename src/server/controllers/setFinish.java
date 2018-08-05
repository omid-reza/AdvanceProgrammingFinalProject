package server.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import controller.driver.SalarayController;
import controller.travel.TravelController;

public class setFinish extends Base implements HttpHandler{
	@Override
	public void handle(HttpExchange t) throws IOException {
		Map<String, String> params = queryToMap(t.getRequestURI().toString());
		int cost=TravelController.setFinish(Integer.parseInt(params.get("travel")));
		SalarayController.addSalary(Integer.parseInt(params.get("driver")), cost);
		String response=cost+"";
   	 	t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
	}
}
