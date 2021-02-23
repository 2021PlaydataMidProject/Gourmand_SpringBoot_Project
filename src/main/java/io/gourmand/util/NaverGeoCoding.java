package io.gourmand.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NaverGeoCoding {
	public static BigDecimal[] returnAxis(String location) {
		
		BigDecimal[] answer = new BigDecimal[2];
		try {
			String addr;
			addr = URLEncoder.encode(location, "utf-8");
			String api;
			api = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + addr;

			URL url = new URL(null, api, new sun.net.www.protocol.https.Handler());
			HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
			http.setRequestProperty("Content-Type", "application/json");
			http.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "키아이디");
			http.setRequestProperty("X-NCP-APIGW-API-KEY", "키");
			http.setRequestMethod("GET");
			http.connect();

			InputStreamReader in = new InputStreamReader(http.getInputStream(), "utf-8");
			BufferedReader br = new BufferedReader(in);
			String line = br.readLine();
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(line);
			JSONArray jsonArray = (JSONArray)jsonObject.get("addresses");
			jsonObject = (JSONObject) jsonArray.get(0);
			String x = (String) jsonObject.get("x");
			String y = (String) jsonObject.get("y");
			
			br.close();
			in.close();
			http.disconnect();
			
			answer[0]= new BigDecimal(x).setScale(4, RoundingMode.FLOOR);
			answer[1]= new BigDecimal(y).setScale(5, RoundingMode.FLOOR);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return answer;
	}
}
