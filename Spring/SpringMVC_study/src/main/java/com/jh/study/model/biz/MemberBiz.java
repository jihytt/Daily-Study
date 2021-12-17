package com.jh.study.model.biz;

import com.jh.study.model.dto.MemberDto;

public interface MemberBiz {
	
	public MemberDto login(MemberDto dto);
	
	public int insert(MemberDto dto);

}
