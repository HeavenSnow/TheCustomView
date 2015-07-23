package com.ruby.customandroiddemo.http;

import org.apache.http.HttpStatus;

/**
 * 封装HttpResponse的返回结果,包含返回的状态码和返回的内容
 * 
 */
public class HttpResult {
	private String statusCode;
	private String response;

	/**
	 * 构造方法
	 * @param statusCode
	 * @param response
	 */
	public HttpResult(String statusCode, String response) {
		this.statusCode = statusCode;
		this.response = response;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public String getResponse() {
		return response;
	}

	/**
	 * 是否成功的返回响应
	 * @return
	 */
	public boolean isResponseNormal() {
		return String.valueOf(HttpStatus.SC_OK).equals(statusCode);
	}
}
