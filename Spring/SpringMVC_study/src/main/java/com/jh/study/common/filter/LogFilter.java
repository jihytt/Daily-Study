package com.jh.study.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFilter implements Filter {
	
	/*
	 
	 서버를 실행시켜 서블릿이 올라오는 동안 init()이 실행되고, 그 후에 doFilter 실행
	 스프링에서 필터는 DispatcherServlet 이전에 실행된다.
	 서블릿 종료 시 destory 실행
	 
	 */
	
	private Logger logger = LoggerFactory.getLogger(LogFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		
		String remoteAddr = req.getRemoteAddr(); // 클라이언트 (웹서버로 정보를 요청한 웹브라우저의) 컴퓨터 ID 주소
		String uri = req.getRequestURI(); // context경로 + servlet경로
		// 프로토콜 이름에서 HTTP요청의 첫 번째 줄에 있는 쿼리문자열까지 이 요청의 URL부분을 반환
		String url = req.getRequestURL().toString(); // 프로토콜+도메인+포트번호+context경로 + servlet경로
		// 클라이언트가 요청을 만드는데 사용한 url 재구성
		String queryString = req.getQueryString();
		
		String referer = req.getHeader("referer"); // 지정된 이름의 헤더값을 문자열로 반환
		// referer : 이전페이지 url, 이전 경로
		String agent = req.getHeader("User-Agent"); // 사용자 정보
		
		StringBuffer sb = new StringBuffer();
		sb.append("* remoteAddr : " + remoteAddr + "\n")
		  .append("* uri : " + uri + "\n")
		  .append("* url : " + url + "\n")
		  .append("* queryString : " + queryString + "\n")
		  .append("* referer : " + referer + "\n")
		  .append("* agent : " + agent);
		
		logger.info("LOG FILTER\n" + sb);
		
		// 요청을 하면 서버가 받아서 응답. 필터는 서버 밖에서 필터로 걸러주는 역할
		// 그 뒤에 다음 필터나 서블릿에 요청과 응답을 전해주는 게 chain.dofilter(필터체이닝)
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
