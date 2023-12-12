package com.season.voice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.season.voice.dto.GuideDTO;
import com.season.voice.dto.HomePageDTO;
import com.season.voice.entity.*;
import com.season.voice.mapper.GuideMapper;
import com.season.voice.mapper.HomePageMapper;
import com.season.voice.service.HomePageService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class HomePageServiceImpl extends ServiceImpl<HomePageMapper, HomePage> implements HomePageService {


    @Resource
    private GuideMapper guideMapper;

    @Override
    public HomePageDTO homePageInfo() {
        List<HomePageDTO> homePageDTOList = this.baseMapper.homePageInfo();
        HomePageDTO homePageDTO = null;
        if(!CollectionUtils.isEmpty(homePageDTOList)){
            homePageDTO = homePageDTOList.get(0);
            String cityCode = homePageDTO.getCityCode();
            List<GuideDTO> guideDTOList = new ArrayList<>();
            if(!StringUtils.isEmpty(cityCode)){
                List<Guide> guideList = guideMapper.findByCityCode(cityCode);
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
            homePageDTO.setGuideList(guideDTOList);
            if(homePageDTOList.size()>1){
                List<HomePageDTO> recommendList = homePageDTOList.subList(1,homePageDTOList.size());
                homePageDTO.setRecommendList(recommendList);
            }
        }
        return homePageDTO;
    }

}
