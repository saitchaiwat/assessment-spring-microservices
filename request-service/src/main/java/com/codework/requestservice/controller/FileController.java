package com.codework.requestservice.controller;

import com.codework.requestservice.dto.FileViewDTO;
import com.codework.requestservice.entity.FileEntity;
import com.codework.requestservice.response.ResponseHandler;
import com.codework.requestservice.service.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/request")
public class FileController {
    @Autowired
    private FileServiceImpl fileService;

    @CrossOrigin("*")
    @RequestMapping(value = "/getFile/{name}", method = RequestMethod.GET)
    public void getFile(@PathVariable String name, HttpServletResponse response) throws IOException {
        FileEntity file = fileService.getFile(name);
        response.setContentType(file.getType());
        response.getOutputStream().write(file.getContent());
        response.getOutputStream().close();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/getFileDetail/{requestId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getFileDetail(@PathVariable Long requestId) {
        try {
            List<FileViewDTO> files = fileService.getFileDetail(requestId);

            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, files);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }
}
