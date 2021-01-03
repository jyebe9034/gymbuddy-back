package com.gymbuddy.backgymbuddy.admin.notice.repository;

import com.gymbuddy.backgymbuddy.admin.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
