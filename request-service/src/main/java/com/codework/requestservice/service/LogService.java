package com.codework.requestservice.service;

import com.codework.requestservice.dto.LogViewDTO;
import com.codework.requestservice.entity.RequestEntity;
import org.springframework.data.domain.Page;

public interface LogService {
    void save(Long requestId, RequestEntity.Status status);
    Page<LogViewDTO> findLogByNativeQuery(Long requestId, int offset, int pageSize);
}
