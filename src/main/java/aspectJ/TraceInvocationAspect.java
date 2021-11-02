package aspectJ;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;



@Aspect
@Configuration

public class TraceInvocationAspect implements Ordered{
//private Logger logger1 = (Logger) LoggerFactory.getLogger(this.getClass());
	private static Logger logger = Logger.getLogger(TraceInvocationAspect.class);
	private int order; 
		//What kind of method calls I would intercept
	//execution(* PACKAGE.*.*(..))
	//Weaving & Weaver
	//@Before
	
	/*public void before(JoinPoint joinPoint){
		//Advice
		logger.info(" Check for user access ");
		logger.info(" Allowed execution for {}");
	}*/
	 @Pointcut("call(*package doctolib_service.data.jpa.web.*AppointementController.*(..))")
	
	 public void tracePointCut() {
	  			}
	 
	 @Before("tracePointCut")
	 public Object logBefore(ProceedingJoinPoint joinPoint)throws Throwable {
		 this.logger(joinPoint);
		 System.out.println("Completed: " + joinPoint);
		 return joinPoint.proceed();
		 
	 }
	 
	 @Override
	  public int getOrder() {
	    return order;
	  }
	  
	  @Value("2")
	  public void setOrder(final int order) {
	    this.order = order;
	  }
	private void logger(ProceedingJoinPoint joinPoint) {
		// TODO Auto-generated method stub
		
	}

	
}


