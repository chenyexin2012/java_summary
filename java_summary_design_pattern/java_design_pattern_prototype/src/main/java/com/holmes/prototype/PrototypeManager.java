package com.holmes.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author:         holmes
 * @CreateDate:     2018/12/13 19:56
 * @Version:        1.0.0
*/
public class PrototypeManager {

    private static Map<String, Prototype> map = new HashMap<>();

    private PrototypeManager(){}

    public synchronized static void setPrototype(String prototypeId , Prototype prototype){
        map.put(prototypeId, prototype);
    }

    public synchronized static Prototype getPrototype(String prototypeId) throws Exception{
        Prototype prototype = map.get(prototypeId);
        if(prototype == null){
            throw new Exception("您希望获取的原型还没有注册或已被销毁");
        }
        return (Prototype) prototype.clone();
    }

    public synchronized static void removePrototype(String prototypeId){
        map.remove(prototypeId);
    }
}
