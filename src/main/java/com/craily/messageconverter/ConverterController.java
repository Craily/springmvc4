package com.craily.messageconverter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.craily.advic.DemoObj;

@Controller
public class ConverterController {

	/**
	 * produces = {"application/x-wisely"} 指定返回格式为自定义的格式
	 */
	@RequestMapping(value = "/convert", produces = {"application/x-wisely"})
	public @ResponseBody DemoObj convert(@RequestBody DemoObj demoObj) {
		return demoObj;
	}
}
