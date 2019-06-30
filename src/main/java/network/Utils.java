package network;

import common.CommonActions;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class Utils {

    private Logger logger = LoggerFactory.getLogger(Utils.class);
    protected CommonActions commonActions = new CommonActions();

    protected JSONObject responseToPostRequest(String requestUrl, JSONObject requestBody){
        JSONObject responseBody = null;
        logger.info("POST REQUEST - {} BODY {}",requestUrl,requestBody);
        Response response = given().body(requestBody.toString()).when().post(requestUrl);
        logger.info("RESPONSE {}",response.body().asString());
        try{
            responseBody = new JSONObject(response.body().asString());
        } catch (Exception ex){
            logger.error("Exception on get response body",ex);
            Assert.fail("Response body is empty!");
        }
        return responseBody;
    }

    protected JSONObject responseToGetRequest(String requestUrl){
        JSONObject responseBody = null;
        Response response = given().when().get(requestUrl);
        logger.info("GET REQUEST - {}" + requestUrl);
        try{
            responseBody = new JSONObject(response.body().asString());
        } catch (Exception ex){
            logger.error("Exception on get response body",ex);
            Assert.fail("Response body is empty!");
        }
        logger.info("RESPONSE - {}",responseBody.toString());
        return responseBody;
    }
}
