package com.thryve.backendTask.repo;

import com.thryve.backendTask.repo.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {
}
