package com.gymbuddy.backgymbuddy.admin.goods.repository;

import com.gymbuddy.backgymbuddy.admin.goods.domain.GoodsOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsOptionRepository extends JpaRepository<GoodsOption, Long> {

    List<GoodsOption> findAllByGoodsId(Long id);
}
