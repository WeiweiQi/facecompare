package com.qiweiwei.facecompare;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.kit.LogKit;
import com.jfinal.plugin.activerecord.Record;
import com.qiweiwei.util.image.Face;
import com.qiweiwei.util.image.TFace;

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
		base64 = base64.replaceAll(" ", "+");
		renderJson(new Record().set("success", "1").set("code", "000001").set("base64", base64));
	}
	
	public void initStars() {
		//service.initStars();
		renderText("成功");
	}
	
	public void updateBeautyPoint() {
		service.initFacePoint();
		renderText("ok");
	}
	
	public void test() {
		Face face = new TFace();
		LogKit.info(face.detectByBase64(Face.base64Of("http://img.tupianzj.com/uploads/allimg/161208/9-16120R05Q9.jpg")));
		renderText("测试完毕");
	}

}
