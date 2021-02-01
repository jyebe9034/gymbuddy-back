package com.gymbuddy.backgymbuddy.admin.member.repository;

import com.gymbuddy.backgymbuddy.admin.enums.status.WebMobileStatus;
import com.gymbuddy.backgymbuddy.admin.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
