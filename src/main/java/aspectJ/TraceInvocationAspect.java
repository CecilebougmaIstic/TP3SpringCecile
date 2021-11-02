package aspectJ;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;





@Aspect
@Configuration

public class TraceInvocationAspect implements Ordered{
private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	//private  Logger logger = Logger.getLogger(TraceInvocationAspect.class);
	private int order; 
		//What kind of method calls I would intercept
	//execution(* PACKAGE.*.*(..))
	//Weaving & Weaver
	//@Before

	//*package doctolib_service.data.jpa.web.*AppointementController.*(..))
	 @Pointcut("call(*package doctolib_service.data.jpa.web..*.*(..))")
	
	 public void tracePointCut() {
	  			}
	 
	 @Before("tracePointCut")
	 public Object logBefore(ProceedingJoinPoint joinPoint)throws Throwable {
		 this.logger(joinPoint);
		 System.out.println("Completed: " + joinPoint);
		 return joinPoint.proceed();
		 
	 }
	 
	 private void logger(ProceedingJoinPoint joinPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	  public int getOrder() {
	    return order;
	  }
	  
	  @Value("2")
	  public void setOrder(final int order) {
	    this.order = order;
	  }


	
}


