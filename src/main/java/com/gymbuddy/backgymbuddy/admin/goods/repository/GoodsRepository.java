package com.gymbuddy.backgymbuddy.admin.goods.repository;

import com.gymbuddy.backgymbuddy.admin.goods.domain.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findAllByMainYn(String mainYn);

    @Query("select g from Goods g order by g.mainYn desc, g.id desc")
    Page<Goods> findAllByMainYnAndCreateDate(Pageable pageable);
}
