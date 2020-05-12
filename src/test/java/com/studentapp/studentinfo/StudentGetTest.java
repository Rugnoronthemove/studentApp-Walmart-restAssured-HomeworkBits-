package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Jay
 */
public class StudentGetTest extends TestBase {


    //sending GET request
    @Test
    public void getAllStudentsInfo() {
        Response response = given()
                .when()
                .get("/list");
        response.then().statusCode(200);

        //prettyPrint() converts response in console in pretty format just like postman
        System.out.println(response.prettyPrint());

        //response.asString() as response is object converting it to string in RestAssured
        //System.out.println(response.asString());

        //response.toString() as response is object converting it to string
        //System.out.println(response.toString());
    }

    //sending GET request
    @Test
    public void searchStudentWithParameterSettingLimit() {
        Response response =given()
                .param("programme", "Computer Science")
                .param("limit", 2)
                .when()
                .get("/list");
        response.then().statusCode(200);

    }

    //two other Get methods is HomeWork
    @Test
    public void searchStudentWithParameter() {
        Response response =given()
                .param("programme", "Law")
                .when()
                .get("/list");
                response.then().statusCode(200);
                response.prettyPrint();
    }

    @Test
    public void getStudentById() {
        Response response = given()
                .when()
                .get("/2");
                response.then().statusCode(200);
                response.prettyPrint();
    }

}
