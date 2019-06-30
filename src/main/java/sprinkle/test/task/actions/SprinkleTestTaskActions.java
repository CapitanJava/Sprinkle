package sprinkle.test.task.actions;

import network.RequestModel;
import network.Utils;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static constants.Constants.*;
import static java.util.Base64.*;

public class SprinkleTestTaskActions extends Utils {

    private JSONObject tokenHeader;
    private JSONObject tokenPayload;
    private JSONObject response;
    private Decoder decoder = getDecoder();
    private Logger logger = LoggerFactory.getLogger(SprinkleTestTaskActions.class);

    public SprinkleTestTaskActions getJWTToken(RequestModel model){
        JSONObject body = new JSONObject();
        body.put("username",model.getUsername())
                .put("password",model.getPassword());
        response = responseToPostRequest(GET_TOKEN_URL,body);
        return this;
    }

    public SprinkleTestTaskActions getUserInfo(){
        response = responseToGetRequest(GET_USER_INFO_URL + response.getString("token"));
        return this;
    }

    public SprinkleTestTaskActions encodeToken(){
        String token = response.getString("token");
        tokenHeader = new JSONObject(new String(decoder.decode(token.split("\\.")[0])));
        tokenPayload = new JSONObject(new String(decoder.decode(token.split("\\.")[1])));
        logger.info("ENCODED TOKEN HEADER - {}",tokenHeader);
        logger.info("ENCODED TOKEN PAYLOAD - {}",tokenPayload);
        return this;
    }

    public SprinkleTestTaskActions assertTokenHasUsername(String email){
        Assert.assertEquals(tokenPayload.getString("username"),email);
        return this;
    }

    public SprinkleTestTaskActions verifyResponseJSONSchema(String schemaName){
        commonActions.verifyByJsonSchema(response,"/JSON_schema/" + schemaName);
        return this;
    }
}
