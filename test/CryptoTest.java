import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

class CryptoTest {

    @Test
    void signature_from_example() throws Exception {
        var text = "{\"user\":\"3nYTOSjdlF6UTz9Ir\",\"country\":\"XX\",\"currency\":\"EUR\",\"operator_id\":1,\"token\":\"cd6bd8560f3bb8f84325152101adeb45\",\"platform\":\"GPL_DESKTOP\",\"game_id\":39,\"lang\":\"en\",\"lobby_url\":\"https://examplecasino.io\",\"ip\":\"::ffff:10.0.0.39\"}";
        String signature = Crypto.generateSignature(text);
        System.out.println(signature);
        assert signature.equals("FFdiceKlsnmrBMhWopZYQXrJSMqxJWkynbaiOnuUE1p4mrg2UydlSAdKbOWQZ7o5USbA3SHPum3XuRCa9INWbcp+fdcOhjz0S0JLKF6uXJR2T8zlF+L+8v2lkBzPOvLg6yRuUyspxtcHB6Vlyd0Sj4v8vk5HxoVv1ZV7EpJglzHs7xKchuifjjhUwkX7AAZwvJCNKo/VZrhtfxHd/k8aq7+9h7AztiiiLO7CXbEbo8snDqMqoB/tBAImv45NcAuReBZGb3QY37MbRoOT/uQGj+9ae5VRNpJ74qs3COraDqZ5kqzsa5SjN1cML2foP13Deqp0FTY0Ek521d8CPWQY6A==");
    }


    @Test
    void string_sign_and_validate() throws Exception {
        var text = "message";
        String signature = Crypto.generateSignature(text);
        System.out.println(signature);
        assert Crypto.validateSignature(signature, text);

    }

    @Test
    void json_sign_and_validate() throws Exception {
        JSONObject obj = new JSONObject();

        obj.put("name", "foo");
        obj.put("num", 100);

        StringWriter out = new StringWriter();
        obj.writeJSONString(out);

        String jsonText = out.toString();
        System.out.println(jsonText);

        String signature = Crypto.generateSignature(jsonText);
        System.out.println(signature);
        assert Crypto.validateSignature(signature, jsonText);


    }
}