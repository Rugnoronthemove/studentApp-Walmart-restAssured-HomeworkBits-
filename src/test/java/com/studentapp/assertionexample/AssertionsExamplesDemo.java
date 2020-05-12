package com.studentapp.assertionexample;

import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AssertionsExamplesDemo {

    private static final String API_KEY = "75e3u4sgb2khg673cbv2gjup";

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://api.walmartlabs.com";
        RestAssured.basePath = "/v1";
    }


    // 1) Verify if the number of items is equal to 10
    @Test
    public void test001() {

        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .body("numItems", equalTo(10));

    }

    // 2) Verify Query = ipod
    @Test
    public void test002() {
        //Home work
        given()
                .queryParam("query","ipod")
                .queryParam("format","json")
                .queryParam("apikey",API_KEY)
                .when()
                .get("/search")
                .then()
                .body("query",equalTo("ipod"));
    }

    // 3) Check Single Name in ArrayList (Apple iPod touch 7th Generation 32GB - Space Gray (New Model))
    @Test
    public void test003() {

        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                //hasItem as it returns List of items
                .body("items.name", hasItem("Apple iPod touch 7th Generation 32GB - Space Gray (New Model)"));
                //use hasItems for multiple assertion

    }

    // 4) Check Multiple Names in ArrayList ("Apple iPod touch 7th Generation 128GB - Gold (New Model)","Apple iPod touch 32GB  (Assorted Colors)")
    @Test
    public void test004() {
        //hint: use hasItems for multiple assertion
        //Homework
        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .body("items.name",hasItems("Apple iPod touch 7th Generation 128GB - Gold (New Model)","Apple iPod touch 32GB  (Assorted Colors)"));

    }

    // 5) Verify the entityType inside imageEntities for the first product (Checking Values inside Map using hasKey(entityType))
    @Test
    public void test005() {

        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                //hasKey is for Key value assertion
                .body("items[0].imageEntities[0]", hasKey("entityType"));

    }

    // 6) Check hash map values inside a list categoryPath=Home Page/Electronics/Portable Audio/Apple iPods/iPod Touch
    @Test
    public void test006() {
        //Home work
        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .body("items[3,5].categoryPath",hasItem("Home Page/Electronics/Portable Audio/Apple iPods/iPod Touch"));

    }

    // 7) Checking multiple values in the same statement
    @Test
    public void test007() {

        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                //is kind of hard assert and will fail all if any one of them fail and not execute further
                .body("items[0].imageEntities[0]", hasKey("entityType"))
                .body("numItems", equalTo(10))
                .body("items.name", hasItem("Apple iPod touch 7th Generation 32GB - Space Gray (New Model)"));

    }

    // 8) Logical Assertions
    @Test
    public void test008() {

        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .body("items.size()", equalTo(10))
                .body("items.size()", greaterThan(8))
                .body("items.size()", lessThan(12))
                .body("items.size()", lessThanOrEqualTo(10));


    }

}
