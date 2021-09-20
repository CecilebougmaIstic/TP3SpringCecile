package fr.istic.taa.jaxrs.dao.generic;

import java.io.Serializable;

import fr.istic.taa.jaxrs.domain.TypeOfAppointement;



public class TypeOfAppointementDao extends AbstractJpaDao <Long,TypeOfAppointement> {

	public TypeOfAppointementDao() {
		super(TypeOfAppointement.class);
		
	}
	

}
