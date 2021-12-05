package com.jh.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jh.study.model.biz.MyBoardBiz;

@Controller
public class MyBoardController {
	
	@Autowired
	private MyBoardBiz biz;
	
	/*
	 Spring Model 객체
	 
	 Controller의 메서드는 Model 타입의 객체를 파라미터로 받을 수 있다.
	 jsp에 컨트롤러에서 생성된 데이터를 담아서 전달하는 역할을 하는 존재.
	 request.setAttribute()와 비슷한 역할을 한다!
	 */
	
	@RequestMapping("/list.do")
	public String selectList(Model model) {
		model.addAttribute("list", biz.selectList());
		
		return "myboardlist";
	}
	
	@RequestMapping("/select.do")
	public String selectOne(Model model, int myno) {
		model.addAttribute("dto", biz.selectOne(myno));
		
		return "myboardselect";
	}

}
