package com.season.voice.controller;


import com.season.voice.dto.CityDetailDTO;
import com.season.voice.dto.DataResult;
import com.season.voice.dto.R;
import com.season.voice.service.CityDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/city")
@Api(tags = "城市页面")
public class CityController {

    @Resource
    private CityDetailService cityService;

    @GetMapping("/cityDetail/{cityCode}")
    @ApiOperation(value = "城市详情")
    public DataResult<CityDetailDTO> scenicSpotInfo(@ApiParam(name = "cityCode", value = "城市编码", required = true) @PathVariable("cityCode") String cityCode) {
        return R.success(cityService.getCityDetail(cityCode));
    }

}
