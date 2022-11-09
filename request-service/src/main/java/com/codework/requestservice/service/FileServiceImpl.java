package com.codework.requestservice.service;

import com.codework.requestservice.dto.FileViewDTO;
import com.codework.requestservice.entity.FileEntity;
import com.codework.requestservice.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileEntity getFile(String name) {
        return fileRepository.findByName(name);
    }

    @Override
    public List<FileViewDTO> getFileDetail(Long requestId) {
        return fileRepository.findFileByNativeQuery(requestId);
    }

    @Override
    public void upload(Long requestId, MultipartFile[] requestImages) throws IOException {
        List<FileEntity> files = new ArrayList<>();
        for (MultipartFile requestImage : requestImages) {
            String[] filename = requestImage.getOriginalFilename().split("\\.");
            String name = UUID.randomUUID() + (filename.length > 0 ? "." + filename[1] : "");

            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/request/getFile/")
                    .path(name)
                    .toUriString();

            FileEntity file = new FileEntity();
            file.setRequestId(requestId);
            file.setName(name);
            file.setContent(requestImage.getBytes());
            file.setType(requestImage.getContentType());
            file.setUri(fileUri);
            files.add(file);
        }
        fileRepository.saveAll(files);
    }
}
