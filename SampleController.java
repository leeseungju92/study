package com.open.ma.sys.sample;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.ksign.access.json.JSONException;
public class SampleController {
	
	public static void main(String[] args) throws IOException, JSONException, ParseException {
		System.out.println("크롤시작");
		String url = "https://www.kpx.or.kr/";

		Document doc = Jsoup.connect(url).get();
		Elements smpLand = doc.select("div#smp_01 table tbody tr");
		Elements smpJeju = doc.select("div#smp_02 table tbody tr");
		Elements rec = doc.select("div.rec table tbody tr");
		
		Elements smpLandTd = smpLand.select("td");
		List<String> smpLandValue = smpLandTd.eachText();
		Elements smpJejuTd = smpJeju.select("td");
		List<String> smpJejuValue = smpJejuTd.eachText();
		Elements recTd = rec.select("td");
		List<String> recValue = recTd.eachText();
		
	    String smpLandJsonString = "{\"date\": \""+smpLandValue.get(0).toString()+"\","
	            + "\"max\": \""+smpLandValue.get(1).toString()+"\","
	            + "\"min\": \""+smpLandValue.get(2).toString()+"\","
	            + "\"avg\": \""+smpLandValue.get(3).toString()+"\""
	            + "}";
	    String smpJejuJsonString = "{\"date\": \""+smpJejuValue.get(0).toString()+"\","
	    		+ "\"max\": \""+smpJejuValue.get(1).toString()+"\","
	    		+ "\"min\": \""+smpJejuValue.get(2).toString()+"\","
	    		+ "\"avg\": \""+smpJejuValue.get(3).toString()+"\""
	    		+ "}";
	    String recJsonString = "{\"date\": \""+recValue.get(0).toString()+"\","
	    		+ "\"amount\": \""+recValue.get(1).toString()+"\""
	    		+ "\"avg\": \""+recValue.get(2).toString()+"\""
	    		+ "\"max\": \""+recValue.get(3).toString()+"\","
	    		+ "\"min\": \""+recValue.get(4).toString()+"\","
	    		+ "\"fin\": \""+recValue.get(5).toString()+"\""
	    		+ "}";
	    
	    JSONParser jsonParse = new JSONParser();
	    JSONObject smpLandJson = (JSONObject)jsonParse.parse(smpLandJsonString);
	    JSONObject smpJejuJson = (JSONObject)jsonParse.parse(smpJejuJsonString);
	    JSONObject recJson = (JSONObject)jsonParse.parse(recJsonString);
	    
	    System.out.println(smpLandJson);
	    System.out.println(smpJejuJson);
	    System.out.println(recJson);
	}
}
