package com.demo.blog;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.demo.common.model.Blog;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * 所有 sql 与业务逻辑写在 Service 中，不要放在 Model 中，更不
 * 要放在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(BlogInterceptor.class)
public class BlogController extends Controller {
	
	 //设置APPID/AK/SK
    public static final String APP_ID = "17343924";
    public static final String API_KEY = "QUPxMLg9AeLvADkWNQqIhGcS";
    public static final String SECRET_KEY = "vSBckg2BGKWX7wGWQb58kjlYMTSK72fK";
	
	
	@Inject
	BlogService service;
	
	public void index() {
		setAttr("blogPage", service.paginate(getParaToInt(0, 1), 10));
		render("blog.html");
	}
	
	public void add() {
	}
	
	/**
	 * save 与 update 的业务逻辑在实际应用中也应该放在 serivce 之中，
	 * 并要对数据进正确性进行验证，在此仅为了偷懒
	 */
	@Before(BlogValidator.class)
	public void save() {
		getBean(Blog.class).save();
		redirect("/blog");
	}
	
	public void edit() {
		setAttr("blog", service.findById(getParaToInt()));
	}
	
	/**
	 * save 与 update 的业务逻辑在实际应用中也应该放在 serivce 之中，
	 * 并要对数据进正确性进行验证，在此仅为了偷懒
	 */
	@Before(BlogValidator.class)
	public void update() {
		getBean(Blog.class).update();
		redirect("/blog");
	}
	
	public void delete() {
		service.deleteById(getParaToInt());
		redirect("/blog");
	}
	
	@Clear
	public void test() {
		
		
		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		
		// 调用接口
        String image = "https://goss.veer.com/creative/vcg/veer/800water/veer-152767437.jpg";
        String imageType = "URL";
        
     // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age");
        options.put("max_face_num", "1");
//        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");
        
     // 人脸检测
        JSONObject res = client.detect(image, imageType, options);
        System.out.println(res.toString(2));
		renderText(res.toString());
	}
}


