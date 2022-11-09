package com.codework.requestservice.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "requests")
@EntityListeners(AuditingEntityListener.class)
public class RequestEntity {
    public enum Status {
        Draft,
        Wait_For_Manager_Review,
        Approved,
        Rejected_By_Manager
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "requester_id")
    private Long requesterId;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "title")
    private String title;

    @Column(name = "detail")
    private String detail;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "reason")
    private String reason;

    @Column(name = "created_at", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
}
