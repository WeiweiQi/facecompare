package com.qiweiwei.facecompare;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

public class FaceCompareController extends Controller{
	
	@Inject
	FaceCompareService service;
	
	public void index() {
		render("index.html");
	}
	
	public void previewcut() {
		render("preview/index.html");
	}

}
