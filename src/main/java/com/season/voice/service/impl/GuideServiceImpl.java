package com.season.voice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.season.voice.dto.GuideDTO;
import com.season.voice.entity.Guide;
import com.season.voice.mapper.GuideMapper;
import com.season.voice.service.GuideService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class GuideServiceImpl  extends ServiceImpl<GuideMapper, Guide> implements GuideService {

    @Override
    public List<GuideDTO> getGuideByCityCode(String cityCode) {
        List<GuideDTO> guideDTOList = new ArrayList<>();
        if(!StringUtils.isEmpty(cityCode)){
            List<Guide> guideList = this.baseMapper.findByCityCode(cityCode);
            if(!CollectionUtils.isEmpty(guideList)){
                for(Guide guide : guideList){
                    GuideDTO dto = new GuideDTO();
                    BeanUtils.copyProperties(guide,dto);
                    if(!StringUtils.isEmpty(guide.getLabel())){
                        dto.setLabel(Arrays.asList(guide.getLabel().split(";")));
                    }
                    guideDTOList.add(dto);
                }
            }
        }
        return guideDTOList;
    }
}
