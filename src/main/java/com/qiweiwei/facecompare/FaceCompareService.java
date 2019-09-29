package com.qiweiwei.facecompare;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.demo.common.model.NetPics;
import com.qiweiwei.util.image.ImageUtil;
import com.qiweiwei.util.jsoup.JsoupUtil;

public class FaceCompareService {

	public void initStars() {
		String pageUrl = "http://www.mingxing.com/ziliao/index?p=";
		for (int i = 1; i <= 193; i++) {
			Document document = JsoupUtil.getDocument(pageUrl + i);
			Element element = document.getElementsByClass("page_starlist").first();
			Elements imgElements = element.getElementsByTag("img");
			for (Element element2 : imgElements) {
				NetPics pic = new NetPics();
				pic.setStarname(element2.attr("alt"));
				pic.setPicurl(element2.attr("src"));
				String base64 = ImageUtil.base64Of(pic.getPicurl());
				pic.setBase64(base64);
				
				
			}
			
		}
		
		
		
	}

}
