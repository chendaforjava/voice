package com.season.voice.mapper;

import com.season.voice.db.MyBaseMapper;
import com.season.voice.dto.HomePageDetailDTO;
import com.season.voice.entity.HomePage;

import java.util.List;

public interface HomePageMapper extends MyBaseMapper<HomePage> {

    List<HomePageDetailDTO> homePageInfo();

}
