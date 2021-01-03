package com.gymbuddy.backgymbuddy.admin.member.repository;

import com.gymbuddy.backgymbuddy.admin.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
