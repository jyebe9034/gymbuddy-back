package com.gymbuddy.backgymbuddy.admin.businessIdentity.repository;

import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BusinessIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiRepository extends JpaRepository<BusinessIdentity, Long> {
}
