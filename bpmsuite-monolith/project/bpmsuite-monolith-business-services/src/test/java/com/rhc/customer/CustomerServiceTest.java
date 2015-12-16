package com.rhc.customer;

import org.junit.Assert;
import org.junit.Test;

import com.rhc.aggregates.Customer;
import com.rhc.services.AbstractBusinessServicesTest;

public class CustomerServiceTest extends AbstractBusinessServicesTest{
	
	@Test
	public void shouldSuccessfullyStartACustomerOnboardProcess(){
		Customer c= new Customer();
		c.setFirstName("Joe");
		c.setLastName("Wohar");
		// given
		Assert.assertNotNull(customerService);
		
		// when 
		Long processId = customerService.startCustomerOnboardProcess(c);
		
		// then
		Assert.assertEquals( new Long(1), processId);
		Assert.assertEquals( 1, customerService.getNumberOfCustomerOnboardProcessesInProgress());
	}
	
}
