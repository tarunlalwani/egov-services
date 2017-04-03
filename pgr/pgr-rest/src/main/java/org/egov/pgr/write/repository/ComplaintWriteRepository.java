package org.egov.pgr.write.repository;

import org.apache.commons.lang3.StringUtils;
import org.egov.pgr.common.entity.Complaint;
import org.egov.pgr.common.entity.ComplaintType;
import org.egov.pgr.common.entity.ReceivingCenter;
import org.egov.pgr.common.entity.enums.ComplaintStatus;
import org.egov.pgr.common.repository.ComplaintStatusJpaRepository;
import org.egov.pgr.common.repository.ComplaintTypeJpaRepository;
import org.egov.pgr.common.repository.ReceivingCenterRepository;
import org.egov.pgr.common.repository.ReceivingModeRepository;
import org.egov.pgr.read.persistence.repository.ComplaintJpaRepository;
import org.egov.pgr.write.model.ComplaintRecord;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ComplaintWriteRepository {

    private ComplaintJpaRepository complaintJpaRepository;
    private ReceivingModeRepository receivingModeRepository;
    private ReceivingCenterRepository receivingCenterRepository;
    private ComplaintTypeJpaRepository complaintTypeJpaRepository;
    private ComplaintStatusJpaRepository complaintStatusRepository;

    public ComplaintWriteRepository(ComplaintJpaRepository complaintJpaRepository,
                                    ReceivingModeRepository receivingModeRepository,
                                    ReceivingCenterRepository receivingCenterRepository,
                                    ComplaintTypeJpaRepository complaintTypeJpaRepository,
                                    ComplaintStatusJpaRepository complaintStatusRepository) {
        this.complaintJpaRepository = complaintJpaRepository;
        this.receivingModeRepository = receivingModeRepository;
        this.receivingCenterRepository = receivingCenterRepository;
        this.complaintTypeJpaRepository = complaintTypeJpaRepository;
        this.complaintStatusRepository = complaintStatusRepository;
    }

    public void updateOrInsert(ComplaintRecord complaintRecord) {
        final Complaint complaint = getComplaint(complaintRecord);
        setBasicInfo(complaintRecord, complaint);
        setAuditFields(complaintRecord, complaint);
        setComplainant(complaintRecord, complaint);
        setReceivingMode(complaintRecord, complaint);
        setReceivingCenter(complaintRecord, complaint);
        setComplaintType(complaintRecord, complaint);
        setComplaintStatus(complaintRecord, complaint);
        setAssigneeId(complaintRecord, complaint);
        setLocationDetails(complaintRecord, complaint);
        setEscalationDate(complaintRecord, complaint);
        setWorkflowDetails(complaintRecord, complaint);
        setDepartmentId(complaintRecord, complaint);

        saveComplaint(complaint);
    }

    private void saveComplaint(Complaint complaint) {
        complaintJpaRepository.save(complaint);
    }

    private void setDepartmentId(ComplaintRecord complaintRecord, Complaint complaint) {
        complaint.setDepartment(complaintRecord.getDepartment());

    }

    private void setWorkflowDetails(ComplaintRecord complaintRecord, Complaint complaint) {
        if (complaintRecord.getWorkflowStateId() != null) {
            complaint.setStateId(complaintRecord.getWorkflowStateId());
        }
    }

    private void setEscalationDate(ComplaintRecord complaintRecord, Complaint complaint) {
        complaint.setEscalationDate(complaintRecord.getEscalationDate());
    }

    private void setLocationDetails(ComplaintRecord complaintRecord, Complaint complaint) {
        if (complaintRecord.getLocation() != null)
            complaint.setLocation(complaintRecord.getLocation());
        if (complaintRecord.getChildLocation() != null)
            complaint.setChildLocation(complaintRecord.getChildLocation());
    }

    private void setAssigneeId(ComplaintRecord complaintRecord, Complaint complaint) {
        complaint.setAssignee(complaintRecord.getAssigneeId());
    }

    private void setComplaintStatus(ComplaintRecord complaintRecord, Complaint complaint) {
        ComplaintStatus statusName = ComplaintStatus.valueOf(complaintRecord.getComplaintStatus());
        org.egov.pgr.common.entity.ComplaintStatus complainStatus =
            complaintStatusRepository.findByName(statusName.toString());
        complaint.setStatus(complainStatus);
    }

    private void setComplaintType(ComplaintRecord complaintRecord, Complaint complaint) {
        ComplaintType complaintType = complaintTypeJpaRepository.findByCode(complaintRecord.getComplaintTypeCode());
        complaint.setComplaintType(complaintType);
    }

    private void setReceivingMode(ComplaintRecord complaintRecord, Complaint complaint) {
        if (StringUtils.isNotEmpty(complaintRecord.getReceivingMode())) {
            final String receivingModeInUpperCase = complaintRecord.getReceivingMode().toUpperCase();
            complaint.setReceivingMode(receivingModeRepository.findByCode(receivingModeInUpperCase));
        }
    }

    private void setReceivingCenter(ComplaintRecord complaintRecord, Complaint complaint) {
        if (complaintRecord.getReceivingCenter() != null) {
            ReceivingCenter receivingCenterDB = receivingCenterRepository
                .findById(complaintRecord.getReceivingCenter());
            complaint.setReceivingCenter(receivingCenterDB);
        }
    }

    private void setBasicInfo(ComplaintRecord complaintRecord, Complaint complaint) {
        complaint.setCrn(complaintRecord.getCRN());
        complaint.setLatitude(complaintRecord.getLatitude());
        complaint.setLongitude(complaintRecord.getLongitude());
        complaint.setDetails(complaintRecord.getDescription());
        complaint.setLandmarkDetails(complaintRecord.getLandmarkDetails());
    }

    private void setAuditFields(ComplaintRecord complaintRecord, Complaint complaint) {
        if (complaint.getCreatedBy() == null)
            complaint.setCreatedBy(complaintRecord.getCreatedBy());
        if (complaint.getCreatedDate() == null)
            complaint.setCreatedDate(new Date());
        complaint.setLastModifiedDate(new Date());
        complaint.setLastModifiedBy(complaintRecord.getLastModifiedBy());
    }

    private void setComplainant(ComplaintRecord complaintRecord, Complaint complaint) {
        if (complaint.getId() == null) {
            complaint.getComplainant().setUserDetail(complaintRecord.getComplainantUserId());
        }
        complaint.getComplainant().setName(complaintRecord.getComplainantName());
        complaint.getComplainant().setMobile(complaintRecord.getComplainantMobileNumber());
        complaint.getComplainant().setEmail(complaintRecord.getComplainantEmail());
        complaint.getComplainant().setAddress(complaintRecord.getComplainantAddress());
    }

    private Complaint getComplaint(ComplaintRecord complaintRecord) {
        final Complaint existingComplaint = complaintJpaRepository.findByCrn(complaintRecord.getCRN());
        return existingComplaint == null ? new Complaint() : existingComplaint;
    }
}
