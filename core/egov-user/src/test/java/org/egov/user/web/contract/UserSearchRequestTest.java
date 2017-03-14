package org.egov.user.web.contract;

import org.egov.user.domain.model.UserSearch;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserSearchRequestTest {

    @Test
    public void test_to_domain() throws Exception {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        UserSearchRequest userSearchRequest = new UserSearchRequest();
        userSearchRequest.setId(ids);
        userSearchRequest.setUserName("userName");
        userSearchRequest.setName("name");
        userSearchRequest.setMobileNumber("mobileNumber");
        userSearchRequest.setAadhaarNumber("aadhaarNumber");
        userSearchRequest.setEmailId("emailId");
        userSearchRequest.setPan("pan");
        userSearchRequest.setFuzzyLogic(false);

        UserSearch userSearch = userSearchRequest.toDomain();

        assertThat(userSearch.getId()).isEqualTo(ids);
        assertThat(userSearch.getUserName()).isEqualTo("userName");
        assertThat(userSearch.getName()).isEqualTo("name");
        assertThat(userSearch.getMobileNumber()).isEqualTo("mobileNumber");
        assertThat(userSearch.getAadhaarNumber()).isEqualTo("aadhaarNumber");
        assertThat(userSearch.getEmailId()).isEqualTo("emailId");
        assertThat(userSearch.getPan()).isEqualTo("pan");
        assertThat(userSearch.isFuzzyLogic()).isFalse();
        assertThat(userSearch.isActive()).isTrue();
    }
}