package sp.exam.controller;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;

public class TestFiendController {
	
	@Test
	public void testAddFriend() {
		RestAssured.given()
			.headers(new Headers(new Header("Content-Type", "application/json")))
			.body("{ \"friends\": [ \"andy@example.com\", \"john@example.com\" ]}")
		.when().post("/friend/add")
		.then()
		.assertThat()
			.body("success", Matchers.equalTo(true))
			;
	}
	
	@Test
	public void testListFriend() {
		RestAssured.given()
		.headers(new Headers(new Header("Content-Type", "application/json")))
		.body("{ \"email\" : \"andy@example.com\" }")
		.when().post("/friend/list")
		.then()
		.assertThat()
			.body("success", Matchers.equalTo(true))
			.body("count", Matchers.greaterThan(0))
			.body("friends", Matchers.hasItem("john@example.com"))
			;
	}
}
