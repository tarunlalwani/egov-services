package org.egov.egf.persistence.repository;

import java.util.HashMap;

import org.egov.egf.persistence.queue.contract.BankBranchContractRequest;
import org.egov.egf.producer.FinancialProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BankBranchQueueRepository {

	@Autowired
	private FinancialProducer financialProducer;

	@Value("${kafka.topics.egf.masters.validated.topic}")
	private String bankBranchValidatedTopic;

	@Value("${kafka.topics.egf.masters.bankbranch.validated.key}")
	private String bankBranchValidatedKey;

	public void push(BankBranchContractRequest bankBranchContractRequest) {
		HashMap<String, Object> bankBranchContractRequestMap = new HashMap<String, Object>();

		if ("create".equalsIgnoreCase(bankBranchContractRequest.getRequestInfo().getAction()))
			bankBranchContractRequestMap.put("BankBranchCreate", bankBranchContractRequest);
		else if ("updateAll".equalsIgnoreCase(bankBranchContractRequest.getRequestInfo().getAction()))
			bankBranchContractRequestMap.put("BankBranchUpdateAll", bankBranchContractRequest);
		else if ("update".equalsIgnoreCase(bankBranchContractRequest.getRequestInfo().getAction()))
			bankBranchContractRequestMap.put("BankBranchUpdate", bankBranchContractRequest);

		financialProducer.sendMessage(bankBranchValidatedTopic, bankBranchValidatedKey, bankBranchContractRequestMap);
	}
}
