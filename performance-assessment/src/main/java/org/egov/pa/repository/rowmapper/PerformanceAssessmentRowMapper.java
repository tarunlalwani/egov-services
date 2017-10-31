/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.pa.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.egov.pa.model.Document;
import org.egov.pa.model.KPI;
import org.egov.pa.model.KpiTarget;
import org.egov.pa.model.KpiValue;
import org.egov.pa.model.KpiValueList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PerformanceAssessmentRowMapper {

	public static final Logger LOGGER = LoggerFactory.getLogger(PerformanceAssessmentRowMapper.class);
	
	public class KPIMasterRowMapper implements RowMapper<KPI> {
		public Map<Long, KPI> kpiMap = new HashMap<>();
		public Map<Long, List<Document>> docMap = new HashMap<>();
		@Override
		public KPI mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			if(!kpiMap.containsKey(rs.getLong("id"))) { 
				KPI kpi = new KPI(); 
				kpi.setId(rs.getLong("id")); 
				kpi.setName(rs.getString("name"));
				kpi.setCode(rs.getString("code"));
				kpi.setFinYear(rs.getString("finYear"));
				kpi.setCreatedBy(rs.getLong("createdBy"));
				kpi.setCreatedDate(rs.getLong("createdDate"));
				kpi.setLastModifiedBy(rs.getLong("lastModifiedBy"));
				kpi.setLastModifiedDate(rs.getLong("lastModifiedDate"));
				if(rs.getLong("targetValue") > 0 && rs.getLong("targetId") > 0) {
					KpiTarget target = new KpiTarget();
					target.setId(rs.getLong("targetId"));
					target.setKpiCode(rs.getString("targetkpicode"));
					target.setTargetValue(rs.getLong("targetValue"));
					target.setTargetDescription(rs.getString("targetDescription"));
					target.setInstructions(rs.getString("instructions"));
					kpi.setKpiTarget(target);
				}
				kpiMap.put(rs.getLong("id"), kpi); 
			}
			
			if(docMap.containsKey(rs.getLong("id"))) { 
				List<Document> docList = docMap.get(rs.getLong("id")); 
				if(StringUtils.isNotBlank(rs.getString("documentcode"))) { 
					docList.add(prepareDocumentObject(rs));
				}
			} else { 
				List<Document> docList = new ArrayList<>(); 
				docList.add(prepareDocumentObject(rs));
				if(StringUtils.isNotBlank(rs.getString("documentcode"))) { 
					docMap.put(rs.getLong("id"), docList) ;
				}
			}
			return null; 
		}
		
		private Document prepareDocumentObject(ResultSet rs) {
			Document doc = new Document();
			try { 
				doc.setId(rs.getLong("docid"));
				doc.setKpiCode(rs.getString("dockpicode"));
				doc.setCode(rs.getString("documentcode"));
				doc.setName(rs.getString("documentname"));
				doc.setActive(rs.getBoolean("mandatoryflag"));
			} catch (Exception e) { 
				log.error("Exception encountered while preparing Document Object from RS : " + e);
			}
			return doc; 
		}
	}
	
	public class KPIValueRowMapper implements RowMapper<KpiValueList> {
		@Override
		public KpiValueList mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			KpiValueList valueList = new KpiValueList(); 
			valueList.setTenantId(rs.getString("tenantId")); 
			valueList.setKpiCode(rs.getString("kpiCode"));
			valueList.setFinYear(rs.getString("finYear")); 
			KpiTarget target = new KpiTarget(); 
			target.setId(rs.getLong("targetId"));
			target.setKpiCode(rs.getString("targetKpiCode"));
			target.setTargetValue(rs.getLong("targetValue"));
			target.setTargetDescription(rs.getString("targetDescription"));
			target.setInstructions(rs.getString("instructions"));
			valueList.setKpiTarget(target);
			KpiValue value = new KpiValue(); 
			value.setId(rs.getLong("valueId")); 
			value.setKpiCode(rs.getString("valueKpiCode"));
			value.setActualValue(rs.getLong("actualValue"));
			value.setTenantId(rs.getString("tenantId"));
			List<KpiValue> kpiValueList = new ArrayList<>();
			kpiValueList.add(value); 
			valueList.setKpiValue(kpiValueList);
			return valueList; 
		}
	}
	
	
}