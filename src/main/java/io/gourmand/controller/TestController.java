package io.gourmand.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;


@RestController
public class TestController {

  @ResponseBody
  @RequestMapping("/test3")
  public static String test3(){
    JsonObject obj =new JsonObject();

    JsonObject data = new JsonObject();

    data.addProperty("user_num", "1");
    data.addProperty("user_id", "test");

    obj.add("data", data);

    return obj.toString();
  }
  
  
  public static void main(String[] args) {
	  System.out.println(test3());
  }
}

//@RestController
//public class TestController {
//	@GetMapping("/api/hello")
//	public String hello() {
//		System.out.println("ddd");
//		return "String";
//	}
//}