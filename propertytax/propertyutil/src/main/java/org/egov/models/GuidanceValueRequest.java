package org.egov.models;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuidanceValueRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;
    
    @Valid
    private List<GuidanceValue> guidanceValues;
}
