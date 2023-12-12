package com.season.voice.service;

import com.season.voice.dto.GuideDTO;

import java.util.List;

public interface GuideService {

    List<GuideDTO> getGuideByCityCode(String cityCode);

}
