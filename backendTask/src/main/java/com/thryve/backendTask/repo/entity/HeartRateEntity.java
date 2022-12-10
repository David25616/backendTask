package com.thryve.backendTask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "THRYVE_DATA")
@Entity
public class HeartRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "start_timestamp")
    private Timestamp startTimestamp;
    @Column(name = "end_timestamp")
    private Timestamp endTimestamp;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "dynamic_value_type")
    private Integer dynamicValueType;
    private Long value;
    @Column(name = "user_id")
    private Integer userId;
}
