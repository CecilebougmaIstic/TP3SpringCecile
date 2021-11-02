package doctolib_service.data.jpa.aspectJ;


import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.util.StopWatch;

@Aspect
public class MonitorePerfAspect  implements Ordered{
	
	private int order;
	
	 @Around("@annotation(supervision)")
	  public Object superviser(ProceedingJoinPoint joinPoint, Supervision supervision)
	                      throws Throwable {
	    long maxDuree = supervision.dureeMillis();
	    long start = System.currentTimeMillis();
	    try {
	      return joinPoint.proceed(joinPoint.getArgs());
	    } finally {
	      long end = System.currentTimeMillis();
	      long duree = end - start;
	      if (duree > maxDuree) {
	        System.out.printf("Attention l'appel à %s à durée %dms soit %dms de plus qu'attendu%n",
	                          joinPoint.toShortString(), duree, duree - maxDuree);
	      }
	    }
	  }
	 
	
	
	
	  @Override
	  public int getOrder() {
	    return order;
	  }
	
	 
	  @Value("1")
	  public void setOrder(final int order) {
	    this.order = order;
	  }

	
	
}
