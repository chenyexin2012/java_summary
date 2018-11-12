package org.apache.dubbo.demo.provider.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.api.IService;
import org.apache.dubbo.exception.NullInputException;
import org.apache.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloService implements IService {

    @Override
    public JSONObject handle(JSONObject input) throws NullInputException {

        if(null == input) {
            throw new NullInputException();
        }
        String message = input.getString("message");
        System.out.println("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "] request from "
                + RpcContext.getContext().getRemoteAddress() + ", message:" + message);

        JSONObject output = new JSONObject();
        output.put("message", "response from provider: " + RpcContext.getContext().getLocalAddress());
        return output;
    }
}
