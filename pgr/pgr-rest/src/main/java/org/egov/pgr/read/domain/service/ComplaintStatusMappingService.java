package org.egov.pgr.read.domain.service;

import org.egov.pgr.common.contract.GetUserByIdResponse;
import org.egov.pgr.common.entity.ComplaintStatus;
import org.egov.pgr.common.entity.ComplaintStatusMapping;
import org.egov.pgr.common.model.Role;
import org.egov.pgr.common.repository.UserRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ComplaintStatusMappingService {

	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = SQLGrammarException.class)
	public List<ComplaintStatus> getStatusByRoleAndCurrentStatus(final Long userId, final String status,
                                                                 final String tenantId) {
		Set<Role> roles;
		GetUserByIdResponse userRoles = userRepository.findUserById(userId);
		if (!userRoles.getUser().isEmpty()) {
			roles = userRoles.getUser().get(0).getRoles();
			final List<Long> roleId = roles.stream().map(Role::getId).collect(Collectors.toList());
			final Criteria criteria = entityManager.unwrap(Session.class)
					.createCriteria(ComplaintStatusMapping.class, "complaintMapping")
					.createAlias("complaintMapping.currentStatus", "complaintStatus");
			criteria.add(Restrictions.eq("complaintStatus.name", status))
					.add(Restrictions.in("complaintMapping.role", roleId.toArray()))
					.addOrder(Order.asc("complaintMapping.orderNo"));
			criteria.setProjection(Projections.property("complaintMapping.showStatus"));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			return criteria.list();
		} else
			return null;
	}

}
