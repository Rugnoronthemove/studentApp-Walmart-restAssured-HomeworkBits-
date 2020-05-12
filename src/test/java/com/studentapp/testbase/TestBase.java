package com.studentapp.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {

    //set up for @Test
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/student";
    }


}
