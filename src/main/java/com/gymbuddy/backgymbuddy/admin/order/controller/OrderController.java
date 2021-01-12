package com.gymbuddy.backgymbuddy.admin.order.controller;

import com.gymbuddy.backgymbuddy.admin.order.domain.Order;
import com.gymbuddy.backgymbuddy.admin.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 주문 조회
     * @param userId 해당 유저 아이디
     */
    @GetMapping("/orderDetail/{userId}")
    public List<Order> selectOrderListByUserId(@PathVariable String userId) {
        return null;
    }

    /**
     * 주문 등록
     */
    @PostMapping("/newOrder")
    public Map<String, Object> insertOrder(@RequestBody Map<String, Object> param) {
        return null;
    }
}
