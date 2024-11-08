package tests.hamcrest.Authors;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostTestBooks {


    File postBooks = new File(ConfigurationReader.getProperty("postBooksFile"));

    @DisplayName("POST books - Hamcrest")
    @Test
    public void postBooksSuccessTest() {

        baseURI = ConfigurationReader.getProperty("fakerApiUrl");


        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(postBooks).log().all()
                .when().post("/v1/Books/0")
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK);
        System.out.println("File path for postBooks: " + postBooks);

    }

    @DisplayName("POST Books - Negative Test with Invalid Data")
    @Test
    public void postBooksNegativeTest() {
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body("").log().all()
                .when().post("/v1/Books")
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}
