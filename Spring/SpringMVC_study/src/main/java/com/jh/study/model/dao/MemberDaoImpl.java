package com.jh.study.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jh.study.model.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberDto login(MemberDto dto) {
		MemberDto res = null;
		try {
			res = sqlSession.selectOne(NAMESPACE + "login", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int insert(MemberDto dto) {
		int res = 0;
		try {
			res = sqlSession.insert(NAMESPACE + "insert", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
