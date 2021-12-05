package com.jh.study.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jh.study.model.dao.MyBoardDao;
import com.jh.study.model.dto.MyBoardDto;

@Service
public class MyBoardBizImpl implements MyBoardBiz {

	@Autowired
	private MyBoardDao dao;
	
	@Override
	public List<MyBoardDto> selectList() {
		return dao.selectList();
	}

	@Override
	public MyBoardDto selectOne(int myno) {
		return dao.selectOne(myno);
	}

	@Override
	public int insert(MyBoardDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(MyBoardDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int myno) {
		return dao.delete(myno);
	}

}
