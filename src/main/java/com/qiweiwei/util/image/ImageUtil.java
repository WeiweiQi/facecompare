package com.qiweiwei.util.image;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.jfinal.kit.LogKit;

public class ImageUtil {
	
	public static final String APP_ID = "17343924";
    public static final String API_KEY = "QUPxMLg9AeLvADkWNQqIhGcS";
    public static final String SECRET_KEY = "vSBckg2BGKWX7wGWQb58kjlYMTSK72fK";
	
	private static final String UNKNOWN_IMAGE_BASE64 = "unknown";
	
	public static AipFace getAipFace() {
		return new AipFace(APP_ID, API_KEY, SECRET_KEY);
	}

	public static String base64Of(String url) {
		try (InputStream is = new URL(url).openStream()){
			byte[] bytes = IOUtils.toByteArray(is);
			return Base64.getEncoder().encodeToString(bytes);
 		} catch (Exception e) {
			LogKit.error("Url转base64失败：url=" + url);
		}
		return UNKNOWN_IMAGE_BASE64;
	}
	
	public static String base64Of(File imageFile) {
		if (imageFile == null) {
			return UNKNOWN_IMAGE_BASE64;
		}
		try {
			byte[] filebytes = FileUtils.readFileToByteArray(imageFile);
			return Base64.getEncoder().encodeToString(filebytes);
		} catch (IOException e) {
			LogKit.error("图片转换为base64失败: imageFile absolute path="+ imageFile.getAbsolutePath());
		}
		return UNKNOWN_IMAGE_BASE64;
	}
	
	public static JSONObject compare(String image1Url, String image2Url) {
	    String image1 = ImageUtil.base64Of(image1Url);
	    String image2 = ImageUtil.base64Of(image2Url);
	    return compareByBase64(image1, image2);
	}
	
	public static JSONObject compare(File imageFile1, File imageFile2) {
		String image1 = ImageUtil.base64Of(imageFile1);
	    String image2 = ImageUtil.base64Of(imageFile2);
	    // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
	    return compareByBase64(image1, image2);
	}

	private static JSONObject compareByBase64(String image1, String image2) {
		MatchRequest req1 = new MatchRequest(image1, "BASE64");
	    MatchRequest req2 = new MatchRequest(image2, "BASE64");
	    ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
	    requests.add(req1);
	    requests.add(req2);
	    return getAipFace().match(requests);
	}

}
