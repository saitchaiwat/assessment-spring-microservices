package com.codework.requestservice.dto;

import java.util.Date;

public interface LogViewDTO {
    Long getLogId();
    String getStatus();
    Long getStaffId();
    String getStaffFirstName();
    String getStaffLastName();
    Date getLastUpdate();
}
