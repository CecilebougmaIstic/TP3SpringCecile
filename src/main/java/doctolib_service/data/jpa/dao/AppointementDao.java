package doctolib_service.data.jpa.dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import doctolib_service.data.jpa.domain.Appointement;
import doctolib_service.data.jpa.domain.Customer;



public interface AppointementDao extends JpaRepository <Appointement, Long>{
	
	public final static String FIND_BY_ID_STATE = "SELECT aptment FROM Appointement aptment  " +
            "WHERE aptment.worker.id = :id";
            

@Query(FIND_BY_ID_STATE)
public List<Appointement> findAppointementByWorkerId
(@Param("id") Long id);

@Query(FIND_BY_ID_STATE)
public List<Appointement> findAppointementByCustomerId(@Param("id") Long id);

public List<Appointement> findByCustomer(Customer customerObject);

@Query("select a from Appointement a where "
		+ "((a.appointementStart BETWEEN :appointementStart AND :appointementEnd) OR"
		+"(a.appointementEnd BETWEEN :appointementStart AND :appointementEnd))"
		+ "and a.worker.id=:workerId")
//"select a from Appointement a where a.appointementStart = :appointementStart and a.worker.id=:workerId"
public Optional<Appointement> appointementAlreadyExistForAWorker(@Param("appointementStart") LocalDateTime appointementStart, @Param("appointementEnd") LocalDateTime appointementEnd, @Param("workerId") Long workerId);

}
