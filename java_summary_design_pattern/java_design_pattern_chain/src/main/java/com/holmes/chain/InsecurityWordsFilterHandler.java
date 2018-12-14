package com.holmes.chain;

/**
 * @Description: 不安全词汇过滤
 * @Author: holmes
 * @CreateDate: 2018/12/14 14:40
 * @Version: 1.0.0
 */
public class InsecurityWordsFilterHandler extends Handler {

    public InsecurityWordsFilterHandler(Handler next) {
        super(next);
    }

    @Override
    protected Response handle(Response response) {
        response.setContent(response.getContent().replace("insecurityWords", ""));
        response.setRemark(response.getRemark() + " InsecurityWordsFilterHandler");
        if (null != this.next) {
            return next.handle(response);
        }
        return response;
    }
}
