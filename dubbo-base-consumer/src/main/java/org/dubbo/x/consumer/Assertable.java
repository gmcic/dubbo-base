package org.dubbo.x.consumer;


import org.dubbo.x.facade.RestResult;

/**
 * Created by tom on 16/7/1.
 */
public interface Assertable {
    void assertBlack(RestResult t) throws Exception;
}
