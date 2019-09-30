package com.qiweiwei.facecompare;

import java.util.List;
import java.util.concurrent.Executor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.demo.common.model.NetPics;
import com.jfinal.kit.LogKit;
import com.qiweiwei.util.image.Face;
import com.qiweiwei.util.jsoup.JsoupUtil;
import com.qiweiwei.util.threadpool.MyThreadPool;

public class FaceCompareService {
	
	public static Executor countFacePointExecutor = MyThreadPool.getTheadPool("-thread-get-face-point-%d-");

	public void initStars() {
		String pageUrl = "http://www.mingxing.com/ziliao/index?p=";
		for (int i = 1; i <= 193; i++) {
			Document document = JsoupUtil.getDocument(pageUrl + i);
			if(document == null) {
				return;
			}
			Element element = document.getElementsByClass("page_starlist").first();
			Elements imgElements = element.getElementsByTag("img");
			for (Element element2 : imgElements) {
				NetPics pic = new NetPics();
				pic.setStarname(element2.attr("alt"));
				pic.setPicurl(element2.attr("src"));
				String base64 = Face.base64Of(pic.getPicurl());
				pic.setBase64(base64);
				pic.save();
				MyThreadPool.getThreadExecutor(new Thread(() -> {
					updatePicBeauty(pic, base64);
				}));
			}
		}
	}

	private void updatePicBeauty(NetPics pic, String base64) {
//		String detectResult = Face.getFaceDetectByBase64(base64);
//		LogKit.info("图片检测结果：" + detectResult);
//		JSONObject jsonObject = JSONObject.parseObject(detectResult);
//		
//		try {
//			JsonElement element = jsonObject.getAsJsonObject("result").getAsJsonArray("face_list").get(0);//..getJSONObject(0).get("beauty").toString();
//			Record faceRecord = new Gson().fromJson(element, Record.class);
//			String beauty = faceRecord.get("beauty").toString();
//			int point = Integer.valueOf(beauty.split("\\.")[0]);
//			String genderType = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0).getJSONObject("gender").getString("type");
//			if ("female".equals(genderType)) {
//				pic.setGender(0);
//			} else if ("male".equals(genderType)) {
//				pic.setGender(1);
//			}
//			pic.setBeautifulpoint(point);
//			pic.setBeauty(beauty);
//			pic.update();
//		} catch (Exception e) {
//			LogKit.error("解析json出错：" + e.getMessage());
//		}
	}
	
	public void initFacePoint() {
		for (; ;) {
			try {
				List<NetPics> notPointPics = new NetPics().find("SELECT * FROM net_pics WHERE gender IS NULL LIMIT 1");
				if (notPointPics==null || notPointPics.isEmpty()) {
					break;
				}
				try {
					Thread.sleep(1100);
				} catch (Exception e) {
					LogKit.error("线程睡眠失败：" + e.getMessage());
				}
				updatePicBeauty(notPointPics.get(0), notPointPics.get(0).getBase64());
			} catch (Exception e) {
				LogKit.error("出错：" + e.getMessage());
			}
			
		}
		
	}

}
