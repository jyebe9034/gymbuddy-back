package com.gymbuddy.backgymbuddy.admin.goods.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.goods.domain.Goods;
import com.gymbuddy.backgymbuddy.admin.goods.domain.GoodsDto;
import com.gymbuddy.backgymbuddy.admin.goods.service.GoodsService;
import com.gymbuddy.backgymbuddy.admin.program.domain.Program;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.GOODS_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GoodsController extends BaseController {

    private final String URI_PREFIX = GOODS_PREFIX;

    private final GoodsService goodsService;

    /**
     * 전체 굿즈 조회
     */
    @GetMapping(URI_PREFIX + "/all/{page}")
    public ResponseEntity<List<Goods>> selectGoodsList(@PathVariable int page) {
        return createResponseEntity(true, goodsService.findAll(page));
    }

    /**
     * 굿즈 상세
     */
    @GetMapping(URI_PREFIX + "/detail/{id}")
    public ResponseEntity<Map<String, Object>> selectGoodsDetail(@PathVariable("id") Long id) {
        log.info("굿즈 상세 조회: {}", id);
        return createResponseEntity(true, goodsService.findOne(id));
    }

    /**
     * 굿즈 등록
     */
    @PostMapping(URI_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertGoods(@ModelAttribute GoodsDto dto) {
        log.info("굿즈 등록: {}", dto);

        Map<String, Object> result = new HashMap<>();
        result.put("id", goodsService.save(dto));
        return createResponseEntity(true, result);
    }

    /**
     * 굿즈 수정
     */
    @PostMapping(URI_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateGoods(
            @PathVariable("id") Long id, @ModelAttribute GoodsDto dto) {
        log.info("굿즈 수정 - id: {}, dto: {}", id, dto);

        goodsService.update(id, dto);
        Goods findGoods = goodsService.findOne(id);

        boolean flag = true;
        if (dto.getName() != null) {
            flag = dto.getName().equals(findGoods.getName()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 굿즈 삭제
     */
    @GetMapping(URI_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteGoods(@RequestBody List<Integer> ids) {
        log.info("굿즈 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            goodsService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }
}
