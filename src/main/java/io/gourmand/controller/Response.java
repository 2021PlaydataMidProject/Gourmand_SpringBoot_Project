package io.gourmand.controller;

import com.google.gson.JsonObject;

public class Response {
	public Response(String string, String string2, String message) {
		
	try {
		JsonObject obj =new JsonObject();
	    JsonObject data = new JsonObject();

	    data.addProperty("status", string);
	    data.addProperty("message", string2);
	    data.addProperty("token", message);
	    
	    obj.add("data", data);
	    System.out.println(obj.toString());
	} catch(Exception e) {
		e.printStackTrace();
		}
	}
}

	