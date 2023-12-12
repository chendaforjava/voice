package com.season.voice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.season.voice.entity.ScenicSpots;
import com.season.voice.mapper.ScenicSpotsMapper;
import com.season.voice.service.ScenicSpotsService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ScenicSpotsServiceImpl extends ServiceImpl<ScenicSpotsMapper, ScenicSpots> implements ScenicSpotsService {

    @Override
    public List<ScenicSpots> recommendScenicSpots(String cityCode) {
        LambdaQueryWrapper<ScenicSpots> spotsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        spotsLambdaQueryWrapper.eq(ScenicSpots::getCityCode,cityCode);
        return this.baseMapper.selectList(spotsLambdaQueryWrapper);
    }
}
