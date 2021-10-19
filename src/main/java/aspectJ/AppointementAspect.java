package aspectJ;



import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
/*
@Aspect
@Configuration

public class AppointementAspect {
private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	//What kind of method calls I would intercept
	//execution(* PACKAGE.*.*(..))
	//Weaving & Weaver
	@Before("execution(* package doctolib_service.data.jpa.web.AppointementController.*.*(..))")
	public void before(JoinPoint joinPoint){
		//Advice
		logger.info(" Check for user access ");
		logger.info(" Allowed execution for {}");
	}
}
*/

