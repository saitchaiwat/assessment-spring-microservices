package com.codework.requestservice.repository;

import com.codework.requestservice.dto.FileViewDTO;
import com.codework.requestservice.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    FileEntity findByName(String name);

    @Query(
            value = "SELECT f.id as fileId, f.uri as fileUri \n" +
                    "FROM files f \n" +
                    "LEFT JOIN requests r on r.id = f.request_id\n" +
                    "WHERE r.id = ?1",
            countQuery = "SELECT count(*)\n" +
                    "FROM files f\n" +
                    "LEFT JOIN requests r on r.id = f.request_id\n" +
                    "WHERE r.id = ?1",
            nativeQuery = true
    )
    List<FileViewDTO> findFileByNativeQuery(Long requestId);
}
