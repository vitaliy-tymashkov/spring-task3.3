package com.epam.learn;

import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;
import io.restassured.*;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest
class SpringTask4ApplicationTests {

	@Test
	@Ignore
	void contextLoads() {
	}

	@Test
	@Ignore
	void getUserDetailsTest() {
		//The base URI to be used
		RestAssured.baseURI = "http://localhost:8084/api/user/";

		//Define the specification of request. Server is specified by baseURI above.
		RequestSpecification httpRequest = RestAssured.given();

		//Makes calls to the server using Method type.
		Response response = httpRequest.request(Method.GET, "1");

		//Checks the Status Code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void updateUserDetailsTest() {
		RestAssured.baseURI = "http://localhost:8084/api/user/addUser";

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject updateData = new JSONObject();
		updateData.put("name", "Ivan");
		updateData.put("phoneNumber", "1111");
		updateData.put("phoneOperator", "Orange");
		updateData.put("balance", 1111);

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(updateData.toJSONString());
		Response response = httpRequest.request(Method.POST, "4");
		int statusCode = response.getStatusCode();

		Assert.assertEquals(statusCode, 200);

		JsonPath newData = response.jsonPath();
		String name = newData.get("name");
		Assert.assertEquals(name, "Ivan");
	}
}
