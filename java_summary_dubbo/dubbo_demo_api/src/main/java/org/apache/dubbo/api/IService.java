package org.apache.dubbo.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.exception.NullInputException;

/**
 * dubbo服务统一接口
 * 入参与返回参数统一使用JSONObject
 */
public interface IService {

    public JSONObject handle(JSONObject input) throws NullInputException;
}
