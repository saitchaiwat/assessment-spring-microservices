package com.codework.requestservice.service;

import com.codework.requestservice.dto.LogViewDTO;
import com.codework.requestservice.entity.LogEntity;
import com.codework.requestservice.entity.RequestEntity;
import com.codework.requestservice.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;

    @Override
    public void save(Long requestId, RequestEntity.Status status) {
        LogEntity log = new LogEntity();
        log.setRequestId(requestId);
        log.setStatus(status);
        logRepository.save(log);
    }

    @Override
    public Page<LogViewDTO> findLogByNativeQuery(Long requestId, int offset, int pageSize) {
        return logRepository.findLogByNativeQuery(requestId, PageRequest.of(offset, pageSize));
    }
}
