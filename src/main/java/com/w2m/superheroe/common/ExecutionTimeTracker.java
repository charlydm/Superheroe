package com.w2m.superheroe.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeTracker {
	
	private static final Logger logger = Logger.getLogger(ExecutionTimeTracker.class);
	
	@Around("@annotation(com.w2m.superheroe.common.TrackExecutionTime)")
	public Object trackTime(ProceedingJoinPoint pjp) throws Throwable {
		long stratTime=System.currentTimeMillis();
		Object obj=pjp.proceed();
		long endTime=System.currentTimeMillis();
		logger.info("Method name: " + pjp.getSignature() + " time taken to execute : " + (endTime-stratTime) + " ms");
		return obj;
	}

}
