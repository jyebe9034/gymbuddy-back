package com.gymbuddy.backgymbuddy.admin.goods.controller;

import com.gymbuddy.backgymbuddy.admin.base.BaseController;
import com.gymbuddy.backgymbuddy.admin.enums.status.GoodsStatus;
import com.gymbuddy.backgymbuddy.admin.goods.domain.Goods;
import com.gymbuddy.backgymbuddy.admin.goods.domain.GoodsDto;
import com.gymbuddy.backgymbuddy.admin.goods.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gymbuddy.backgymbuddy.admin.base.Constants.ADMIN_GOODS_PREFIX;
import static com.gymbuddy.backgymbuddy.admin.base.Constants.GOODS_PREFIX;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GoodsController extends BaseController {

    private String goodsPath = "/resources/static/img/goods";
    private String rootPath = System.getProperty("user.dir") + "/src/main" + goodsPath;
    private File saveFile = new File(rootPath);

    private final GoodsService goodsService;

    /**
     * 전체 굿즈 조회
     */
    @GetMapping(GOODS_PREFIX + "/all/{page}")
    public ResponseEntity<Map<String, Object>> selectGoodsList(@PathVariable("page") int page) {
        return createResponseEntity(true, goodsService.findAllByDto(page));
    }

    /**
     * 전체 굿즈 갯수 조회
     */
    @GetMapping(GOODS_PREFIX + "/totalCount")
    public ResponseEntity<Map<String, Object>> selectGoodsTotalCount() {
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", goodsService.selectTotalCount());
        return createResponseEntity(true, result);
    }

    /**
     * 굿즈 상세
     */
    @GetMapping(GOODS_PREFIX + "/detail/{id}")
    public ResponseEntity<GoodsDto> selectGoodsDetail(@PathVariable("id") Long id) {
        log.info("굿즈 상세 조회: {}", id);
        return createResponseEntity(true, goodsService.findOneByDto(id));
    }

    /**
     * 굿즈 등록
     */
    @PostMapping(ADMIN_GOODS_PREFIX + "/new")
    public ResponseEntity<Map<String, Object>> insertGoods(@RequestBody GoodsDto dto) {
        log.info("굿즈 등록: {}", dto);

        try {
            if (!saveFile.exists()) {
                saveFile.mkdir();
            }
            // 썸네일 이미지
            if(dto.getThumbnailFile() != null) {
                String thumbnailName = dto.getThumbnailFile().getOriginalFilename();
                File thumbnail = new File(saveFile + "/" + System.currentTimeMillis() + "_" + thumbnailName);
                dto.getThumbnailFile().transferTo(thumbnail);
                dto.setThumbnailImgName(thumbnailName);
                dto.setThumbnailImgPath(saveFile + "/" + thumbnail.getName());
            }
            // 상세 이미지
            if(dto.getDetailFile() != null) {
                String detailName = dto.getThumbnailFile().getOriginalFilename();
                File detail = new File(saveFile + "/" + System.currentTimeMillis() + "_" + detailName);
                dto.getDetailFile().transferTo(detail);
                dto.setDetailImgName(detailName);
                dto.setDetailImgPath(saveFile + "/" + detail.getName());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", goodsService.save(dto));
        return createResponseEntity(true, result);
    }

    /**
     * 굿즈 수정
     */
    @PutMapping(ADMIN_GOODS_PREFIX + "/update/{id}")
    public ResponseEntity<Map<String, Object>> updateGoods(
            @PathVariable("id") Long id, @RequestBody GoodsDto dto) {
        log.info("굿즈 수정 id: {}, dto: {}", id, dto);

        Goods goods = goodsService.findOne(id);

        // 썸네일 이미지 수정
        if (dto.getThumbnailFile() != null) {
            try {
                String thumbnailName = dto.getThumbnailFile().getOriginalFilename();
                File thumbnail = new File(saveFile + "/" + System.currentTimeMillis() + "_" + thumbnailName);
                dto.getThumbnailFile().transferTo(thumbnail);
                dto.setThumbnailImgName(thumbnailName);
                dto.setThumbnailImgPath(saveFile + "/" + thumbnail.getName());

                if (goods.getThumbnailImgPath() != null) {
                    File originThumbnail = new File(goods.getThumbnailImgPath());
                    if (originThumbnail.exists()) {
                        originThumbnail.delete();
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        // 상세 이미지 수정
        if (dto.getDetailFile() != null) {
            try {
                String detailName = dto.getThumbnailFile().getOriginalFilename();
                File detail = new File(saveFile + "/" + System.currentTimeMillis() + "_" + detailName);
                dto.getDetailFile().transferTo(detail);
                dto.setDetailImgName(detailName);
                dto.setDetailImgPath(saveFile + "/" + detail.getName());

                if (goods.getThumbnailImgPath() != null) {
                    File originDetail = new File(goods.getDetailImgPath());
                    if (originDetail.exists()) {
                        originDetail.delete();
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        goodsService.update(id, dto);

        Goods findGoods = goodsService.findOne(id);

        boolean flag = true;
        if (dto.getName() != null) {
            flag = dto.getName().equals(findGoods.getName()) ? true : false;
        }
        if (dto.getPrice() != null) {
            flag = dto.getPrice().compareTo(findGoods.getPrice()) == 0 ? true : false;
        }
        if (dto.getMainYn() != null) {
            flag = dto.getMainYn().equals(findGoods.getMainYn()) ? true : false;
        }
        if (dto.getThumbnailImgName() != null) {
            flag = dto.getThumbnailImgName().equals(findGoods.getThumbnailImgName()) ? true : false;
        }
        if (dto.getThumbnailImgPath() != null) {
            flag = dto.getThumbnailImgPath().equals(findGoods.getThumbnailImgPath()) ? true : false;
        }
        if (dto.getDetailImgName() != null) {
            flag = dto.getDetailImgName().equals(findGoods.getDetailImgName()) ? true : false;
        }
        if (dto.getDetailImgPath() != null) {
            flag = dto.getDetailImgPath().equals(findGoods.getDetailImgPath()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 굿즈 삭제
     */
    @DeleteMapping(ADMIN_GOODS_PREFIX + "/delete")
    public ResponseEntity<Map<String, Object>> deleteGoods(@RequestBody List<Integer> ids) {
        log.info("굿즈 삭제: {}", ids);

        for (int id : ids) {
            long idL = new Long(id);
            Goods goods = goodsService.findOne(idL);
            if (goods.getThumbnailImgPath() != null) {
                // 썸네일 이미지 삭제
                File thumbnail = new File(goods.getThumbnailImgPath());
                if (thumbnail.exists()) {
                    thumbnail.delete();
                }
            }
            if (goods.getDetailImgPath() != null) {
                // 상세 이미지 삭제
                File detail = new File(goods.getDetailImgPath());
                if (detail.exists()) {
                    detail.delete();
                }
            }
            goodsService.delete(idL);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return createResponseEntity(true, result);
    }

    /**
     * 굿즈 상태 변경
     */
    @PutMapping(ADMIN_GOODS_PREFIX + "/updateStatus/{status}")
    public ResponseEntity<Map<String, Object>> updateGoodsStatus(
            @RequestBody List<Integer> ids, @PathVariable GoodsStatus status) {
        log.info("굿즈 상태 변경: {}", ids);

        boolean flag = true;

        if (ids != null) {
            for(int id : ids) {
                long idL = new Long(id);
                goodsService.updateStatus(idL, status);

                Goods findGoods = goodsService.findOne(idL);
                if (status != null) {
                    flag = status.equals(findGoods.getStatus()) ? true : false;
                }
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }

    /**
     * 굿즈 메인 설정
     */
    @PutMapping(ADMIN_GOODS_PREFIX + "/setMainYn/{id}/{mainYn}")
    public ResponseEntity<Map<String, Object>> setGoodsMainYn(
            @PathVariable Long id, @PathVariable String mainYn) {
        log.info("굿즈 메인 설정: {}", id);

        goodsService.setMainYn(id, mainYn);

        Goods findGoods = goodsService.findOne(id);
        boolean flag = true;
        if (mainYn != null) {
            flag = mainYn.equals(findGoods.getMainYn()) ? true : false;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", flag);
        return createResponseEntity(true, result);
    }
}
