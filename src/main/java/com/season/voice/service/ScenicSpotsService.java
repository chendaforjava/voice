package com.season.voice.service;

import com.season.voice.entity.ScenicSpots;

import java.util.List;

public interface ScenicSpotsService {

    List<ScenicSpots> recommendScenicSpots(String cityCode);

}
