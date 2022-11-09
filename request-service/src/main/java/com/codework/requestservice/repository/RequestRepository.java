package com.codework.requestservice.repository;

import com.codework.requestservice.dto.RequestDetailDTO;
import com.codework.requestservice.dto.RequestViewDTO;
import com.codework.requestservice.entity.RequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {
    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.updated_at as lastUpdate,\n" +
                    "c.id as customerId, c.first_name as customerFirstName, c.last_name as customerLastName,\n" +
                    "s.id as staffId, s.first_name as staffFirstName, s.last_name as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1 AND r.status = ?2 AND ((c.first_name LIKE concat('%', ?3, '%') OR c.last_name LIKE concat('%', ?3, '%')) OR\n" +
                    "(s.first_name LIKE concat('%', ?3, '%') OR s.last_name LIKE concat('%', ?3, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?3, '%') OR r.detail LIKE concat('%', ?3, '%')))",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1 AND r.status = ?2 AND ((c.first_name LIKE concat('%', ?3, '%') OR c.last_name LIKE concat('%', ?3, '%')) OR\n" +
                    "(s.first_name LIKE concat('%', ?3, '%') OR s.last_name LIKE concat('%', ?3, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?3, '%') OR r.detail LIKE concat('%', ?3, '%')))",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRemainingRequestsByStaff(Long staffId, String status, String keyword, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.updated_at as lastUpdate,\n" +
                    "c.id as customerId, c.first_name as customerFirstName, c.last_name as customerLastName,\n" +
                    "s.id as staffId, s.first_name as staffFirstName, s.last_name as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1 AND r.status = ?2",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1 AND r.status = ?2",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRemainingRequestsByStaffWithoutKeyword(Long staffId, String status, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.updated_at as lastUpdate,\n" +
                "c.id as customerId, c.first_name as customerFirstName, c.last_name as customerLastName,\n" +
                "s.id as staffId, s.first_name as staffFirstName, s.last_name as staffLastName\n" +
                "FROM requests r\n" +
                "LEFT JOIN users c on r.customer_id = c.id\n" +
                "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1 AND ((c.first_name LIKE concat('%', ?2, '%') OR c.last_name LIKE concat('%', ?2, '%')) OR\n" +
                    "(s.first_name LIKE concat('%', ?2, '%') OR s.last_name LIKE concat('%', ?2, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?2, '%') OR r.detail LIKE concat('%', ?2, '%')))",
            countQuery = "SELECT count(*)\n" +
                "FROM requests r\n" +
                "LEFT JOIN users c on r.customer_id = c.id\n" +
                "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1 AND ((c.first_name LIKE concat('%', ?2, '%') OR c.last_name LIKE concat('%', ?2, '%')) OR\n" +
                    "(s.first_name LIKE concat('%', ?2, '%') OR s.last_name LIKE concat('%', ?2, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?2, '%') OR r.detail LIKE concat('%', ?2, '%')))",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRequestsByStaff(Long staffId, String keyword, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.updated_at as lastUpdate,\n" +
                    "c.id as customerId, c.first_name as customerFirstName, c.last_name as customerLastName,\n" +
                    "s.id as staffId, s.first_name as staffFirstName, s.last_name as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRequestsByStaffWithoutKeyword(Long staffId, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.updated_at as lastUpdate,\n" +
                    "c.id as customerId, c.first_name as customerFirstName, c.last_name as customerLastName,\n" +
                    "s.id as staffId, s.first_name as staffFirstName, s.last_name as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "LEFT JOIN users m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status != ?2 AND ((c.first_name LIKE concat('%', ?3, '%') OR c.last_name LIKE concat('%', ?3, '%')) OR\n" +
                    "(s.first_name LIKE concat('%', ?3, '%') OR s.last_name LIKE concat('%', ?3, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?3, '%') OR r.detail LIKE concat('%', ?3, '%')))",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "LEFT JOIN users m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status != ?2 AND ((c.first_name LIKE concat('%', ?3, '%') OR c.last_name LIKE concat('%', ?3, '%')) OR\n" +
                    "(s.first_name LIKE concat('%', ?3, '%') OR s.last_name LIKE concat('%', ?3, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?3, '%') OR r.detail LIKE concat('%', ?3, '%')))",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRequestsByManager(String role, String status, String keyword, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.updated_at as lastUpdate,\n" +
                    "c.id as customerId, c.first_name as customerFirstName, c.last_name as customerLastName,\n" +
                    "s.id as staffId, s.first_name as staffFirstName, s.last_name as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "LEFT JOIN users m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status != ?2",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "LEFT JOIN users m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status != ?2",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRequestsByManagerWithoutKeyword(String role, String status, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.updated_at as lastUpdate,\n" +
                    "c.id as customerId, c.first_name as customerFirstName, c.last_name as customerLastName,\n" +
                    "s.id as staffId, s.first_name as staffFirstName, s.last_name as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "LEFT JOIN users m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status = ?2 AND ((c.first_name LIKE concat('%', ?3, '%') OR c.last_name LIKE concat('%', ?3, '%')) OR\n" +
                    "(s.first_name LIKE concat('%', ?3, '%') OR s.last_name LIKE concat('%', ?3, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?3, '%') OR r.detail LIKE concat('%', ?3, '%')))",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "LEFT JOIN users m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status = ?2 AND ((c.first_name LIKE concat('%', ?3, '%') OR c.last_name LIKE concat('%', ?3, '%')) OR\n" +
                    "(s.first_name LIKE concat('%', ?3, '%') OR s.last_name LIKE concat('%', ?3, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?3, '%') OR r.detail LIKE concat('%', ?3, '%')))",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRemainingRequestsByManager(String role, String status, String keyword, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.updated_at as lastUpdate,\n" +
                    "c.id as customerId, c.first_name as customerFirstName, c.last_name as customerLastName,\n" +
                    "s.id as staffId, s.first_name as staffFirstName, s.last_name as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "LEFT JOIN users m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status = ?2",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "LEFT JOIN users m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status = ?2",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRemainingRequestsByManagerWithoutKeyword(String role, String status, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.reason,\n" +
                    "c.id as customerId, c.first_name as customerFirstName, c.last_name as customerLastName,\n" +
                    "s.id as staffId, s.first_name as staffFirstName, s.last_name as staffLastName,\n" +
                    "m.id as managerId, m.first_name as managerFirstName, m.last_name as managerLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN users c on r.customer_id = c.id\n" +
                    "LEFT JOIN users s on r.requester_id = s.id\n" +
                    "LEFT JOIN users m on r.manager_id = m.id\n" +
                    "WHERE r.id = ?1",
            nativeQuery = true
    )
    RequestDetailDTO findRequestDetail(Long id);
}
