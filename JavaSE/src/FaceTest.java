import java.io.File;

import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;


public class FaceTest {
	public static void main(String[] args) {
		// 初始化函数HttpRequests，传入四个参数依次是API_Key，API_Secret，是否使用CN服务器，是否使用HTTP模式连接。

		// replace api_key and api_secret here (note)
		HttpRequests httpRequests = new HttpRequests("13tDchV-pJjLSueZIj13qSmfbQn42CqO",
				"Mf50AfjvdxelkvN-35trBpyjZBRpD_2M", true, true);

		JSONObject result = null;

		StringBuilder sb = new StringBuilder();


			// detection/detect
			// 识别图片
			//result = httpRequests
			//		.detectionDetect(new PostParameters()//.setImg(new File("F:\\self\\me\\img\\IMAG0111.jpg")));
			//				.setUrl("http://cms.csdnimg.cn/article/201403/28/53351b5db17fa.jpg"));
			//System.out.println(result);

			HttpRequests hs = new HttpRequests("4480afa9b8b364e30ba03819f3e9eff5", "Pz9VFT8AP3g_Pz8_dz84cRY_bz8_Pz8M", true, false);
            try{
                /**
                 * 具体方法可以对照着看API 都有详细讲解
                 * 这里只是简单的测试人脸识别 获取一些attribute信息 （比如：年龄 性别 人种..）
                 * @author Folger
                 */
                PostParameters pps=new PostParameters();
                result = hs.detectionDetect(pps.setImg(new File("F:\\self\\me\\img\\IMAG0111.jpg")).setAttribute("all"))
                        .getJSONArray("face").getJSONObject(0).getJSONObject("attribute");
                int age = result.getJSONObject("age").getInt("value");
                int agerange=result.getJSONObject("age").getInt("range");
                String sex=result.getJSONObject("gender").getString("value");
                Double sexconfidence=result.getJSONObject("gender").getDouble("confidence");
                String race=result.getJSONObject("race").getString("value");
                Double raceconfidence=result.getJSONObject("race").getDouble("confidence");
                Double smilingfidence=result.getJSONObject("smiling").getDouble("value");

                //System.out.println(result);

             sb.append(" "+"\n");
             sb.append("年龄"+age+"岁左右"+" ");
             sb.append("误差范围在"+agerange+"岁上下"+"\n");
             sb.append("性别为"+sex+" "+"正确率："+sexconfidence+"%\n");
             sb.append("种族为"+race+" "+"正确率："+raceconfidence+"%\n");
             sb.append("正在笑的概率："+smilingfidence+"%");

		} catch (FaceppParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
		}
        System.out.println(sb.toString());
	}
}
