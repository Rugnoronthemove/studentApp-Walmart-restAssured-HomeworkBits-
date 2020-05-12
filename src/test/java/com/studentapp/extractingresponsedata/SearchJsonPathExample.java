package com.studentapp.extractingresponsedata;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Created by Jay
 */
public class SearchJsonPathExample {

    private static final String API_KEY = "75e3u4sgb2khg673cbv2gjup";

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://api.walmartlabs.com";
        RestAssured.basePath = "/v1";
    }


    // 1) Extract numItems
    @Test
    public void test001() {

        //storing into int variable numItems as value of numItems is of int type
        int numItems = given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("numItems");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of items are: " + numItems);
        System.out.println("------------------End of Test---------------------------");

    }

    // 2) Extract query
    @Test
    public void test002() {
        //Home work

        String query = given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("query");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The search query is: " + query);
        System.out.println("------------------End of Test---------------------------");

    }

    // 3) Extract first productName by providing list index value
    @Test
    public void test003() {

        String itemName = given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items[0].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product name is: " + itemName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4) Get the first list from imageEntities for the first product
    @Test
    public void test004() {
        //Home work

        HashMap<String, Object> firstList = given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items[0].imageEntities[0]");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gift options under the first product are: " + firstList);
        System.out.println("------------------End of Test---------------------------");
    }

    // 5)Print the size of items
    @Test
    public void test005() {
        //Home work

        int sizeOfItems = given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items.size");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the items is: " + sizeOfItems);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6) Get All the Names from Items
    @Test
    public void test006() {
        //item.name returns list of names, so need to store all the names in List<String>
        //Home work
        List<String> namesOfItem = given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the products are: "+namesOfItem);
        System.out.println("------------------End of Test---------------------------");
    }

    // 7) Get the all the values for Name == Apple iPod touch 32GB  (Assorted Colors)
    @Test
    public void test007() {
        //HashMap is same as Map in Java
        // using String as key is String
        // and value is of different values so using Object(ex some are int and some are string

        List<HashMap<String, Object>> values = given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                //.findAll and it.name is from groovy //as all value
                .extract().path("items.findAll{it.name == 'Apple iPod touch 32GB  (Assorted Colors)'}");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for item name Apple iPod touch 32GB  (Assorted Colors) are: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8) Get the sale price for Name == Refurbished Apple iPod Touch 7th Generation 32GB Space Gray MVHW2LL/A
    @Test
    public void test008() {

        //one thing need to change only the name from above query and after curly braces provide what specific you are after
        //.extract().path("items.findAll{it.name == 'Apple iPod touch 32GB  (Assorted Colors)'}.salesPrice");

        //Home work
        List<HashMap<String, Object>> salesPrice = given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                //.findAll and it.name is from groovy //as all value
                .extract().path("items.findAll{it.name == 'Refurbished Apple iPod Touch 7th Generation 32GB Space Gray MVHW2LL/A'}.salePrice");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The sale price is: " + salesPrice);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9) Get the Names which have salePrice less than 200
    @Test
    public void test009() {

        List<String> namesOfIpods = given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                //.findAll and it.name is from groovy //as all value
                .extract().path("items.findAll{it.salePrice < 200}.name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of items that price is less than 200 are: " + namesOfIpods);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10) Get the msrp of items that Start with name = Ref
    @Test
    public void test010() {

        List<String> msrp = given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                //.findAll and it.name is from groovy //as all value
                .extract().path("items.findAll{it.name==~/Ref.*/}.msrp");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The msrp of items that start with Ref are: " + msrp);
        System.out.println("------------------End of Test---------------------------");
    }

    // 11) Get the sale price of items that End with name =ing
    @Test
    public void test011() {

        List<String> salePrice = given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                //.findAll and it.name is from groovy //as all value
                .extract().path("items.findAll{it.name==~/.*ing/}.salePrice");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The salePrice of items that end with ing are: " + salePrice);
        System.out.println("------------------End of Test---------------------------");
    }

}
