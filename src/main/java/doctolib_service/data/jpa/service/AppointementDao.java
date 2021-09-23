package doctolib_service.data.jpa.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import doctolib_service.data.jpa.domain.Appointement;



public interface AppointementDao extends JpaRepository <Appointement, Long>{
	
	public final static String FIND_BY_ID_STATE = "SELECT aptment FROM Appointement aptment  " +
            "WHERE aptment.worker.id = :id";
            

@Query(FIND_BY_ID_STATE)
public List<Appointement> findAppointementByWorkerId
(@Param("id") Long id);

}
