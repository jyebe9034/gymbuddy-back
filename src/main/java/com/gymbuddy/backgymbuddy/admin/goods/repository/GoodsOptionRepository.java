package com.gymbuddy.backgymbuddy.admin.goods.repository;

import com.gymbuddy.backgymbuddy.admin.goods.domain.GoodsOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GoodsOptionRepository extends JpaRepository<GoodsOption, Long> {

    List<GoodsOption> findAllByGoodsId(Long id);

    @Modifying
    @Transactional
    @Query("delete from GoodsOption o where o.goods.id = :id")
    void deleteByGoodsId(@Param("id") Long goodsId);
}
