package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Created by Jay
 */
public class StudentPostTest extends TestBase {


    //sending POST request
    @Test
    public void createStudent() {

        List<String> course = new ArrayList<>();
        course.add("Selenium");
        course.add("Java");

        //creating object of StudentPojo class(payload body from postman)
        StudentPojo studentPojo = new StudentPojo();
        //setting values
        studentPojo.setFirstName("Prime");
        studentPojo.setLastName("Testing");
        studentPojo.setEmail("xyz123456@gmail.com");
        studentPojo.setProgramme("Automation Testing");
        studentPojo.setCourses(course);

        Response response = given()
                .header("Content-Type", "application/json")
                .log()
                .body()
                .when()
                .body(studentPojo)
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

}
