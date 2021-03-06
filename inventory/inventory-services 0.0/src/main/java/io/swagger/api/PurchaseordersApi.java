/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.0-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import java.math.BigDecimal;
import io.swagger.model.ErrorRes;
import io.swagger.model.PurchaseOrderRequest;
import io.swagger.model.PurchaseOrderResponse;
import io.swagger.model.RequestInfo;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-28T13:21:55.964+05:30")

@Api(value = "purchaseorders", description = "the purchaseorders API")
public interface PurchaseordersApi {

    @ApiOperation(value = "Create  new  purchaseorders", nickname = "purchaseordersCreatePost", notes = "Create  new purchaseorders", response = PurchaseOrderResponse.class, tags={ "Inventory","PurchaseOrder", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "PurchaseOrder created Successfully", response = PurchaseOrderResponse.class),
        @ApiResponse(code = 400, message = "Invalid Input", response = ErrorRes.class) })
    @RequestMapping(value = "/purchaseorders/_create",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<PurchaseOrderResponse> purchaseordersCreatePost( @NotNull@ApiParam(value = "Unique id for a tenant.", required = true) @Valid @RequestParam(value = "tenantId", required = true) String tenantId,@ApiParam(value = "Create  new"  )  @Valid @RequestBody PurchaseOrderRequest purchaseOrderRequest, @RequestHeader(value = "Accept", required = false) String accept) throws Exception;


    @ApiOperation(value = "Get the list of purchaseorders", nickname = "purchaseordersSearchPost", notes = "purchaseorders", response = PurchaseOrderResponse.class, tags={ "Inventory","PurchaseOrder", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "PurchaseOrder retrieved Successfully", response = PurchaseOrderResponse.class),
        @ApiResponse(code = 400, message = "Invalid Input", response = ErrorRes.class) })
    @RequestMapping(value = "/purchaseorders/_search",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<PurchaseOrderResponse> purchaseordersSearchPost( @NotNull@ApiParam(value = "Unique id for a tenant.", required = true) @Valid @RequestParam(value = "tenantId", required = true) String tenantId,@ApiParam(value = "Parameter to carry Request metadata in the request body"  )  @Valid @RequestBody RequestInfo requestInfo, @Size(max=50)@ApiParam(value = "comma seperated list of Ids") @Valid @RequestParam(value = "ids", required = false) List<String> ids,@ApiParam(value = "store of the PurchaseOrder ") @Valid @RequestParam(value = "store", required = false) Long store,@ApiParam(value = "purchaseOrderNumber  Auto generated number, read only ") @Valid @RequestParam(value = "purchaseOrderNumber", required = false) String purchaseOrderNumber,@ApiParam(value = "purchase order date of the PurchaseOrder ") @Valid @RequestParam(value = "purchaseOrderDate", required = false) Long purchaseOrderDate,@ApiParam(value = "rate type of the PurchaseOrder ", allowableValues = "DIT, DGSD, Tender, Quotation") @Valid @RequestParam(value = "rateType", required = false) String rateType,@ApiParam(value = "supplier code of the PurchaseOrder ") @Valid @RequestParam(value = "supplierCode", required = false) String supplierCode,@ApiParam(value = "advance amount of the PurchaseOrder ") @Valid @RequestParam(value = "advanceAmount", required = false) BigDecimal advanceAmount,@ApiParam(value = "advance percentage of the PurchaseOrder ") @Valid @RequestParam(value = "advancePercentage", required = false) BigDecimal advancePercentage,@ApiParam(value = "expected delivery date of the PurchaseOrder ") @Valid @RequestParam(value = "expectedDeliveryDate", required = false) Long expectedDeliveryDate,@ApiParam(value = "delivery terms of the PurchaseOrder ") @Valid @RequestParam(value = "deliveryTerms", required = false) String deliveryTerms,@ApiParam(value = "payment terms of the PurchaseOrder ") @Valid @RequestParam(value = "paymentTerms", required = false) String paymentTerms,@ApiParam(value = "description of the PurchaseOrder ") @Valid @RequestParam(value = "description", required = false) String description,@ApiParam(value = "status of the PurchaseOrder ") @Valid @RequestParam(value = "status", required = false) String status,@ApiParam(value = "state id of the PurchaseOrder ") @Valid @RequestParam(value = "stateId", required = false) String stateId,@ApiParam(value = "pageSize") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "offset") @Valid @RequestParam(value = "offset", required = false) Integer offset,@ApiParam(value = "This takes any field from the Object seperated by comma and asc,desc keywords.   example name asc,code desc or name,code or name,code desc  ") @Valid @RequestParam(value = "sortBy", required = false) String sortBy, @RequestHeader(value = "Accept", required = false) String accept) throws Exception;


    @ApiOperation(value = "Update any of the purchaseorders", nickname = "purchaseordersUpdatePost", notes = "Update any of the purchaseorders", response = PurchaseOrderResponse.class, tags={ "Inventory","PurchaseOrder", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "PurchaseOrder updated Successfully", response = PurchaseOrderResponse.class),
        @ApiResponse(code = 400, message = "Invalid Input", response = ErrorRes.class) })
    @RequestMapping(value = "/purchaseorders/_update",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<PurchaseOrderResponse> purchaseordersUpdatePost( @NotNull@ApiParam(value = "Unique id for a tenant.", required = true) @Valid @RequestParam(value = "tenantId", required = true) String tenantId,@ApiParam(value = "common Request info"  )  @Valid @RequestBody PurchaseOrderRequest purchaseOrderRequest, @RequestHeader(value = "Accept", required = false) String accept) throws Exception;

}
