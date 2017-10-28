package org.egov.lcms.controller;

import javax.validation.Valid;

import org.egov.common.contract.request.RequestInfo;
import org.egov.lcms.config.PropertiesManager;
import org.egov.lcms.factory.ResponseFactory;
import org.egov.lcms.models.AdvocatePaymentRequest;
import org.egov.lcms.models.AdvocatePaymentResponse;
import org.egov.lcms.models.AdvocatePaymentSearchCriteria;
import org.egov.lcms.service.AdvocatePaymentService;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * 
 * @author Shubham Pratap
 *
 */
@RestController
@RequestMapping("/legalcase/advocatepayment")
public class AdvocatePaymentController {

	
	@Autowired
	AdvocatePaymentService advocatePaymentService;
	
	@Autowired
	ResponseFactory responseInfoFactory;
	
	@Autowired
	PropertiesManager propertiesManager;
	
	
	@RequestMapping(path="/_create",method=RequestMethod.POST)
	public ResponseEntity<?> createAdvocatePayment(@RequestBody @Valid AdvocatePaymentRequest advocatePaymentRequest) throws Exception{

		AdvocatePaymentResponse advocatePaymentResponse = advocatePaymentService.createAdvocatePayment(advocatePaymentRequest);
	
		return new ResponseEntity<>(advocatePaymentResponse, HttpStatus.CREATED);
	}
	
	@RequestMapping(path="/_update",method=RequestMethod.POST)
	public ResponseEntity<?> updateAdvocatePayment(@RequestBody @Valid AdvocatePaymentRequest advocatePaymentRequest ) throws Exception{

		AdvocatePaymentResponse advocatePaymentResponse = advocatePaymentService.updateAdvocatePayment(advocatePaymentRequest);
	
		return new ResponseEntity<>(advocatePaymentResponse, HttpStatus.CREATED);
	}
	
	/**
	 * Search method for AdvocatePayment service.
	 * 
	 * @param requestInfo
	 * @param advocatePaymentSearchCriteria
	 * @return
	 */
	@RequestMapping(path = "/_search", method = RequestMethod.POST)
	public ResponseEntity<?> searchAdvocatePayment(@RequestBody @Valid RequestInfo requestInfo,
			@ModelAttribute @Valid AdvocatePaymentSearchCriteria advocatePaymentSearchCriteria) {
		
		if(advocatePaymentSearchCriteria.getTenantId() == null ){
			throw new CustomException(propertiesManager.getTenantMandatoryCode(), propertiesManager.getTenantMandatoryMessage());
		}
		return new ResponseEntity<>(advocatePaymentService.searchAdvocatePayment(requestInfo, advocatePaymentSearchCriteria), HttpStatus.OK);
	}
	
}