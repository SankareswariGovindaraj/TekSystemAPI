package apple.com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class APIGetCall extends DemoApplication {
   public String response;
    public static Logger log= LogManager.getLogger(DemoApplication.class.getName());

@BeforeTest
public void initilize()
{
    //RestTemplate restTemplate=new RestTemplate();
}
    @Test
    public void getService() {

        String baseURI = "https://istheapplestoredown.com/api/v1/status/worldwide";
        RestTemplate restTemplate = new RestTemplate();
        response = restTemplate.getForObject(baseURI, String.class);
        JSONObject rootjson = new JSONObject(response);
        Set<String> keys = rootjson.keySet();
        int cnt = 0;
        for (String key : keys) {

                if (rootjson.getJSONObject(key).get("status").equals("y") ) {
                    //print the country where apple store is down.this country name can also be printed logger and written int ofile or report.
                    System.out.println("Apple Store Down in " + rootjson.getJSONObject(key).get("name"));
                    log.info("Apple Store Down in " + rootjson.getJSONObject(key).get("name"));
                    cnt ++;

                }
        }


        if (cnt>0)
        {
            Assert.fail("Test case Failed");
        }



    }
}
