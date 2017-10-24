/*
 * Master and Object Definitions
 * Definitions of Master and Object used across LandEstate, Acquisition and Advertisement Tax.
 *
 * OpenAPI spec version: 1.0.0
 * Contact: info@egovernments.org
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.egov.lams.common.web.contract;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * An object which holds the  Compensation Mode Master info
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompensationMode {
  @NotNull
  private Long id = null;

  @NotNull
  @Size(min = 2, max = 128)
  private String tenantId = null;

  @NotNull
  @Size(min = 1, max = 64)
  private String code = null;

  @NotNull
  @Size(min = 1, max = 256)
  private String name = null;

  @NotNull
  private Boolean isActive = null;
}
