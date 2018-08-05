package server.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import controller.passenger.MoneyController;

public class addMoney extends Base implements HttpHandler{
	@Override
	public void handle(HttpExchange t) throws IOException {
		Map<String, String> params = queryToMap(t.getRequestURI().toString());
		String response=MoneyController.addMoney(Integer.parseInt(params.get("id")), Integer.parseInt(params.get("money")));
   	 	t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
	}
}