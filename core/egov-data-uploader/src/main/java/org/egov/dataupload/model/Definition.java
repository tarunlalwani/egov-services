package org.egov.dataupload.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Definition {

	@JsonProperty("defName")
	private String defName;
	
	@JsonProperty("apiRequest")
	private Object apiRequest;
	
	@JsonProperty("isBulkApi")
	private Boolean isBulkApi;
	
	@JsonProperty("arrayPath")
	private String arrayPath;
	
	@JsonProperty("uri")
	private String uri;	
	
}