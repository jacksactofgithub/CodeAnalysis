package pkg.service.impl;

import java.util.Iterator;
import java.util.List;

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
			Code attendence = generateAttendence(stamps.next() , stuID);
			codeDAO.addCode(attendence);
//			attList.add(attendence);
		}
		
		return 0;
	}
	
	private Code generateAttendence(CodeStamp stamp , int stuID){
		Code attendence = new Code();
		attendence.setStudent_id(stuID);
		attendence.setLine_count(stamp.getLineCount());
		attendence.setNote_count(stamp.getNoteCount());
		attendence.setMethod_count(stamp.getMethodCount());
		attendence.setVar_count(stamp.getVaryCount());
		attendence.setMax_cyclomaticcpl(stamp.getMaxCyc());
		attendence.setSecond((int) (stamp.getRelativeTime()/1000) );
		
		String proName = stamp.getSourceName().split("/")[0];
		attendence.setPro_name(proName);
		
		return attendence;
	}
	
//	private int getProIDByPath(String sourceName){
//		String proName = sourceName.split("\\.")[0];
//		
//		return 0;
//	}

	@Override
	public JSONArray getCodeRecord(int stuID, String proName) {
		// TODO Auto-generated method stub
		
		List<Code> list = codeDAO.queryCode(stuID, proName);
		
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
	
	public JSONArray getCodeJSON(Iterator<Code> code) {
		
		JSONArray array = new JSONArray();
		
		int count =0;		//第几分钟
		Code former = null;
		Code current = null;
		int interval = 0;
		while((code.hasNext())||((count*60)<current.getSecond())){
			JSONObject json;
			try {
				
				if(former == null){
					Code temp = code.next();
					json = formCode(temp , count);
					array.put(json);
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
					json = formCode(current , count);
					array.put(json);
					count++;
					interval = (count*60) - former.getSecond();
				}
				
				if((!code.hasNext()) &&((count*60 > current.getSecond()))){	//保证时间够
					json = formCode(current, count);
					array.put(json);
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

}
