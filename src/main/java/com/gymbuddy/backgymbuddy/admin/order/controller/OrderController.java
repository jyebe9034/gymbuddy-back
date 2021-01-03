package com.gymbuddy.backgymbuddy.admin.order.controller;

import com.gymbuddy.backgymbuddy.admin.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
}
