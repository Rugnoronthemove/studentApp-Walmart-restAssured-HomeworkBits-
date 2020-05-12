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
public class StudentPutTest extends TestBase {
    //homework add 1 Request

    @Test
    public void updateStudentEmail() {

        List<String> courses = new ArrayList<>();
        courses.add("Challenging");
        courses.add("HomeWork");


        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("V");
        studentPojo.setLastName("H");
        studentPojo.setEmail("PostMan123@gmail.com");
        studentPojo.setProgramme("Financial Analysis");
        studentPojo.setCourses(courses);

        Response response = given()
                .header("Content-Type", "application/json")
                .log()
                .body()
                .when()
                .body(studentPojo)
                .put("/1");
        response.then().statusCode(200);
    }
}
