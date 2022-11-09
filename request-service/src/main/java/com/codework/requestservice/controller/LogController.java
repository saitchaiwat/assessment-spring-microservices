package com.codework.requestservice.controller;

import com.codework.requestservice.dto.LogViewDTO;
import com.codework.requestservice.service.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/request")
public class LogController {
    @Autowired
    private LogServiceImpl logService;

    @CrossOrigin("*")
    @RequestMapping(value = "/getLogInfo", method = RequestMethod.GET)
    public ResponseEntity<Page<LogViewDTO>> getLogInfo(@RequestParam(name = "requestId") Long requestId,
                                                       @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                       @RequestParam(name = "pageSize", defaultValue = "25") int pageSize) {
        Page<LogViewDTO> logs = logService.findLogByNativeQuery(requestId, offset, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(logs);
    }
}
