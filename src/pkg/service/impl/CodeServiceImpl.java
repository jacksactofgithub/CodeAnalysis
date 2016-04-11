package pkg.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lmooc.modulize.bean.CodeStamp;
import pkg.dao.CodeDAO;
import pkg.entity.Code;
import pkg.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService{
	
	@Autowired
	private CodeDAO codeDAO;
	
	@Override
	public int saveStamps(Iterator<CodeStamp> stamps, int stuID, int exam) {
		// TODO Auto-generated method stub
		
//		List<Attendence> attList = new ArrayList<Attendence>();
		
		while(stamps.hasNext()){
			Code attendence = generateAttendence(stamps.next() , stuID , exam);
			codeDAO.addCode(attendence);
		}
		
		return 0;
	}
	
	private Code generateAttendence(CodeStamp stamp , int stuID , int exam){
		Code attendence = new Code();
		attendence.setStudent_id(stuID);
		attendence.setExam_id(exam);
		attendence.setLine_count(stamp.getLineCount());
		attendence.setNote_count(stamp.getNoteCount());
		attendence.setMethod_count(stamp.getMethodCount());
		attendence.setVar_count(stamp.getVaryCount());
		attendence.setMax_cyclomaticcpl(stamp.getMaxCyc());
		attendence.setSecond((int) (stamp.getRelativeTime()/1000) );
		attendence.setTimestamp(stamp.getTimestamp());
		
		String proName = stamp.getSourceName().split("/")[0];
		attendence.setPro_name(proName);
		
		return attendence;
	}

	@Override
	public JSONArray getCodeRecord(int stuID, String proName , int exam) {
		// TODO Auto-generated method stub
		List<Code> list = codeDAO.queryCode(stuID, proName , exam);
		
		Iterator<Code> it = list.iterator();
		
		while(it.hasNext()){
			Code code = it.next();
			if(!code.getPro_name().equals(proName)){
				list.remove(code);
			}
		}
		JSONArray array = getCodeJSON(list.iterator());
		
		return array;
	}
	
	/**
	 * 将每一分钟对应至一个code
	 * @return
	 */
	private Map<Integer , Code> mapTime(Iterator<Code> code){
		Map<Integer , Code> map = new HashMap<Integer , Code>();
		if((code == null)||(!code.hasNext())){
			return map;
		}
		
		int count =0;		//第几分钟
		Code former = null;
		Code current = null;
		int interval = 0;
		while((code.hasNext())||((count*60)<current.getSecond())){
			if(former == null){
				Code temp = code.next();
				map.put(count, temp);
				former = temp;
				if(code.hasNext()){
					current = code.next();
				}else{
					break;
				}
				count++;
				interval = (count*60);
				continue;
			}
			
			if(former.getSecond() == current.getSecond()){
				former = current;
				if(code.hasNext()){
					current = code.next();
				}else{
					interval = 0;
				}
			}
			
			int currentInterval = calInterval((count*60) , current.getSecond());
			if(currentInterval < interval){
				interval = currentInterval;
				former = current;
				if(code.hasNext()){
					current = code.next();
				}else{
					interval = 0;
				}
			}else{
				map.put(count, former);
				count++;
				interval = (count*60) - former.getSecond();
			}
			
			if((!code.hasNext()) &&((count*60 > current.getSecond()))){	//保证时间够
				map.put(count, current);
			}
				
		}
		
		return map;
	}
	
	public JSONArray getCodeJSON(Iterator<Code> code) {
		JSONArray array = new JSONArray();
		
		Map<Integer , Code> codeMap = mapTime(code);
		
		Iterator<Entry<Integer , Code>> codeIt = codeMap.entrySet().iterator();
		
		while(codeIt.hasNext()){
			Entry<Integer , Code> entry = codeIt.next();
			try {
				JSONObject json = formCode(entry.getValue(), entry.getKey());
				array.put(json);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return array;
			}
			
		}
		
		return array;
	}
	
	private int calInterval(int timeOne , int timeTwo){
		int result = timeOne - timeTwo;
		if(timeOne < timeTwo){
			result = 0 - result;
		}
		return result;
	}
	
	private JSONObject formCode(Code attendence ,int minute) throws JSONException{
		
		JSONObject json = new JSONObject();
		json.put("timestamp", minute);
		json.put("source", attendence.getPro_name());
		json.put("lineCount", attendence.getLine_count());
		json.put("noteCount" , attendence.getNote_count());
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

}
