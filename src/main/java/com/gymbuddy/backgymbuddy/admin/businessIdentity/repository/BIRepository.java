package com.gymbuddy.backgymbuddy.admin.businessIdentity.repository;

import com.gymbuddy.backgymbuddy.admin.base.WebMobile;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BusinessIdentity;
import com.gymbuddy.backgymbuddy.admin.term.domain.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BIRepository extends JpaRepository<BusinessIdentity, Long> {
    BusinessIdentity findWeb(WebMobile webMobile);
    BusinessIdentity findMobile(WebMobile webMobile);
}
