package com.gymbuddy.backgymbuddy.admin.businessIdentity.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.domain.BusinessIdentity;
import com.gymbuddy.backgymbuddy.admin.businessIdentity.service.BiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.BI_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BiController extends BaseController {

    private final String URI_PREFIX = BI_PREFIX;

    private final BiService biService;

    /**
     * BI 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertBi(@RequestBody BusinessIdentity bi) {
        log.info("BI 등록: {}", bi);
        Long id = biService.save(bi);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

    /**
     * BI 삭제
     */
    @DeleteMapping(URI_PREFIX + "/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteBi(@PathVariable("id") Long id) {
        log.info("BI 삭제: {}", id);
        biService.delete(id);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return createResponseEntity(true, result);
    }

}
