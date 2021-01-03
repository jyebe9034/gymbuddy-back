package com.gymbuddy.backgymbuddy.admin.goods.repository;

import com.gymbuddy.backgymbuddy.admin.goods.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
