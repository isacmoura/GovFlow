package dev.isac.govflow.request.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataServiceRequestRepository extends JpaRepository<ServiceRequestEntity, UUID> {
}
