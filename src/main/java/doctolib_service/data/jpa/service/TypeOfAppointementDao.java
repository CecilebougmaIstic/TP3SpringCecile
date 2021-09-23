package doctolib_service.data.jpa.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import doctolib_service.data.jpa.domain.TypeOfAppointement;

public interface TypeOfAppointementDao extends JpaRepository <TypeOfAppointement, Long>{
	
	public List<TypeOfAppointement> findByAppointementDescription(String appointementDescription);
	public List<TypeOfAppointement> findByAppointementDescriptionContaining(String appointementDescription);
	public List<TypeOfAppointement> findByAppointementLimit(int appointementLimit);
	public List<TypeOfAppointement> findByAppointementDescriptionContainingAndAppointementLimitContaining(String appointementDescription, int appointementLimit);
	
	
}
