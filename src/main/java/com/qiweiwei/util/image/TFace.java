package com.qiweiwei.util.image;

import java.util.Base64;

import com.jfinal.kit.LogKit;
import com.jfinal.kit.PropKit;

import cn.xsshome.taip.face.TAipFace;

public class TFace implements Face{
	
	private static final String TENCENT_APP_ID = PropKit.get("tencent_app_id");
	private static final String TENCENT_APP_KEY = PropKit.get("tencent_app_key");
	
	

	@Override
	public String detectByBase64(String base64) {
		return getTencentDetectResult(base64);
	}

	@Override
	public String getBeauty(String detectResult) {
		//TODO 
		
		return null;
	}

	@Override
	public String getGender(String detectResult) {
		// TODO Auto-generated method stub
		return null;
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
