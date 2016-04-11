package pkg.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pkg.dao.CommonTestDAO;
import pkg.dao.DBOperation;
import pkg.entity.CommonTest;

@Repository
public class CommonTestDAOImpl implements CommonTestDAO {

	@Autowired
	private DBOperation dbopt;

	@Override
	public CommonTest addCommonTest(int examId, String proName) {
		// TODO Auto-generated method stub
		CommonTest common = new CommonTest();
		common.setExam_id(examId);
		common.setPro_name(proName);

		dbopt.save(common);

		return common;
	}

	@Override
	public CommonTest queryCommonTest(int examId, String proName) {
		// TODO Auto-generated method stub
		String hql = "from CommonTest as common where common.exam_id=? and common.pro_name=?";

		@SuppressWarnings("unchecked")
		List<CommonTest> common = dbopt.findList(hql, examId, proName);

		if (common.size() >= 1) {
			return common.get(0);
		}

		return null;
	}

	@Override
	public List<String> queryCommonTestNames(int examId, String proName) {
		// TODO Auto-generated method stub
//		String hql = "select TestName.test_name from TestName , CommonTest" +
//				" where TestName.common=CommonTest.common_id and CommonTest.exam_id=? and CommonTest.pro_name=?";
//		@SuppressWarnings("unchecked")
//		List<String> result = dbopt.findList(hql, examId , proName);
		
		return null;
	}

}
