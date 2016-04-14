package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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

	private static String INVOCATION_URL;
	
	public static void setInvocationURL(String url){
		INVOCATION_URL = url;
		System.out.println(INVOCATION_URL);
	}
	
	public String postHttpInvocation(String interfaceName , Map<String , String> params) throws Exception{ 
		
		String paramStr = formParams(params);
		URL url = new URL(INVOCATION_URL + interfaceName + "?" + paramStr);
		
		OutputStream outputStream = null;
		OutputStreamWriter writer = null;
		BufferedReader inputStream = null;
		
		try{
			
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
	        conn.setRequestProperty("contentType", "utf-8");  
	        conn.setConnectTimeout(5 * 1000);  
	        conn.setRequestMethod("GET");  
	        InputStream inStream = conn.getInputStream();  
			
	        BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "utf-8"));  
	        StringBuffer buffer = new StringBuffer();  
	        String line = "";  
	        while ((line = in.readLine()) != null){  
	          buffer.append(line);  
	        }  
	        String str = buffer.toString(); 
			
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
