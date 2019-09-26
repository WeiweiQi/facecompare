package detectface;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.qiweiwei.util.image.ImageUtil;

public class Test {
	
	 //设置APPID/AK/SK
    public static final String APP_ID = "17343924";
    public static final String API_KEY = "QUPxMLg9AeLvADkWNQqIhGcS";
    public static final String SECRET_KEY = "vSBckg2BGKWX7wGWQb58kjlYMTSK72fK";

	public static void main(String[] args) {
		System.out.println(ImageUtil.compare("http://img.tupianzj.com/uploads/allimg/161208/9-16120R05Q9.jpg", "http://img.tupianzj.com/uploads/allimg/161208/9-16120R05R7.jpg"));
	}
	
	

}
