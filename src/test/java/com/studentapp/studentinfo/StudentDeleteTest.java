package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by Jay
 */
public class StudentDeleteTest extends TestBase {

    //homework add 1 Request
    @Test
    public void deleteAStudentRecordById() {

        given()
                .log()
                .body()
                .when()
                .delete("/1")
                .then().statusCode(204).log().all();
    }
}
