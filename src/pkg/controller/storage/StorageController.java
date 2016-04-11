package pkg.controller.storage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pkg.service.RunService;
import pkg.service.StorageService;

@Controller
public class StorageController {

	@Autowired
	private StorageService storageService;
	@Autowired
	private RunService runService;
	
	@RequestMapping("/storageStart")
	public String studentAnalysis(HttpServletRequest request,HttpSession session){
		
		int examID = Integer.parseInt(request.getParameter("exam_id"));
		
//		storageService.startStore(examID);
		
		runService.saveCommonTests(382, "CalculateMatrix");
		
		return null;
	}
	
}
