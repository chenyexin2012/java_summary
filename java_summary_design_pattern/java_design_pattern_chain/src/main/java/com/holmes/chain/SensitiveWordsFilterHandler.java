package com.holmes.chain;

/**
 * @Description: 敏感词汇过滤
 * @Author: holmes
 * @CreateDate: 2018/12/14 14:34
 * @Version: 1.0.0
 */
public class SensitiveWordsFilterHandler extends Handler {

    public SensitiveWordsFilterHandler(Handler next) {
        super(next);
    }

    @Override
    protected Response handle(Response response) {
        response.setContent(response.getContent().replace("sensitiveWords", ""));
        response.setRemark(response.getRemark() + " SensitiveWordsFilterHandler");
        if (null != this.next) {
            return next.handle(response);
        }
        return response;
    }
}
