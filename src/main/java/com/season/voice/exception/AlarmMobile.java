package com.season.voice.exception;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 告警手机配置
 * 按公司规定，要求所有代码文件的首行增加以下版权内容：
 * Copyright© 2020年12月4日 by 博纳德集团有限公司.All rights reserved.
 * 1.版权归博纳德集团公司所有；
 * 2.未经原作者允许不得转载本代码内容，否则将视为侵权；
 * 3.对于不遵守此声明或者其他违法使用本代码内容者，本公司依法保留追究权。
 * @author hhs
 * @createTime 2020年12月4日
 */
@Component
@Data
public class AlarmMobile {

	@Value("${alarm.mobile:}")
	private String alarmMobile;

	@Override
	public String toString() {
		return alarmMobile;
	}

}
