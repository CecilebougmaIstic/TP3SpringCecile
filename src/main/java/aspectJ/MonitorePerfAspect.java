package aspectJ;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.util.StopWatch;

public class MonitorePerfAspect  implements Ordered{
	private static Logger logger = Logger.getLogger(MonitorePerfAspect.class);
	private int order;
	
	 @Around("monitorePerfPointcut()")
	  public Object executer(final ProceedingJoinPoint joinpoint) throws Throwable {
	    Object returnValue;
	    StopWatch clock = new StopWatch(getClass().getName());
	    
	    try {
	      clock.start(joinpoint.toString());
	      returnValue = joinpoint.proceed();
	    } finally {
	      clock.stop();
	      logger.info("temps d'execution : " + clock.prettyPrint());
	    }
	    return returnValue;
	  }
	 
	  @Override
	  public int getOrder() {
	    return order;
	  }
	 //"execution(* package doctolib_service.data.jpa.web.*WorkerController.*(..))
	  @Pointcut("execution(* package doctolib_service.data.jpa.web.*.*(..))")
	  public void monitorePerfPointcut() {
	  }
	 
	  @Value("1")
	  public void setOrder(final int order) {
	    this.order = order;
	  }

	
	
}
