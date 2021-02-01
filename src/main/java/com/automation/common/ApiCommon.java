package com.automation.common;

import com.automation.framework.AbastractBase;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.*;

import java.util.List;

/**
 * api公共方法类
 * @author mazy
 */
public class ApiCommon extends AbastractBase{
	//api common method to create
	
	String stfTokenValue = "Bearer YOUR-TOKEN-HERE";//登录token
	String stfTokenName = "Authorization";//登录token名称
	String stfContentType = "application/json";
	
	static {
		RestAssured.baseURI = "http://mystf.org";		
	}
	
	/**
	 * check device status
	 * @author: mazy
	 * @param udid
	 * @return
	 */
	public boolean deviceCanUse(String udid) {
		Response response = given()
                //ssl configure all hosts are support 
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .header(stfTokenName, stfTokenValue)
                .contentType(stfContentType)
                .get("/api/v1/devices/" + udid);
		response.prettyPrint();
		return response.jsonPath().get("using");
	}
	
	/**
	 * get devices list
	 * @author: mazy
	 * @return
	 */
	public List<String> getDevices() {
		Response response = given()
				.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
				.header(stfTokenName, stfTokenValue)
				.contentType(stfContentType)
				.get("/api/v1/devices");
		response.prettyPrint();		
		List<String> devicesList = response.jsonPath().getList("serial");
		return devicesList;
	}	
	
	/**
	 * get ready device
	 * @author: mazy
	 * @return
	 */
	public List<String> getReadyDevices() {
		Response response = given()
				.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
				.header(stfTokenName, stfTokenValue)
				.contentType(stfContentType)
				.get("/api/v1/devices");
		response.prettyPrint();		
		List<String> devicesList = response.jsonPath().getList("serial");
		List<Boolean> statusList = response.jsonPath().getList("ready");
		for(int i=0; i<statusList.size(); i++) {
			if(!statusList.get(i)) {
				devicesList.remove(i);
			}
		}
		return devicesList;
	}
	/**
	 * current device using user
	 * @author: mazy
	 * @param udid
	 * @return
	 */
	public String deviceUsingUser(String  udid) {
		Response response = given()
				.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
				.header(stfTokenName, stfTokenValue)
				.contentType(stfContentType)
				.get("/api/v1/user");
		response.prettyPrint();
		return response.toString();
	}
	
	/**
	 * disconnect device
	 * @author: mazy   
	 * @param udid
	 */
	public void disConnect(String udid) {
		Response response = given()
				.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
				.header(stfTokenName, stfTokenValue)
				.contentType(stfContentType)
				.get("api/v1/user/devices/" + udid);
		response.prettyPrint();
	}
}
