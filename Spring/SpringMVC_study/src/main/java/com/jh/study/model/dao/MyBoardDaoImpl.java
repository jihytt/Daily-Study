package com.jh.study.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jh.study.model.dto.MyBoardDto;
@Repository
public class MyBoardDaoImpl implements MyBoardDao {

	// Mybatis와 연결
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<MyBoardDto> selectList() {
		List<MyBoardDto> list = new ArrayList<MyBoardDto>();
		
		try {
			list = sqlSession.selectList(NAMESPACE + "selectList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public MyBoardDto selectOne(int myno) {
		MyBoardDto dto = null;
		try {
			dto = sqlSession.selectOne(NAMESPACE+"selectOne", myno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public int insert(MyBoardDto dto) {
		int res = 0;
		try {
			res = sqlSession.insert(NAMESPACE+"insert", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int update(MyBoardDto dto) {
		int res = 0;
		try {
			sqlSession.update(NAMESPACE+"update", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int delete(int myno) {
		int res = 0;
		try {
			res = sqlSession.delete(NAMESPACE+"delete", myno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
