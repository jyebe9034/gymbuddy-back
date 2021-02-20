package com.gymbuddy.backgymbuddy.admin.goods.service;

import com.gymbuddy.backgymbuddy.admin.goods.domain.Goods;
import com.gymbuddy.backgymbuddy.admin.goods.domain.GoodsDto;
import com.gymbuddy.backgymbuddy.admin.goods.domain.GoodsOption;
import com.gymbuddy.backgymbuddy.admin.goods.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public List<Goods> findAll(int page) {
        return goodsRepository.findAll(PageRequest.of(page, 10)).getContent();
    }

    public Goods findOne(Long id) {
        return goodsRepository.findById(id).get();
    }

    @Transactional
    public Long save(GoodsDto dto) {
        Goods goods = new Goods();
        goods.setName(dto.getName());
        goods.setCreateDate(LocalDateTime.now());
        goods.setUpdateDate(LocalDateTime.now());

        List<GoodsOption> goodsOption = new ArrayList<>();
        for (GoodsOption option : goodsOption) {
            option.setColorAndSize(dto.getColorAndSize());
            option.setInventory(dto.getInventory());
            option.setCreateDate(LocalDateTime.now());
            option.setUpdateDate(LocalDateTime.now());
        }
        goods.setGoodsOptions(goodsOption);

        goodsRepository.save(goods);
        return goods.getId();
    }

    @Transactional
    public void update(Long id, GoodsDto dto) {
        Goods goods = findOne(id);
        if (dto.getName() != null) {
            goods.setName(dto.getName());
        }
        goods.setUpdateDate(LocalDateTime.now());
    }

    @Transactional
    public void delete(Long id) {
        goodsRepository.deleteById(id);
    }
}
