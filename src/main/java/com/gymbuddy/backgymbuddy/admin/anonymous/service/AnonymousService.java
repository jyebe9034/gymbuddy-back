package com.gymbuddy.backgymbuddy.admin.anonymous.service;

import com.gymbuddy.backgymbuddy.admin.anonymous.repository.AnonymousRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true) // 모든 데이터의 변경은 트랜잭션 안에서 일어나야 함. readOnly = true 는 읽기 전용!
@RequiredArgsConstructor
public class AnonymousService {

    private final AnonymousRepository anonymousRepository;
}
