package com.gymbuddy.backgymbuddy.admin.goods.repository;

import com.gymbuddy.backgymbuddy.admin.goods.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findAllByMainYn(String mainYn);
}
