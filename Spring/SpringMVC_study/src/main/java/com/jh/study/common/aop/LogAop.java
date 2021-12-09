package com.jh.study.common.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAop {
	
	public void before(JoinPoint join) {
		Logger logger = LoggerFactory.getLogger(join.getTarget() + ""); // 대상 객체(연결될 cc)
		
	}
	
	public void after(JoinPoint join) {
		
	}
	
	public void afterThrowing(JoinPoint join) {
		
	}

}
