package com.codework.requestservice.service;

import com.codework.requestservice.dto.FileViewDTO;
import com.codework.requestservice.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    FileEntity getFile(String name);
    List<FileViewDTO> getFileDetail(Long requestId);
    void upload(Long requestId, MultipartFile[] multipartFiles) throws IOException;
}
