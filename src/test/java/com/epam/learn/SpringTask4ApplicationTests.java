package com.epam.learn;

import com.epam.learn.model.UserAccount;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
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
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringTask4ApplicationTests {

	@Test
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
	void addUserDetailsTest() {
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



	@Test
	public void testGetUserAccount() {
		String URI = "http://localhost:8084/api/user/{id}";
		RestTemplate restTemplate = new RestTemplate();
		UserAccount userAccount = restTemplate.getForObject(URI, UserAccount.class, "1");
		Assert.assertEquals(Long.valueOf(1), userAccount.getId());
	}
}
