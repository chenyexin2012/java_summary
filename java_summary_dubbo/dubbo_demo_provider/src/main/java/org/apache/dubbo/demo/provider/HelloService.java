package org.apache.dubbo.demo.provider;

import com.alibaba.dubbo.common.serialize.support.json.JsonObjectInput;
import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.demo.ServiceImpl;
import org.apache.dubbo.exception.NullInputException;

public class HelloService implements ServiceImpl {

    @Override
    public JSONObject handle(JSONObject input) {

        if(null == input) {
//            throw new NullInputException();
        }
        return null;
    }
}
