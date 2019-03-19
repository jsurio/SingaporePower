package sp.exam.controller;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFriendController {
	
	@Test
	public void aTestAdd() {
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
	public void bTestList() {
		RestAssured.given()
			.headers(new Headers(new Header("Content-Type", "application/json")))
			.body("{ \"friends\": [ \"andy@example.com\", \"john@example.com\" ]}")
		.when().post("/friend/add")
		;
		
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
	
	@Test
	public void cTestCommon() {
		RestAssured.given()
			.headers(new Headers(new Header("Content-Type", "application/json")))
			.body("{ \"friends\": [ \"andy@example.com\","
					+ " \"john@example.com\", \"common@example.com\","
					+ " \"anothercommon@example.com\", \"morecommon@example.com\"]}")
		.when().post("/friend/add")
		;
		
		RestAssured.given()
		.headers(new Headers(new Header("Content-Type", "application/json")))
		.body("{ \"friends\": [ \"andy@example.com\", \"john@example.com\" ]}")
		.when().post("/friend/common")
		.then()
		.assertThat()
			.body("success", Matchers.equalTo(true))
			.body("count", Matchers.greaterThan(0))
			.body("friends", Matchers.hasItem("common@example.com"))
			.body("friends", Matchers.hasItem("anothercommon@example.com"))
			.body("friends", Matchers.hasItem("morecommon@example.com"))
		;
	}
	
	@Test
	public void dTestSubscribe() {
		RestAssured.given()
		.headers(new Headers(new Header("Content-Type", "application/json")))
		.body("{\r\n" + 
				"\"requestor\": \"requestor@example.com\",\r\n" + 
				"\"target\": \"target@example.com\"\r\n" + 
				"}")
		.when().post("/friend/subscribe")
		.then()
		.assertThat()
			.body("success", Matchers.equalTo(true))
		;
	}
	
	@Test
	public void eTestBlock() {
		RestAssured.given()
		.headers(new Headers(new Header("Content-Type", "application/json")))
		.body("{\r\n" + 
				"\"requestor\": \"requestor@example.com\",\r\n" + 
				"\"target\": \"target@example.com\"\r\n" + 
				"}")
		.when().post("/friend/block")
		.then()
		.assertThat()
			.body("success", Matchers.equalTo(true))
		;
	}
	
	@Test
	public void fTestNotify() {
		RestAssured.given()
		.headers(new Headers(new Header("Content-Type", "application/json")))
		.body("{\r\n" + 
				"\"sender\": \"john@example.com\",\r\n" + 
				"\"text\": \"Hello World! kate@example.com\"\r\n" + 
				"}")
		.when().post("/friend/notify")
		.then()
		.assertThat()
			.body("success", Matchers.equalTo(true))
			.body("recipients", Matchers.hasItem("kate@example.com"))
//			.body("recipients", Matchers.hasItem("andy@example.com"))
		;
		
		RestAssured.given()
		.headers(new Headers(new Header("Content-Type", "application/json")))
		.body("{\r\n" + 
				"\"sender\": \"requestor@example.com\",\r\n" + 
				"\"text\": \"Hello World! nice@spexam.com\"\r\n" + 
				"}")
		.when().post("/friend/notify")
		.then()
		.assertThat()
			.body("success", Matchers.equalTo(true))
			.body("recipients", Matchers.not(Matchers.hasItem("target@example.com")))
			.body("recipients", Matchers.hasItem("nice@spexam.com"))
		;
	}
}
