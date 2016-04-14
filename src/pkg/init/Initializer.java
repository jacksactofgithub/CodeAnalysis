package pkg.init;

import java.util.Iterator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import lmooc.modulize.io.FileReader;
import util.HttpHandler;

@Component
public class Initializer implements InitializingBean{

	private static final String URL_CFG_PATH = "../url.cfg";
	
//	public static String MOOC_SERVICE_URL;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		FileReader freader = new FileReader();
		Iterator<String> it = freader.read(this.getClass().getResource("/").getPath() + URL_CFG_PATH);
		
		boolean inService = false;
		
		while(it.hasNext()){
			String str = it.next();
			if(str.startsWith("#")){
				continue;
			}
			if(str.equals("service:")){
				inService = true;
				continue;
			}
			if(inService){
				HttpHandler.setInvocationURL(str);
				inService = false;
			}
		}
		
		
	}

}
