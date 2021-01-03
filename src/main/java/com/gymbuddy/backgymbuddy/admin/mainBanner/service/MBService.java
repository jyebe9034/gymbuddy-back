package com.gymbuddy.backgymbuddy.admin.mainBanner.service;

import com.gymbuddy.backgymbuddy.admin.mainBanner.repository.MBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MBService {

    private final MBRepository mbRepository;
}
