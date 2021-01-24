package com.gymbuddy.backgymbuddy.admin.goods.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.goods.service.GoodsService;
import com.gymbuddy.backgymbuddy.admin.program.domain.Program;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GoodsController extends BaseController {

    private final GoodsService goodsService;

    /**
     * 전체 굿즈 조회
     */
    @GetMapping("/allGoods")
    public List<Program> selectGoodsList() {
        return null;
    }

    /**
     * 굿즈 상세
     */
    @GetMapping("/goodsDetail/{no}")
    public Map<String, Object> selectGoodsDetail(@PathVariable("no") int no) {
        return null;
    }

    /**
     * 굿즈 등록
     */
    @PostMapping("/newGoods")
    public Map<String, Object> insertGoods(@RequestBody Map<String, Object> param) {
        // 여기에서 받은 굿즈 + 굿즈 옵션...
        return null;
    }

    /**
     * 굿즈 수정
     */
    @PostMapping("/updateGoods")
    public Map<String, Object> updateGoods(@RequestBody Map<String, Object> param) {
        // 굿즈 + 굿즈 옵션 수정을 같이 처리해야 함..
        return null;
    }

    /**
     * 굿즈 삭제
     */
    @GetMapping("/deleteGoods")
    public Map<String, Object> deleteGoods(@RequestParam Map<String, Object> param) {
        return null;
    }
}
