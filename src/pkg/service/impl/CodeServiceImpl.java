package pkg.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lmooc.modulize.bean.CodeStamp;
import lmooc.modulize.io.Reader;
import pkg.dao.CodeDAO;
import pkg.entity.Code;
import pkg.service.CodeService;
import pkg.service.ExamService;
//import util.cache.Cache;
//import util.cache.LRUCache;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeDAO codeDAO;
	@Autowired
	private ExamService examService;
	
	private Reader reader = new Reader();
	
//	private static final String seperator = ";";
//
//	// key: examId;proName;classMemId;fileName
//	private static Cache<String, Map<Integer, Code>> codeCache = new LRUCache<String, Map<Integer, Code>>(2,
//			5 * 60 * 1000);

	/**
	 * 
	 * @return
	 */
	private Map<Integer, Code> mapTime(Iterator<Code> code) {
		System.out.println("Mapping code");
		Map<Integer, Code> map = new HashMap<Integer, Code>(130);
		if ((code == null) || (!code.hasNext())) {
			return map;
		}

		int count = 0; 
		Code former = null;
		Code current = null;
		int interval = 0;
		while ((code.hasNext()) || (count <=120)) {
			if (former == null) {
				Code temp = code.next();
				map.put(count, temp);
				former = temp;
				if (code.hasNext()) {
					current = code.next();
				} else {
					break;
				}
				count++;
				interval = (count * 60);
				continue;
			}

			if (former.getSecond() == current.getSecond()) {
				former = current;
				if (code.hasNext()) {
					current = code.next();
				} else {
					interval = 0;
				}
			}

			int currentInterval = calInterval((count * 60), current.getSecond());
			if (currentInterval < interval) {
				interval = currentInterval;
				former = current;
				if (code.hasNext()) {
					current = code.next();
				} else {
					interval = 0;
				}
			} else {
				map.put(count, former);
				count++;
				interval = (count * 60) - former.getSecond();
			}

			if ((!code.hasNext()) && ((count * 60 > current.getSecond()))) { 
				map.put(count, current);
			}

		}
		
		System.out.println("Map code end");
		return map;
	}

	private static int calInterval(int timeOne, int timeTwo) {
		int result = timeOne - timeTwo;
		if (timeOne < timeTwo) {
			result = 0 - result;
		}
		return result;
	}

	@Override
	public int saveStamps(Iterator<CodeStamp> stamps, int stuID, int exam) {
		// TODO Auto-generated method stub
		// List<Attendence> attList = new ArrayList<Attendence>();
		while (stamps.hasNext()) {
			Code attendence = generateAttendence(stamps.next(), stuID, exam);
			codeDAO.addCode(attendence);
		}

		return 0;
	}

	private Code generateAttendence(CodeStamp stamp, int stuID, int exam) {
		Code attendence = new Code();
		attendence.setStudent_id(stuID);
		attendence.setExam_id(exam);
		attendence.setLine_count(stamp.getLineCount());
		attendence.setNote_count(stamp.getNoteCount());
		attendence.setMethod_count(stamp.getMethodCount());
		attendence.setVar_count(stamp.getVaryCount());
		attendence.setMax_cyclomaticcpl(stamp.getMaxCyc());
		attendence.setSecond((int) (stamp.getRelativeTime() / 1000));
		attendence.setTimestamp(stamp.getTimestamp());
		attendence.setFile_name(stamp.getSourceName());

		String proName = stamp.getSourceName().split("/")[0];
		attendence.setPro_name(proName);

		return attendence;
	}

	@Override
	public JSONArray getCodeRecord(int stuID, String proName, int exam , String fileName) {
		// TODO Auto-generated method stub
		int classMemberId = examService.getClassMemberId(stuID, exam);
//		String key = exam+seperator+proName+seperator+classMemberId+seperator+fileName;
		
		Map<Integer , Code> codeMap = cacheCodeMap(classMemberId, proName, exam, fileName);
//		if(!codeCache.containsKey(key)){
//			cacheCodeMap(classMemberId, proName, exam, fileName);
//		}
//		
//		codeMap = codeCache.get(key);
		
		JSONArray array = getCodeJSON(codeMap);

		return array;
	}
	
	private Map<Integer, Code> cacheCodeMap(int classMemberId , String proName , int exam_id , String fileName){
//		String key = exam_id+seperator+proName+seperator+classMemberId+seperator+fileName;
		List<Code> list = codeDAO.queryCode(classMemberId, proName, exam_id);

		Iterator<Code> it = list.iterator();

		while (it.hasNext()) {
			Code code = it.next();
			if (!code.getFile_name().contains(fileName)) {
				it.remove();
			}
		}
		
		Map<Integer, Code> codeMap = mapTime(list.iterator());
//		codeCache.put(key, codeMap);
//		System.out.println("cacheSize:"+codeCache.size());
		return codeMap;
	}

	public JSONArray getCodeJSON(Map<Integer, Code> codeMap) {
		JSONArray array = new JSONArray();

		int minute = 0;
		while (true) {
			if (!codeMap.containsKey(minute)) {
				break;
			} else {
				try {
					JSONObject json = formCode(codeMap.get(minute), minute);
					array.put(json);
					minute++;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					minute++;
					continue;
				}
			}
		}

		return array;
	}

	private JSONObject formCode(Code attendence, int minute) throws JSONException {

		JSONObject json = new JSONObject();
		json.put("timestamp", minute);
		json.put("source", attendence.getPro_name());
		json.put("lineCount", attendence.getLine_count());
		json.put("noteCount", attendence.getNote_count());
		json.put("methodCount", attendence.getMethod_count());
		json.put("varyCount", attendence.getVar_count());
		json.put("maxCyclomaticCpl", attendence.getMax_cyclomaticcpl());

		return json;
	}

	@Override
	public JSONArray getAvgCodeRecord(String proName, int examID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStuCode(int stu_id, int time, int exam_id, String problem_name , String fileName) {
		// TODO Auto-generated method stub
		int classMemberId = examService.getClassMemberId(stu_id, exam_id);
		
		return getStuCodeByClassMemId(classMemberId, time, exam_id, problem_name, fileName);
	}
	


	@Override
	public List<String> getAllProNames(int examId) {
		// TODO Auto-generated method stub
		
		return codeDAO.queryAllProNames(examId);
	}

	@Override
	public List<String> getStuFileNamesByStuId(int stu_id, int exam_id, String problem_name) {
		// TODO Auto-generated method stub
		
		int classMemberId = examService.getClassMemberId(stu_id, exam_id);
		
		return getStuFileNamesByClassMemId(classMemberId, exam_id, problem_name);
	}

	@Override
	public List<String> getStuFileNamesByClassMemId(int classMemId, int exam_id, String problem_name) {
		// TODO Auto-generated method stub
		List<String> result = codeDAO.queryStuFileNames(exam_id, classMemId, problem_name);
		
		for(int i=0 ; i<result.size() ; ++i){
			String str = result.get(i);
			str = str.split("/" , 2)[1];
			result.set(i, str);
		}
		
		return result;
	}

	@Override
	public String getStuCodeByClassMemId(int classMemId, int time, int exam_id, String problem_name, String fileName) {
		
//		String key = exam_id+seperator+ problem_name+seperator+classMemId+seperator+fileName;
		
		Map<Integer, Code> codeMap = cacheCodeMap(classMemId , problem_name , exam_id , fileName);
//		if(!codeCache.containsKey(key)){
//			cacheCodeMap(classMemId, problem_name, exam_id, fileName);
//		}
//		Map<Integer , Code> codeMap = codeCache.get(key);
		Code code = codeMap.get(time);
		long timestamp = code.getTimestamp();
		
		Iterator<String> codes = reader.readJava(timestamp,problem_name+"/"+fileName, classMemId, exam_id);
		
		StringBuffer sBuffer = new StringBuffer();
		
		while(codes.hasNext()){
			sBuffer.append(codes.next());
			sBuffer.append("\t\n");
		}
		
		String result = sBuffer.toString();
		
		return result;
		
	}

}
