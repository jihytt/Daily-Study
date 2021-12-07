package com.jh.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jh.study.model.biz.MyBoardBiz;
import com.jh.study.model.dto.MyBoardDto;

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
	
	@RequestMapping("/insertform.do")
	public String insertform() {
		return "myboardinsert";
	}
	
	@RequestMapping("/insertres.do")
	public String insertres(MyBoardDto dto) {
		// Spring이 내부적으로 컨트롤러에 오기 전에 전달된 데이터를 dto에 넣어준다. (커맨드 객체) 
		if (biz.insert(dto) > 0) {
			// redirect를 붙이지 않으면 view에서 찾게 된다.
			return "redirect:list.do";
		}
		// 실패 시
		return "redirect:insertform.do";
	}
	
	@RequestMapping("/updateform.do")
	public String updateform(Model model, int myno) {
		
		model.addAttribute("dto", biz.selectOne(myno));
		
		return "myboardupdate";
	}
	
	@RequestMapping("/updateres.do")
	public String updateres(MyBoardDto dto) {
		
		if(biz.update(dto) > 0) {
			return "redirect:select.do?myno="+dto.getMyno();
		}
		
		return "redirect:updateform.do?myno="+dto.getMyno();
	}
	
	@RequestMapping("/delete.do")
	public String delete(int myno) {
		
		if (biz.delete(myno) > 0) {
			return "redirect:list.do";
		}
		
		return "redirect:select.do?myno="+myno;
	}

}
