package com.holmes.chain;

/**
 * @Description:    抽象的处理类
 * @Author:         holmes
 * @CreateDate:     2018/12/14 14:24
 * @Version:        1.0.0
*/
public abstract class Handler {

    protected Handler next;

    public Handler(Handler next) {
        this.next = next;
    }

    /**
     * 处理响应信息
     * @param response
     * @return
     */
    protected abstract Response handle(Response response);
}
