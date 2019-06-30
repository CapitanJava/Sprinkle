package testClasses;

import network.RequestModel;
import org.junit.Test;

import static constants.Constants.PASSWORD;
import static constants.Constants.USERNAME;


public class TestClass extends Base {

    @Test
    public void assertJWTTokenUsername(){
        RequestModel model = RequestModel.newBuilder()
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .build();
        taskActions.getJWTToken(model)
                .encodeToken()
                .assertTokenHasUsername(USERNAME);
    }

    @Test
    public void getUserInfo(){
        RequestModel model = RequestModel.newBuilder()
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .build();
        taskActions.getJWTToken(model)
                .getUserInfo()
                .verifyResponseJSONSchema("sprincle_user_info.json");
    }

    @Test
    public void getJWTTokenWrongUsername(){
        RequestModel model = RequestModel.newBuilder()
                .setUsername(USERNAME + ".wrong")
                .setPassword(PASSWORD)
                .build();
        taskActions.getJWTToken(model)
                .verifyResponseJSONSchema("bad_credentials.json");
    }

    @Test
    public void getJWTTokenWrongPassword(){
        RequestModel model = RequestModel.newBuilder()
                .setUsername(USERNAME)
                .setPassword(PASSWORD + "wrong")
                .build();
        taskActions.getJWTToken(model)
                .verifyResponseJSONSchema("bad_credentials.json");
    }
}
