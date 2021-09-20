package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Customer;

public class CustomerDao extends AbstractJpaDao <Long,Customer>{

	public CustomerDao() {
		super(Customer.class);
		
	}

}
