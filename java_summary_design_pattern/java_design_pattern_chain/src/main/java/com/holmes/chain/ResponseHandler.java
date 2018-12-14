package com.holmes.chain;

import java.util.Objects;

/**
 * @Description: 责任链模式
 * @Author: holmes
 * @CreateDate: 2018/12/14 14:48
 * @Version: 1.0.0
 */
public class ResponseHandler {

    private Handler chain;

    private Response response;

    public ResponseHandler(Handler chain, Response response) {
        this.chain = Objects.requireNonNull(chain);
        this.response = Objects.requireNonNull(response);
    }

    public Response getResponse() {
        return this.response;
    }

    public Handler getChain() {
        return this.getChain();
    }

    public void handle() {
        chain.handle(this.response);
    }
}
