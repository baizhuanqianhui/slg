package com.slj.core.common.dao.impl;

import java.util.HashMap;
import java.util.Map;






import org.mybatis.spring.SqlSessionTemplate;

import com.slj.core.common.dao.BaseDao;

public class BaseDaoImpl implements BaseDao{
	public SqlSessionTemplate sqlSessionTemplate;
	public Map<Integer,String> sqlMap = new HashMap<Integer,String>();
	public void executeMultiSQL() {
		for (Integer key : sqlMap.keySet()) {
			String sqlVal = sqlMap.get(key);
			switch (key) {
			case 1:
				sqlSessionTemplate.insert(sqlVal);
				break;
			case 2:
				sqlSessionTemplate.delete(sqlVal);
				break;
			case 3:
				sqlSessionTemplate.update(sqlVal);
				break;
			default:
				break;
			}
		}
	}
	public void setSqlMap(Map<Integer, String> sqlMap) {
		this.sqlMap = sqlMap;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}
