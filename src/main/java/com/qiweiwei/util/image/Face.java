package com.qiweiwei.util.image;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.jfinal.kit.LogKit;

public interface Face {
	
	static final String UNKNOWN_IMAGE_BASE64 = "unknown";
	
	public abstract String detectByBase64(String base64);
	public abstract String getBeauty(String detectResult);
	public abstract String getGender(String detectResult);
	
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

}
