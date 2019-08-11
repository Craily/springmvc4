package com.craily.messageconverter;

import java.io.IOException;
import java.nio.charset.Charset;


import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.craily.advic.DemoObj;

public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

	/**
	 * 
	 */
	public MyMessageConverter() {
		super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
	}

	/**
	 * 表明本HttpMessageConverter只处理DemoObj这个类。
	 */
	@Override
	protected boolean supports(Class<?> clazz) {
		return DemoObj.class.isAssignableFrom(clazz);
	}

	/**
	 * 重写readInternal方法，处理请求的数据。
	 */
	@Override
	protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
		String[] tempArr = temp.split("-");
		return new DemoObj(new Long(tempArr[0]), tempArr[1]);
	}

	/**
	 * 重写writeInternal，处理如何输出数据到response。
	 */
	@Override
	protected void writeInternal(DemoObj t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		String out = "hello:" + t.getTempArr1() + "-" + t.getTempArr2();
		outputMessage.getBody().write(out.getBytes());
	}

}
