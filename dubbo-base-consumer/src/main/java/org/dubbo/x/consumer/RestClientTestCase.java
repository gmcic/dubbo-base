package org.dubbo.x.consumer;


import org.dubbo.x.facade.RestResult;
import org.dubbo.x.util.ConstantVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * @author tom
 */
public class RestClientTestCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientTestCase.class);
    public static final Client CLIENT = ClientBuilder.newClient();


    public Invocation.Builder getBuilder(String url, String token) {
        WebTarget target = CLIENT.target(url);
        return target.request().header(ConstantVariable.HEADER_AUTHORIZATION_KEY, token);
    }

    public void  assertRequest(Assertable assertable,String url, String token) {
        LOGGER.info("Request url: {}", url);

        final Response response = getBuilder(url, token).get();

        assertRequest(assertable, response);
    }

    public void  assertRequest(Assertable assertable, Response response) {
        try {
            RestResult restResult = response.readEntity(RestResult.class);
            assertable.assertBlack(restResult);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            response.close();
            throw new ProcessingException("测试结束");
        }
    }

}
