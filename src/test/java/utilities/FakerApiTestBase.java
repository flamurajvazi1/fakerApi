package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FakerApiTestBase {

    protected static Response response;

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("fakerApiUrl");
    }

    @AfterAll
    public static void destroy(){
        reset();
    }

//    public void verifyZipCode(JsonPath jsonPath, String expZipCode){
//        assertEquals(expZipCode, jsonPath.getString("'post code'"));
//    }
}
