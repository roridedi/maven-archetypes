package com.rhc.customer;

import java.io.File;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;

import com.rhc.aggregates.Customer;
import com.rhc.entities.Address;
import com.rhc.entities.Country;
import com.rhc.services.AbstractBusinessServicesTest;

public class CustomerServiceTest extends AbstractBusinessServicesTest{
	
	@BeforeClass
	public static void init(){
		// ensure the latest version of the KieJar is on the classpath
		KieServices.Factory.get().newKieBuilder( new File(getKieJarPath()) ).buildAll();
	}
	
	@Test
	public void shouldSuccessfullyStartACustomerOnboardProcess(){
		Customer c= new Customer();
		c.setFirstName("Joe");
		c.setLastName("Wohar");
		Address address= new Address();
		address.setCity("Lagos");
		address.setCountry(Country.NIGERIA);
		address.setNumber("4");
		address.setStreetName("Lagos Street");
		c.setAddress(address);
		// given
		Assert.assertNotNull(customerService);
		
		// when 
		Long processId = customerService.startCustomerOnboardProcess(c);
		
		// then
		Assert.assertEquals( new Long(1), processId);
		Assert.assertEquals( 1, customerService.getNumberOfCustomerOnboardProcessesInProgress());
	}
	
	
	public static String getKieJarPath(){
		String dir = System.getProperty("user.dir");
		return dir.substring(0, dir.lastIndexOf("/")) + "/bpmsuite-monolith-knowledge";
	}
}
