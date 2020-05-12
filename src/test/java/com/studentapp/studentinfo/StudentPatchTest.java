package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Jay
 */
public class StudentPatchTest extends TestBase {

    //homework add 1 Request
    @Test
    public void updateStudentEmailUsingId() {

        StudentPojo studentPojo = new StudentPojo();

        studentPojo.setEmail("PTesting@gmail.com");

        Response response =given()
                .header("Content-Type", "application/json")
                .log()
                .body()
                .when()
                .body(studentPojo)
                .patch("/1");
                response.then().statusCode(200);

    }

}
