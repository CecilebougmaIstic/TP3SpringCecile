package doctolib_service.data.jpa.aspectJ;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class TraceInvocationAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());	//private  Logger logger = Logger.getLogger(TraceInvocationAspect.class);

	  @Pointcut("execution(* doctolib_service..*Controller.*(..))")
	  public void methodLog() {}
	

	  @Before("methodLog()")
public void before(JoinPoint joinPoint){
	//Advice
	logger.info(" Check for user access ");
	logger.info(" Allowed execution for {}", joinPoint);
	System.out.println("Appel de %s avec %d paramètres%n"+
	        joinPoint.toShortString()+
	        joinPoint.getArgs().length);

} 

		 @AfterThrowing(pointcut = "methodLog()", throwing = "e")
		  public void exeptionLg(JoinPoint joinPoint, Throwable e) {
		    System.out.printf("Retour de %s avec une exception %s%n",
		                      joinPoint.toShortString(),
		                      e.getClass().getSimpleName());
		  }
			
	
}


