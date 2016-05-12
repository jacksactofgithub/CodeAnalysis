package pkg.controller.storage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import pkg.service.CodeService;
//import pkg.service.RunService;
import pkg.service.StorageService;

@Controller
public class StorageController {

	@Autowired
	private StorageService storageService;
//	@Autowired
//	private RunService runService;
//	@Autowired
//	private CodeService codeService;
	
	@RequestMapping("/storageStart")
	public String studentAnalysis(HttpServletRequest request,HttpSession session){
		
		int examID = Integer.parseInt(request.getParameter("exam_id"));
		
		int state = storageService.getAnalyseState(examID);
		
		if(state == 0){
			storageService.startStore(examID);
		}
		
		return null;
	}
	
}
