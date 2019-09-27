package com.qiweiwei.facecompare;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;

public class FaceCompareController extends Controller{
	
	@Inject
	FaceCompareService service;
	
	public void index() {
		render("index.html");
	}
	
	public void previewcut() {
		render("preview/index.html");
	}
	
	public void uploadimage() {
		//UploadFile file = getFile();
		String base64 = getPara("base64code");
		renderJson(new Record().set("success", "1").set("code", "000001"));
	}

}
