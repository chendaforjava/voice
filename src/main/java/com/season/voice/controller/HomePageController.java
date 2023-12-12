package com.season.voice.controller;

import com.season.voice.dto.*;
import com.season.voice.service.HomePageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/homePage")
@Api(tags = "首页")
public class HomePageController {
    @Resource
    private HomePageService homePageService;

    @GetMapping("/homePageInfo")
    @ApiOperation(value = "首页信息")
    public DataResult<HomePageDTO> scenicSpotInfo() {
        return R.success(homePageService.homePageInfo());
    }


}
