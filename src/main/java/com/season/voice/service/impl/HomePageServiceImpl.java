package com.season.voice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.season.voice.dto.HomePageDTO;
import com.season.voice.entity.*;
import com.season.voice.mapper.HomePageMapper;
import com.season.voice.service.GuideService;
import com.season.voice.service.HomePageService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class HomePageServiceImpl extends ServiceImpl<HomePageMapper, HomePage> implements HomePageService {


    @Resource
    private GuideService guideService;

    @Override
    public HomePageDTO homePageInfo() {
        List<HomePageDTO> homePageDTOList = this.baseMapper.homePageInfo();
        HomePageDTO homePageDTO = null;
        if(!CollectionUtils.isEmpty(homePageDTOList)){
            homePageDTO = homePageDTOList.get(0);
            String cityCode = homePageDTO.getCityCode();
            homePageDTO.setGuideList(guideService.getGuideByCityCode(cityCode));
            if(homePageDTOList.size()>1){
                List<HomePageDTO> recommendList = homePageDTOList.subList(1,homePageDTOList.size());
                homePageDTO.setRecommendList(recommendList);
            }
        }
        return homePageDTO;
    }

}
