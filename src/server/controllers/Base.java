package server.controllers;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mysql.jdbc.ResultSetMetaData;


public class Base{

	  public Map<String, String> queryToMap(String query) {
	        Map<String, String> result = new HashMap<>();
	        for (String param : query.split("&")) {
	            String[] entry = param.split("=");
	            if (entry.length > 1) {
	                result.put(entry[0], entry[1]);
	            }else{
	                result.put(entry[0], "");
	            }
	        }
	        return result;
	    }
	    
	    public JSONObject convertToJSON(ResultSet resultSet) throws Exception {
	    	JSONArray json = new JSONArray();
	    	ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
	    	while(resultSet.next()) {
	    	  int numColumns = rsmd.getColumnCount();
	    	  JSONObject obj = new JSONObject();
	    	  for (int i=1; i<=numColumns; i++) {
	    	    String column_name = rsmd.getColumnName(i);
	    	    obj.put(column_name, resultSet.getObject(column_name));
	    	  }
	    	  json.put(obj);
	    	}
	    	JSONObject returnable = new JSONObject();
	    	returnable.put("data",json);
	    	return returnable;
	    }
}
