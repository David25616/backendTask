package com.thryve.backendTask.repo;

import com.thryve.backendTask.model.HeartRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DataRepository extends JpaRepository<HeartRateEntity, UUID> {


    List<HeartRateEntity> findByUserId(int userId);

    List<HeartRateEntity> findByValueGreaterThan(Long threshold);

    List<HeartRateEntity> findByValueLessThan(Long threshold);
}
