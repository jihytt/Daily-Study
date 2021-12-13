package com.jh.study.model.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jh.study.model.dao.MemberDao;
import com.jh.study.model.dto.MemberDto;

@Service
public class MemberBizImpl implements MemberBiz {

	@Autowired
	private MemberDao dao;
	
	@Override
	public MemberDto login(MemberDto dto) {

		return dao.login(dto);
	}

}
