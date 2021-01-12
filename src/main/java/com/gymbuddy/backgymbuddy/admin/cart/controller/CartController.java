package com.gymbuddy.backgymbuddy.admin.cart.controller;

import com.gymbuddy.backgymbuddy.admin.cart.domain.Cart;
import com.gymbuddy.backgymbuddy.admin.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * 장바구니 조회
     * @param userId 해당 유저 아이디
     */
    @GetMapping("/cartList/{userId}")
    public List<Cart> selectCartListByUserId(@PathVariable("userId") String userId) {
        return null;
    }

    /**
     * 장바구니 등록
     */
    @PostMapping("/newCart")
    public Map<String, Object> insertCart(@RequestBody Map<String, Object> param) {
        // param안에 해당 유저의 아이디 값을 포함해서 선택한 굿즈나 프로그램 정보가 담겨있어야 함.
        return null;
    }
}
