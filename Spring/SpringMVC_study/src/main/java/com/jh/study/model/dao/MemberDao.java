package com.jh.study.model.dao;

import com.jh.study.model.dto.MemberDto;

public interface MemberDao {
	
	String NAMESPACE = "mymember.";
	
	public MemberDto login(MemberDto dto);
}
