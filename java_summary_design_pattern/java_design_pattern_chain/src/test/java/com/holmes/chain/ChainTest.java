package com.holmes.chain;

import org.junit.Test;

public class ChainTest {

    @Test
    public void test() {

        Response response = new Response("insecurityWordschainsensitiveWords sensitiveWordsof " +
                "sensitiveWordsresponsibilityinsecurityWords", "chain");

        ResponseHandler responseHandler = new ResponseHandler(new SensitiveWordsFilterHandler(
                new InsecurityWordsFilterHandler(null)), response);
        responseHandler.handle();

        System.out.println(response);
    }
}
