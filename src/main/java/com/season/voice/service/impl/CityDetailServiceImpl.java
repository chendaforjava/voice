package com.season.voice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.season.voice.dto.CityDetailDTO;
import com.season.voice.dto.GuideDTO;
import com.season.voice.dto.ScenicSpotsDTO;
import com.season.voice.entity.CityDetail;
import com.season.voice.entity.Guide;
import com.season.voice.entity.ScenicSpots;
import com.season.voice.mapper.CityDetailMapper;
import com.season.voice.mapper.GuideMapper;
import com.season.voice.service.CityDetailService;
import com.season.voice.service.GuideService;
import com.season.voice.service.ScenicSpotsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CityDetailServiceImpl extends ServiceImpl<CityDetailMapper, CityDetail> implements CityDetailService {

    @Resource
    private CityDetailMapper cityDetailMapper;

    @Resource
    private GuideService guideService;


    @Resource
    private ScenicSpotsService scenicSpotsService;

    @Override
    public CityDetailDTO getCityDetail(String cityCode) {
        CityDetail cityDetail = cityDetailMapper.selectById(cityCode);
        if(Objects.nonNull(cityDetail)){
            CityDetailDTO cityDetailDTO = new CityDetailDTO();
            BeanUtils.copyProperties(cityDetail,cityDetailDTO);
            cityDetailDTO.setGuideRecommendList(guideService.getGuideByCityCode(cityCode));
            List<ScenicSpots> scenicSpots = scenicSpotsService.recommendScenicSpots(cityCode);
            if(!CollectionUtils.isEmpty(scenicSpots)){
                cityDetailDTO.setScenicSpotsRecommend(BeanUtil.copyToList(scenicSpots, ScenicSpotsDTO.class));
            }
            return cityDetailDTO;
        }
        return null;
    }
}
