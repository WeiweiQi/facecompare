package com.qiweiwei.util.image;

import java.util.Base64;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.PropKit;

import cn.xsshome.taip.face.TAipFace;

public class TFace implements Face{
	
	private static final String TENCENT_APP_ID = PropKit.get("tencent_app_id");
	private static final String TENCENT_APP_KEY = PropKit.get("tencent_app_key");
	
	
	@Override
	public String detectByBase64(String base64) {
		String result = getTencentDetectResult(base64);
		return result;
	}

	@Override
	public String getBeauty(String detectResult) {
		JSONObject jsonObject = JSONObject.parseObject(detectResult);
		return jsonObject.getJSONObject("data").getJSONArray("face_list").getJSONObject(0).get("beauty").toString();
	}

	@Override
	public int getGender(String detectResult) {
		JSONObject jsonObject = JSONObject.parseObject(detectResult);
		int genderNumber = jsonObject.getJSONObject("data").getJSONArray("face_list").getJSONObject(0).getIntValue("gender");
		return genderNumber <= 50 ? 0 : 1;
	}
	
	private static TAipFace getTencentAipFace() {
		return new TAipFace(TENCENT_APP_ID, TENCENT_APP_KEY);
	}
	
	private static String getTencentDetectResult(String base64) {
		try {
			return getTencentAipFace().detect(Base64.getDecoder().decode(base64));
		} catch (Exception e) {
			LogKit.error("腾讯检测图片出错");
		}
		return "";
	}

}
