package com.qiweiwei.util.image;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.jfinal.kit.PropKit;

public class BFace implements Face {
	
	public static final String APP_ID = PropKit.get("baidu_app_id");
    public static final String API_KEY = PropKit.get("baidu_app_key");
    public static final String SECRET_KEY = PropKit.get("baidu_app_secret");
    
    

	@Override
	public String detectByBase64(String base64) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("face_field", "beauty,gender");
		return getBaiduAipFace().detect(base64, "BASE64", options).toString();
	}

	@Override
	public String getBeauty(String detectResult) {
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(detectResult);
		return jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0).getString("beauty");
	}

	@Override
	public String getGender(String detectResult) {
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(detectResult);
		return jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0).getJSONObject("gender").getString("type");
	}
	
	
	
	
	
	
	private static JSONObject compare(String image1Url, String image2Url) {
	    String image1 = Face.base64Of(image1Url);
	    String image2 = Face.base64Of(image2Url);
	    return compareByBase64(image1, image2);
	}
	
	private static JSONObject compare(File imageFile1, File imageFile2) {
		String image1 = Face.base64Of(imageFile1);
	    String image2 = Face.base64Of(imageFile2);
	    // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
	    return compareByBase64(image1, image2);
	}
	
	private static JSONObject compareByBase64(String image1, String image2) {
		MatchRequest req1 = new MatchRequest(image1, "BASE64");
	    MatchRequest req2 = new MatchRequest(image2, "BASE64");
	    ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
	    requests.add(req1);
	    requests.add(req2);
	    return getBaiduAipFace().match(requests);
	}
	
	private static AipFace getBaiduAipFace() {
		return new AipFace(APP_ID, API_KEY, SECRET_KEY);
	}

}
