package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendToServer {
	   public static String send(String urlToRead){
		      StringBuilder result = new StringBuilder();
		      URL url;
			try {
				url = new URL(urlToRead);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			      conn.setRequestMethod("GET");
			      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			      String line;
			      while ((line = rd.readLine()) != null) {
			         result.append(line);
			      }
			      rd.close();
			      return result.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
	   }
}
