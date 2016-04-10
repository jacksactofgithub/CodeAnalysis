package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 进行http调用的辅助类
 * @author Ray Liu
 *
 */
public class HttpHandler {

	private static final String INVOCATION_URL = "http://127.0.0.1:9000/plugin/";
	
	public String postHttpInvocation(String interfaceName , Map<String , String> params) throws Exception{ 
		
		String paramStr = formParams(params);
		URL url = new URL(INVOCATION_URL + interfaceName + "?" + paramStr);
		
		OutputStream outputStream = null;
		OutputStreamWriter writer = null;
		BufferedReader inputStream = null;
		
		try{
			URLConnection connection = url.openConnection();
			HttpURLConnection httpCon = (HttpURLConnection) connection;
			
			
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Accept-Charset", "utf-8");
			
			inputStream = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
			
//			List<String> result = new ArrayList<String>();
			String str = "";
			str = inputStream.readLine();
//			while ((str = inputStream.readLine())!=null ){
////				result.add(str);
//			}
			
			return str;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(outputStream != null){
				outputStream.close();
			}
			
			if(writer != null){
				writer.close();
			}
			
			if(inputStream != null){
				inputStream.close();
			}
			
		}
		
		return null;
	}
	
	/**
	 * 将所有参数转为一个String，中间用&链接
	 * @param params
	 * @return
	 */
	private String formParams(Map<String , String> params){
		String result = "";
		
		if(params != null){
			
			if(params.isEmpty()){
				return result;
			}
			
			Iterator<Entry<String , String>> it = params.entrySet().iterator();
			
			Entry<String ,String> entry = it.next();
			String param = entry.getKey()+"="+entry.getValue();
			result = param;
			
			while(it.hasNext()){
				entry = it.next();
				param = entry.getKey()+"="+entry.getValue();
				result = result + "&" + param;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args){
		HttpHandler h = new HttpHandler();
		Map<String , String> map = new HashMap<String , String>();
		
		map.put("teaId", "34");
		map.put("test", "testValue");
		
		try {
			String object = h.postHttpInvocation("getTeacherFinishedExams", map);
//			JSONObject json = (JSONObject) object;
			System.out.println(object);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
