package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.service;

import com.gymbuddy.backgymbuddy.admin.frequencyQuestion.repository.FQRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FQService {

    private final FQRepository fqRepository;
}
