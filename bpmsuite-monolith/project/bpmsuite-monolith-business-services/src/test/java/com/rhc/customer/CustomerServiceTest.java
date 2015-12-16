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
	public void shouldSuccessfullyStartACustomerOnboardProcessCompleteHumantTaskAndCompleteProcess(){

		// given
		Customer c= new Customer();
		c.setFirstName("Joe");
		c.setLastName("Wohar");
		
		// when I start a process
		Long processId = customerService.startCustomerOnboardProcess(c);
		
		// when I complete a human task
		// TODO
		Address address= new Address();
		address.setCity("Lagos");
		address.setCountry(Country.NIGERIA);
		address.setNumber("4");
		address.setStreetName("Lagos Street");
		customerService.addAddress(processId, address);
		
		
		// then  ensure process is complete
		Assert.assertTrue( customerService.isProcessComplete(processId) );
	}
	
	
	public static String getKieJarPath(){
		String dir = System.getProperty("user.dir");
		return dir.substring(0, dir.lastIndexOf("/")) + "/bpmsuite-monolith-knowledge";
	}
}
