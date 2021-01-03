package com.gymbuddy.backgymbuddy.admin.goods.controller;

import com.gymbuddy.backgymbuddy.admin.goods.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;
}
