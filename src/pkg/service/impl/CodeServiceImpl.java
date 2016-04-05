package pkg.service.impl;

import java.util.Iterator;
import java.util.List;

import lmooc.modulize.bean.CodeStamp;
import pkg.dao.AttendenceDAO;
import pkg.dao.impl.AttendenceDAOImpl;
import pkg.entity.Attendence;
import pkg.service.CodeService;

public class CodeServiceImpl implements CodeService{
	
	private AttendenceDAO attDAO = new AttendenceDAOImpl();

	@Override
	public int saveStamps(Iterator<CodeStamp> stamps, int stuID, int exam) {
		// TODO Auto-generated method stub
		
//		List<Attendence> attList = new ArrayList<Attendence>();
		
		while(stamps.hasNext()){
			Attendence attendence = generateAttendence(stamps.next() , stuID);
			attDAO.addAttendence(attendence);
//			attList.add(attendence);
		}
		
		return 0;
	}
	
	private Attendence generateAttendence(CodeStamp stamp , int stuID){
		Attendence attendence = new Attendence();
		attendence.setStudent_id(stuID);
		attendence.setLine_count(stamp.getLineCount());
		attendence.setNote_count(stamp.getNoteCount());
		attendence.setMethod_count(stamp.getMethodCount());
		attendence.setVar_count(stamp.getVaryCount());
		attendence.setMax_cyclomaticcpl(stamp.getMaxCyc());
		attendence.setSecond((int) (stamp.getRelativeTime()/1000) );
		
		String proName = stamp.getSourceName().split("\\.")[0];
		attendence.setPro_name(proName);
		
		return attendence;
	}
	
//	private int getProIDByPath(String sourceName){
//		String proName = sourceName.split("\\.")[0];
//		
//		return 0;
//	}

	@Override
	public Iterator<Attendence> getStamps(int stuID, String proName) {
		// TODO Auto-generated method stub
		
		List<Attendence> list = attDAO.queryAttendences(stuID, proName);
		
		Iterator<Attendence> it = list.iterator();
		
		while(it.hasNext()){
			Attendence attendence = it.next();
			if(!attendence.getPro_name().equals(proName)){
				list.remove(attendence);
			}
		}
		
		return list.iterator();
	}

}
