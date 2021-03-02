package com.gymbuddy.backgymbuddy.admin.goods.service;

import com.gymbuddy.backgymbuddy.admin.enums.status.GoodsStatus;
import com.gymbuddy.backgymbuddy.admin.goods.domain.Goods;
import com.gymbuddy.backgymbuddy.admin.goods.domain.GoodsDto;
import com.gymbuddy.backgymbuddy.admin.goods.domain.GoodsOption;
import com.gymbuddy.backgymbuddy.admin.goods.domain.GoodsOptionDto;
import com.gymbuddy.backgymbuddy.admin.goods.repository.GoodsOptionRepository;
import com.gymbuddy.backgymbuddy.admin.goods.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;
    private final GoodsOptionRepository optionRepository;

    public List<Goods> findAll(int page) {
        return goodsRepository.findAllByMainYnAndCreateDate(PageRequest.of(page, 10, Sort.by("id").descending())).getContent();
    }

    public int selectTotalCount() {
        return goodsRepository.findAll().size();
    }

    public List<GoodsDto> findAllByDto(int page) {
        List<Goods> goodsList = findAll(page);
        List<GoodsDto> dtoList = new ArrayList<>();
        for (Goods goods : goodsList) {
            GoodsDto dto = goodsToDto(goods);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private GoodsDto goodsToDto(Goods goods) {
        GoodsDto dto = new GoodsDto();
        if (goods.getId() != null) {
            dto.setId(goods.getId());
        }
        if (goods.getName() != null) {
            dto.setName(goods.getName());
        }
        if (goods.getPrice() != null) {
            dto.setPrice(goods.getPrice());
        }
        if (goods.getMainYn() != null) {
            dto.setMainYn(goods.getMainYn());
        }
        if (goods.getStatus() != null) {
            dto.setStatus(goods.getStatus());
        }
        if (goods.getThumbnailImgName() != null) {
            dto.setThumbnailImgName(goods.getThumbnailImgName());
        }
        if (goods.getThumbnailImgPath() != null) {
            dto.setThumbnailImgPath(goods.getThumbnailImgPath());
        }
        if (goods.getDetailImgName() != null) {
            dto.setDetailImgName(goods.getDetailImgName());
        }
        if (goods.getThumbnailImgPath() != null) {
            dto.setDetailImgPath(goods.getThumbnailImgPath());
        }
        if (!goods.getGoodsOptions().isEmpty()) {
            List<GoodsOptionDto> optionDtoList = new ArrayList<>();
            for (GoodsOption option : goods.getGoodsOptions()) {
                GoodsOptionDto optionDto = new GoodsOptionDto();
                if (option.getId() != null) {
                    optionDto.setId(option.getId());
                }
                if (option.getColorAndSize() != null) {
                    optionDto.setColorAndSize(option.getColorAndSize());
                }
                if (option.getInventory() != 0) {
                    optionDto.setInventory(option.getInventory());
                }
                if (option.getExtraPrice() != null) {
                    optionDto.setExtraPrice(option.getExtraPrice());
                }
                optionDtoList.add(optionDto);
            }
            dto.setOptionList(optionDtoList);
        }
        return dto;
    }

    public Goods findOne(Long id) {
        Goods goods = goodsRepository.findById(id).get();
        List<GoodsOption> optionList = optionRepository.findAllByGoodsId(id);
        goods.setGoodsOptions(optionList);
        return goods;
    }

    public GoodsOption findOption(Long id) {
        return optionRepository.findById(id).get();
    }

    public GoodsDto findOneByDto(Long id) {
        Goods goods = findOne(id);
        return goodsToDto(goods);
    }

    @Transactional
    public Long save(GoodsDto dto) {
        Goods goods = new Goods();
        if (dto.getName() != null) {
            goods.setName(dto.getName());
        }
        if (dto.getPrice() != null) {
            goods.setPrice(dto.getPrice());
        }
        if (dto.getMainYn() != null) {
            goods.setMainYn(dto.getMainYn());
        }
        if (dto.getStatus() != null) {
            goods.setStatus(dto.getStatus());
        }
        if (dto.getThumbnailImgName() != null) {
            goods.setThumbnailImgName(dto.getThumbnailImgName());
        }
        if (dto.getThumbnailImgPath() != null) {
            goods.setThumbnailImgPath(dto.getThumbnailImgPath());
        }
        if (dto.getDetailImgName() != null) {
            goods.setDetailImgName(dto.getDetailImgName());
        }
        if (dto.getDetailImgPath() != null) {
            goods.setDetailImgPath(dto.getDetailImgPath());
        }
        goodsRepository.save(goods);

        List<GoodsOptionDto> optionList = dto.getOptionList();
        for (GoodsOptionDto optionDto : optionList) {
            GoodsOption option = new GoodsOption();
            if (optionDto.getColorAndSize() != null) {
                option.setColorAndSize(optionDto.getColorAndSize());
            }
            if (optionDto.getInventory() != 0) {
                option.setInventory(optionDto.getInventory());
            }
            if (optionDto.getExtraPrice() != null) {
                option.setExtraPrice(optionDto.getExtraPrice());
            }
            option.setGoods(goods);
            optionRepository.save(option);
        }

        return goods.getId();
    }

    @Transactional
    public void update(Long id, GoodsDto dto) {
        Goods goods = findOne(id);
        if (dto.getName() != null) {
            goods.setName(dto.getName());
        }
        if (dto.getPrice() != null) {
            goods.setPrice(dto.getPrice());
        }
        if (dto.getMainYn() != null) {
            goods.setMainYn(dto.getMainYn());
        }
        if (dto.getStatus() != null) {
            goods.setStatus(dto.getStatus());
        }
        if (dto.getThumbnailImgName() != null) {
            goods.setThumbnailImgName(dto.getThumbnailImgName());
        }
        if (dto.getThumbnailImgPath() != null) {
            goods.setThumbnailImgPath(dto.getThumbnailImgPath());
        }
        if (dto.getDetailImgName() != null) {
            goods.setDetailImgName(dto.getDetailImgName());
        }
        if (dto.getDetailImgPath() != null) {
            goods.setDetailImgPath(dto.getDetailImgPath());
        }

        List<GoodsOptionDto> optionList = dto.getOptionList();
        if (!optionList.isEmpty()) {
            for (GoodsOptionDto optionDto : optionList) {
                GoodsOption option = findOption(optionDto.getId());
                if (optionDto.getColorAndSize() != null) {
                    option.setColorAndSize(optionDto.getColorAndSize());
                }
                if (optionDto.getInventory() != 0) {
                    option.setInventory(optionDto.getInventory());
                }
                if (optionDto.getExtraPrice() != null) {
                    option.setExtraPrice(optionDto.getExtraPrice());
                }
            }
        }
    }

    @Transactional
    public void delete(Long id) {
        goodsRepository.deleteById(id);
    }

    public List<GoodsDto> findAllForMain() {
        List<Goods> list = goodsRepository.findAllByMainYn("Y");
        List<GoodsDto> dtoList = new ArrayList<>();
        list.stream().forEach(goods -> {
            GoodsDto dto = new GoodsDto();
            dto.setId(goods.getId());
            dto.setName(goods.getName());
            dto.setPrice(goods.getPrice());
            dto.setMainYn(goods.getMainYn());
            dto.setStatus(goods.getStatus());
            if (goods.getThumbnailImgPath() != null) {
                dto.setThumbnailImgPath(goods.getThumbnailImgPath());
            }
            if (goods.getThumbnailImgName() != null) {
                dto.setThumbnailImgName(goods.getThumbnailImgName());
            }
            if (goods.getDetailImgPath() != null) {
                dto.setDetailImgPath(goods.getDetailImgPath());
            }
            if (goods.getDetailImgName() != null) {
                dto.setDetailImgName(goods.getDetailImgName());
            }
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Transactional
    public void updateStatus(Long id, GoodsStatus status) {
        Goods goods = findOne(id);
        if (status != null) {
            goods.setStatus(status);
        }
    }
}
