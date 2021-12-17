package com.jh.study.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jh.study.model.biz.MemberBiz;
import com.jh.study.model.dto.MemberDto;

@Controller
public class MemberController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberBiz biz;
	
	// security-context 에 만들어두었다.
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/loginform.do")
	public String loginForm() {
		logger.info("[Controller] loginform.do");
		
		return "memberlogin";
	}
	
	@ResponseBody
	@RequestMapping(value="/ajaxlogin.do", method=RequestMethod.POST)
	public Map<String, Boolean> ajaxLogin(@RequestBody MemberDto dto, HttpSession session) {
		
		
		/*
		 
		 @RequestBody : request 객체의 body에 저장되어 넘어오는 데이터 -> java object에 binding
		 (request 객체 body 데이터를 자바 객체로 전달 받기)
		 
		 @ResponseBody : java object -> response 객체의 body에 binding
		 (자바 객체를 response 객체의 바디로 전송)
		 
		 1. request 바디에서 id, pw를 보냄
		 2. controller 까지 값이 넘어옴
		 	원래는 String으로 하나하나 값을 받아줘야 했는데 자바 객체(dto)에 자동으로 값을 넣어줌
		 	RequestBody 사용 시 문자열이 아닌 것(자바가 아닌 것)을 자바 객체로 넣어준다.
		 3. @ResponseBody 로 자바객체를 response 객체에 바로 넣어서 받아 준다.
		 	이 때, jackson이 response 객체에 담겨져 있는 자바 객체를 json형태로 만들어 준다.
		 4. requestBody에 json객체(문자열이 아닌 타입의 값)이 왔는데 이 객체를 @Requestbody가 자바 객체로 바꿔준다.
		 	*그래서 ajax에서 contentType이 필요했던 것!
		 	
		 5.	객체가 그냥 넘어가면 주소값이 담겨져서 간다. jackson이 map에 담긴 객체를 json으로 바꿔준다.
		 	gson 객체 만들고 ~~ 했던 것들을 jackson이 알아서 해줌
		 	
		 
		 리퀘스트 객체의 바디에 담겨오는 객체를 dto에 넣어준다.(파라미터)
		 자바 객체 Map을 ModelAndView 거쳐서 ViewResolver 거쳐서 뷰를 찾아서 응답해주는 게 아니라
		 `ResponseBody`에 받아서 바로 전달해준다. -> Controller에서 바로 ResponseBody에 받아서 클라이언트에게 값 전달
		 
		 */
		
		logger.info("[Controller] ajaxlogin.do");
		
		MemberDto res = biz.login(dto);
		boolean check = false;
		
		if (res != null) {
			check = true;
			// 성공하면 세션에 담기
			session.setAttribute("login", res);
		}
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("check", check);
		
		return map;
	}
	
	@RequestMapping("/registerform.do")
	public String registerForm() {
		return "memberinsert";
	}
	
	@RequestMapping("register.do")
	public String registerRes(MemberDto dto) {
		
		System.out.println("암호화 전 : " + dto.getMemberpw());
		dto.setMemberpw(passwordEncoder.encode(dto.getMemberpw()));
		System.out.println("암호화 후 : " + dto.getMemberpw());
		
		if(biz.insert(dto) > 0) {
			return "redirect:loginform.do";
		}
		
		return "redirect:registerform.do";
	}
}