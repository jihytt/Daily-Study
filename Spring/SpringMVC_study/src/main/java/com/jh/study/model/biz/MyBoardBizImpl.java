package com.jh.study.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	// insert 도중 에러가 나서 롤백하면 처음으로 돌아가야 한다.
	@Transactional
	@Override
	public String test() {
		
		dao.insert(new MyBoardDto(0, "transaction", "test", "insert", null));
		
		String test = dao.test();
		test.length(); // java.lang.NullPointerException 뜬다

		return test;
	}

}
