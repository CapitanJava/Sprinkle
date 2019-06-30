package common;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;

public class CommonActions {

    public void verifyByJsonSchema(JSONObject body, String jsonSchemaName) {
        try (InputStream inputStream = getClass().getResourceAsStream(jsonSchemaName)) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.load(rawSchema);
            try {
                schema.validate(body);
            } catch (ValidationException e) {
                Assert.fail(e.toJSON().toString(4));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
