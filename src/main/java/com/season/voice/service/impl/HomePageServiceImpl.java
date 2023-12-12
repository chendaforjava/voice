package com.season.voice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.season.voice.dto.HomePageDTO;
import com.season.voice.dto.HomePageDetailDTO;
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
        HomePageDTO homePageDTO = new HomePageDTO();
        List<HomePageDetailDTO> homePageDetailDTOList = this.baseMapper.homePageInfo();
        HomePageDetailDTO homePageDetailDTO;
        if(!CollectionUtils.isEmpty(homePageDetailDTOList)){
            homePageDetailDTO = homePageDetailDTOList.get(0);
            homePageDTO.setHomePageDetail(homePageDetailDTO);
            String cityCode = homePageDetailDTO.getCityCode();
            homePageDTO.setGuideList(guideService.getGuideByCityCode(cityCode));
            if(homePageDetailDTOList.size()>1){
                List<HomePageDetailDTO> recommendList = homePageDetailDTOList.subList(1,homePageDetailDTOList.size());
                homePageDTO.setRecommendList(recommendList);
            }
            return homePageDTO;
        }
        return null;
    }

}
