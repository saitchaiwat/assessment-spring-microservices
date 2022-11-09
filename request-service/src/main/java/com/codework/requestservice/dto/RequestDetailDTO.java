package com.codework.requestservice.dto;

public interface RequestDetailDTO {
    Long getRequestId();
    String getTitle();
    String getDetail();
    String getStatus();
    String getReason();
    Long getCustomerId();
    String getCustomerFirstName();
    String getCustomerLastName();
    Long getStaffId();
    String getStaffFirstName();
    String getStaffLastName();
    Long getManagerId();
    String getManagerFirstName();
    String getManagerLastName();
}
