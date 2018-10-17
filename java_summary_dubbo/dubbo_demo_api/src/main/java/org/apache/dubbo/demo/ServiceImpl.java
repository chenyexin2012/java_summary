package org.apache.dubbo.demo;

import com.alibaba.fastjson.JSONObject;

/**
 * dubbo服务统一接口
 * 入参与返回参数统一使用JSONObject
 */
public interface ServiceImpl {

    public JSONObject handle(JSONObject input);
}
