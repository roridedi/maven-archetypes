package com.rhc.customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.UserTaskService;
import org.jbpm.services.api.model.DeploymentUnit;
import org.jbpm.services.api.model.ProcessInstanceDesc;

import com.rhc.aggregates.Customer;
import com.rhc.entities.Address;
import com.rhc.services.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	
	private static final String CUSTOMER_ONBOARD_PROCESS_ID = "com.rhc.customer.CustomerOnboard";

	private static final String GROUP_ID = "com.rhc";
	private static final String ARTIFACT_ID = "bpmsuite-monolith-knowledge";
	private static final String VERSION = "6.2.0-SNAPSHOT";
	private static final DeploymentUnit DEPLOYMENT_UNIT = new KModuleDeploymentUnit(GROUP_ID, ARTIFACT_ID, VERSION);

	private ProcessService processService;
	private RuntimeDataService runtimeDataService;
	private DeploymentService deploymentService;
	private UserTaskService userTaskService;

	@Override
	public Long startCustomerOnboardProcess(Customer customer) {

		ensureCustomerKieJarIsDeploy();

		Map<String, Object> processVariables = new HashMap<String, Object>();
		processVariables.put("Customer",customer);
		
		processVariables.put("Address", customer.getAddress());
		

		Long processId = processService.startProcess(DEPLOYMENT_UNIT.getIdentifier(), CUSTOMER_ONBOARD_PROCESS_ID, processVariables);
		return processId;
	}

	@Override
	public int getNumberOfCustomerOnboardProcessesInProgress() {
		// TODO switch the query to test the process status
		Collection<ProcessInstanceDesc> processList = runtimeDataService.getProcessInstancesByProcessDefinition(CUSTOMER_ONBOARD_PROCESS_ID, null);
		return processList.size();
	}
	
	@Override
	public boolean isProcessComplete(Long processId) {
		ProcessInstanceDesc instanceDesc = runtimeDataService.getProcessInstanceById(processId);
		return instanceDesc.getState().equals( new Integer(2) );
	}
	
	@Override
	public void addAddress(Long processId, Address address) {
		List<Long> taskIds = runtimeDataService.getTasksByProcessInstanceId(processId);
		
		 userTaskService.claim(taskIds.get(0), "jboss");
		 userTaskService.start(taskIds.get(0), "jboss");
		 
		 Map<String,Object> taskData = new HashMap<String, Object>();
		 taskData.put("out_Address", address);
		 
		 userTaskService.complete(taskIds.get(0), "jboss", taskData);
		
	}

	public void ensureCustomerKieJarIsDeploy() {
		//KieServices.Factory.get().getRepository().addKieModule(kModule);
	
		
		if (!deploymentService.isDeployed(DEPLOYMENT_UNIT.getIdentifier())) {
			deploymentService.deploy(DEPLOYMENT_UNIT);
		}
	}

	public ProcessService getProcessService() {
		return processService;
	}

	public void setProcessService(ProcessService processService) {
		this.processService = processService;
	}

	public RuntimeDataService getRuntimeDataService() {
		return runtimeDataService;
	}

	public void setRuntimeDataService(RuntimeDataService runtimeDataService) {
		this.runtimeDataService = runtimeDataService;
	}

	public DeploymentService getDeploymentService() {
		return deploymentService;
	}

	public void setDeploymentService(DeploymentService deploymentService) {
		this.deploymentService = deploymentService;
	}

	public UserTaskService getUserTaskService() {
		return userTaskService;
	}

	public void setUserTaskService(UserTaskService userTaskService) {
		this.userTaskService = userTaskService;
	}





}
