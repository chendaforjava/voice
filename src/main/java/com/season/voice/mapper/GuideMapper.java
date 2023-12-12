package com.season.voice.mapper;

import com.season.voice.db.MyBaseMapper;
import com.season.voice.entity.Guide;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuideMapper  extends MyBaseMapper<Guide> {

    List<Guide> findByCityCode(@Param("cityCode") String cityCode);

}
