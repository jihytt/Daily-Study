package com.jh.study.model.dao;

import java.util.List;

import com.jh.study.model.dto.MyBoardDto;

public interface MyBoardDao {
	
	String NAMESPACE = "spring.mvc.study.";
	
	public List<MyBoardDto> selectList();
	public MyBoardDto selectOne(int myno);
	public int insert(MyBoardDto dto);
	public int update(MyBoardDto dto);
	public int delete(int myno);

	public String test();
}
