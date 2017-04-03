package sample.web.ui.Service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Ids van der Zee on 3-4-2017.
 */

@Aspect
public class Advice {

    @Pointcut("execution(* sample.web.ui.controller.*.*(..))")
    public void pointcutMethod(){

    }

    private static final Logger logger = LoggerFactory.getLogger(Advice.class);

    @Before("pointcutMethod()")
    public void beforeMethod() {
        logger.info("before method");
    }

    @After("pointcutMethod()")
    public void afterMethod() {
        logger.info("after method");
    }

    @Around("pointcutMethod()")
    public Object aroundMethod(ProceedingJoinPoint joinpoint) {
        try {
            long start = System.nanoTime();
            Object result = joinpoint.proceed();
            long end = System.nanoTime();
            logger.info(String.format("%s took %d ns", joinpoint.getSignature(), (end - start)));
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
