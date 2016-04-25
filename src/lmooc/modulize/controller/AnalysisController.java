//package lmooc.modulize.controller;
//
//import lmooc.modulize.bean.SubjectResult;
//import lmooc.modulize.model.AnalysisDataManager;
//
//public class AnalysisController {
//	
//	/**
//	 * 提供数据的接口，输入考试编号，学生编号和所要查询的题目名称
//	 * 返回该题目的代码分析数据和运行分析数据
//	 * @param examID
//	 * @param stuID
//	 * @param subjectName
//	 * @return
//	 */
//	public SubjectResult acquireOneSubject(String examID , String stuID , String problemName){
//		
//		return AnalysisDataManager.getResult(examID, stuID, problemName);
//	}
//	
//	public static void main(String[] args){
//		AnalysisController con = new AnalysisController();
//		SubjectResult result = con.acquireOneSubject("290", "2", "Triangle");
//	}
//}
