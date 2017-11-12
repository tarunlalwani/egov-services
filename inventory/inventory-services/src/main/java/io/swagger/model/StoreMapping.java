package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.AuditDetails;
import io.swagger.model.ChartofAccount;
import io.swagger.model.Store;
import lombok.Builder;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This object holds the store mapping details information. 
 */
@ApiModel(description = "This object holds the store mapping details information. ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-11T07:24:51.478Z")
@Builder
public class StoreMapping   {
  @JsonProperty("store")
  private Store store = null;

  @JsonProperty("chartofAccount")
  private ChartofAccount chartofAccount = null;

  @JsonProperty("active")
  private Boolean active = null;

  @JsonProperty("delete")
  private Boolean delete = null;

  @JsonProperty("auditDetails")
  private AuditDetails auditDetails = null;

  public StoreMapping store(Store store) {
    this.store = store;
    return this;
  }

   /**
   * Get store
   * @return store
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public StoreMapping chartofAccount(ChartofAccount chartofAccount) {
    this.chartofAccount = chartofAccount;
    return this;
  }

   /**
   * Get chartofAccount
   * @return chartofAccount
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ChartofAccount getChartofAccount() {
    return chartofAccount;
  }

  public void setChartofAccount(ChartofAccount chartofAccount) {
    this.chartofAccount = chartofAccount;
  }

  public StoreMapping active(Boolean active) {
    this.active = active;
    return this;
  }

   /**
   * active or inactive flag of mapping 
   * @return active
  **/
  @ApiModelProperty(value = "active or inactive flag of mapping ")


  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public StoreMapping delete(Boolean delete) {
    this.delete = delete;
    return this;
  }

   /**
   * delete flag of mapping used during deleting the mapping.     
   * @return delete
  **/
  @ApiModelProperty(value = "delete flag of mapping used during deleting the mapping.     ")


  public Boolean getDelete() {
    return delete;
  }

  public void setDelete(Boolean delete) {
    this.delete = delete;
  }

  public StoreMapping auditDetails(AuditDetails auditDetails) {
    this.auditDetails = auditDetails;
    return this;
  }

   /**
   * Get auditDetails
   * @return auditDetails
  **/
  @ApiModelProperty(value = "")

  @Valid

  public AuditDetails getAuditDetails() {
    return auditDetails;
  }

  public void setAuditDetails(AuditDetails auditDetails) {
    this.auditDetails = auditDetails;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StoreMapping storeMapping = (StoreMapping) o;
    return Objects.equals(this.store, storeMapping.store) &&
        Objects.equals(this.chartofAccount, storeMapping.chartofAccount) &&
        Objects.equals(this.active, storeMapping.active) &&
        Objects.equals(this.delete, storeMapping.delete) &&
        Objects.equals(this.auditDetails, storeMapping.auditDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(store, chartofAccount, active, delete, auditDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StoreMapping {\n");
    
    sb.append("    store: ").append(toIndentedString(store)).append("\n");
    sb.append("    chartofAccount: ").append(toIndentedString(chartofAccount)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    delete: ").append(toIndentedString(delete)).append("\n");
    sb.append("    auditDetails: ").append(toIndentedString(auditDetails)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

