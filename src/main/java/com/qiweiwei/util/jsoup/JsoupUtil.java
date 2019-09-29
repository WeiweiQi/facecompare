package com.qiweiwei.util.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.jfinal.kit.LogKit;

public final class JsoupUtil {
	
	public static Document getDocument(String url) {
		try {
			return Jsoup.connect(url).timeout(4000).get();
		} catch (IOException e) {
			LogKit.error("解析Url出错");
		}
		return null;
	}
	
	
	

}
