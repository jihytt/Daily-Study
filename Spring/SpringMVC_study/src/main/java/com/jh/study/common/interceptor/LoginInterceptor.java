package com.jh.study.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Dispatcher Servlet에서 Controller 넘어가는 사이에서 동작할 것
		// 특정 조건에 맞으면 넘어가고 특정 조건에 안맞으면 다른 데로 넘어가게 할 수 있다.
		logger.info("[Interceptor] : preHandle");
		
		//Spring 3.2이상부터는 servlet-context.xml에서 <exclude-mapping-path>를 통해 설정 가능하다.
		if(request.getRequestURI().contains("/loginform.do") ||
		   request.getRequestURI().contains("/ajaxlogin.do") ||
		   request.getSession().getAttribute("login") != null || // 세션에 로그인 값이 null이 아니라면
		   request.getRequestURI().contains("/test.do")
				) {
			// 위의 조건이 맞으면 보내준다.
			return true;
		}
		
		if (request.getSession().getAttribute("login") == null) {
			response.sendRedirect("loginform.do");
		}
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// 어디로 넘길건지
		// INFO : com.jh.study.common.interceptor.LoginInterceptor - Target View : myboardlist
		logger.info("[Interceptor] : postHandle");
		
		if (modelAndView != null) {
			logger.info("Target View : " + modelAndView.getViewName());
		}
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("[Interceptor] : afterCompletion");
		
	}

}
