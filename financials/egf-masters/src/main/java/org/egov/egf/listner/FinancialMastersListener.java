package org.egov.egf.listner;

import java.util.HashMap;

import org.egov.egf.persistence.queue.contract.BankAccountContractResponse;
import org.egov.egf.persistence.queue.contract.BankBranchContractResponse;
import org.egov.egf.persistence.queue.contract.BankContractResponse;
import org.egov.egf.persistence.service.BankAccountService;
import org.egov.egf.persistence.service.BankBranchService;
import org.egov.egf.persistence.service.BankService;
import org.egov.egf.producer.FinancialProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FinancialMastersListener {

	@Value("${kafka.topics.egf.masters.completed.topic}")
	private String completedTopic;

	@Value("${kafka.topics.egf.masters.bank.completed.key}")
	private String bankCompletedKey;

	@Value("${kafka.topics.egf.masters.bankbranch.completed.key}")
	private String bankBranchCompletedKey;

	@Value("${kafka.topics.egf.masters.bankaccount.completed.key}")
	private String bankAccountCompletedKey;

	private BankService bankService;

	private BankBranchService bankBranchService;

	private BankAccountService bankAccountService;

	@Autowired
	private FinancialProducer financialProducer;

	@Autowired
	public FinancialMastersListener(BankService bankService, BankBranchService bankBranchService,
			BankAccountService bankAccountService) {
		this.bankService = bankService;
		this.bankBranchService = bankBranchService;
		this.bankAccountService = bankAccountService;
	}

	@KafkaListener(id = "${kafka.topics.egf.masters.validated.id}", topics = "${kafka.topics.egf.masters.validated.topic}", group = "${kafka.topics.egf.masters.validated.group}")
	public void process(HashMap<String, Object> financialContractRequestMap) {

		if (financialContractRequestMap.get("BankCreate") != null) {
			BankContractResponse bankContractResponse = bankService.create(financialContractRequestMap);
			financialProducer.sendMessage(completedTopic, bankCompletedKey, bankContractResponse);
		}

		if (financialContractRequestMap.get("BankUpdate") != null) {
			BankContractResponse bankContractResponse = bankService.update(financialContractRequestMap);
			financialProducer.sendMessage(completedTopic, bankCompletedKey, bankContractResponse);
		}

		if (financialContractRequestMap.get("BankBranchCreate") != null) {
			BankBranchContractResponse bankBranchContractResponse = bankBranchService
					.create(financialContractRequestMap);
			financialProducer.sendMessage(completedTopic, bankBranchCompletedKey, bankBranchContractResponse);
		}

		if (financialContractRequestMap.get("BankBranchUpdate") != null) {
			BankBranchContractResponse bankBranchContractResponse = bankBranchService
					.update(financialContractRequestMap);
			financialProducer.sendMessage(completedTopic, bankBranchCompletedKey, bankBranchContractResponse);
		}

		if (financialContractRequestMap.get("BankAccountCreate") != null) {
			BankAccountContractResponse bankAccountContractResponse = bankAccountService
					.create(financialContractRequestMap);
			financialProducer.sendMessage(completedTopic, bankAccountCompletedKey, bankAccountContractResponse);
		}

		if (financialContractRequestMap.get("BankAccountUpdate") != null) {
			BankAccountContractResponse bankAccountContractResponse = bankAccountService
					.update(financialContractRequestMap);
			financialProducer.sendMessage(completedTopic, bankAccountCompletedKey, bankAccountContractResponse);
		}

	}

}
