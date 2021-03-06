package org.egov.works.workorder.web.contract;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets TrackMilestoneStatus
 */
public enum TrackMilestoneStatus {

    APPROVED("APPROVED"),

    CANCELLED("CANCELLED");

    private String value;

    TrackMilestoneStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TrackMilestoneStatus fromValue(String text) {
        for (TrackMilestoneStatus b : TrackMilestoneStatus.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }
}

