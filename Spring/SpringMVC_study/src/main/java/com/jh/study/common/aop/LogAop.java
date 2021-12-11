package com.jh.study.common.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAop {
	
	public void before(JoinPoint join) {
		Logger logger = LoggerFactory.getLogger(join.getTarget() + ""); // 대상 객체(연결될 cc)
		logger.info("---------AOP Start---------");
		
		Object[] args = join.getArgs(); // 대상 아규먼트 (넘어가는 값이 있는지)
		if (args != null) {
			// 만일 있으면 대상 메서드 정보 가져와서 몇번째 아규먼트가 넘어가는거 반복해서 출력
			logger.info("method : " + join.getSignature().getName());
			for (int i = 0; i < args.length; i++) {
				logger.info(i + "번째 : " + args[i]);
			}
		}
		
	}
	
	public void after(JoinPoint join) {
		Logger logger = LoggerFactory.getLogger(join.getTarget() + "");
		logger.info("-------AOP End-------");
		
	}
	
	public void afterThrowing(JoinPoint join) {
		Logger logger = LoggerFactory.getLogger(join.getTarget() + "");
		logger.info("-------AOP Error-------");
		logger.info("ERROR : " + join.getArgs());
		logger.info("ERROR : " + join.toString());
	}

}
